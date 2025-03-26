<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="新闻资讯"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="交流论坛"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/registered_user/list', 'get')"
				:list="result_registered_user_user_name"
				title="注册用户用户名"
				source_table="registered_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/feedback/list', 'get')"
				:list="result_feedback_theme"
				title="意见反馈主题"
				source_table="feedback"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/video_sharing/list', 'get')"
				:list="result_video_sharing_title"
				title="视频分享标题"
				source_table="video_sharing"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_registered_user_user_name":[],
			"result_feedback_theme":[],
			"result_video_sharing_title":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取交流论坛
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取user_name
	 */
	get_registered_user_user_name(){
		this.$get("~/api/registered_user/get_list?like=0", { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_registered_user_user_name = json.result.list;
			result_registered_user_user_name.map(o => o.title = o['user_name'])
	  			this.result_registered_user_user_name = result_registered_user_user_name
		 	}
		});
	},
	/**
	 * 获取theme
	 */
	get_feedback_theme(){
		this.$get("~/api/feedback/get_list?like=0", { page: 1, size: 10, "theme": this.query.word }, (json) => {
		  if (json.result) {
			var result_feedback_theme = json.result.list;
			result_feedback_theme.map(o => o.title = o['theme'])
	  			this.result_feedback_theme = result_feedback_theme
		 	}
		});
	},
	/**
	 * 获取title
	 */
	get_video_sharing_title(){
		this.$get("~/api/video_sharing/get_list?like=0", { page: 1, size: 10, "title": this.query.word }, (json) => {
		  if (json.result) {
			var result_video_sharing_title = json.result.list;
			result_video_sharing_title.map(o => o.title = o['title'])
	  			this.result_video_sharing_title = result_video_sharing_title
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_registered_user_user_name();
		this.get_feedback_theme();
		this.get_video_sharing_title();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_registered_user_user_name();
	  this.get_feedback_theme();
	  this.get_video_sharing_title();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
