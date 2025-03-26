<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="70px">

			<!--<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">-->
				<!--<el-form-item label="头像" prop="avatar">-->
					<!--<el-upload class="avatar-uploader" drag accept="image/gif, image/jpeg, image/png, image/jpg"-->
						<!--action="" :http-request="uploadAvatar" :show-file-list="false">-->
						<!--<img v-if="form.avatar" :src="$fullUrl(form.avatar)" class="avatar">-->
						<!--<i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
					<!--</el-upload>-->
				<!--</el-form-item>-->
			<!--</el-col>-->

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面图" prop="img">
					<el-upload class="avatar-uploader" drag accept="image/gif, image/jpeg, image/png, image/jpg"
						action="" :http-request="uploadImg" :show-file-list="false">
						<img v-if="form.img" :src="$fullUrl(form.img)" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="标题" prop="title">
					<el-input v-model="form.title" placeholder="请输入标题"></el-input>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="分类">
					<el-select v-model="form.type">
						<el-option v-for="(o,i) in list_forum_type" :value="o.name" :key="i" :label="o.name"></el-option>
					</el-select>
				</el-form-item>
			</el-col>

			<!--<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">-->
				<!--<el-form-item label="关键词" prop="keywords">-->
					<!--<el-input v-model="form.keywords" placeholder="请输入关键词"></el-input>-->
				<!--</el-form-item>-->
			<!--</el-col>-->

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="标签" prop="tag">
					<el-input v-model="form.tag" placeholder="请输入标签"></el-input>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="昵称" prop="nickname">
					<el-input v-model="form.nickname" placeholder="请输入昵称"></el-input>
				</el-form-item>
			</el-col>

			<!--<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">-->
				<!--<el-form-item label="描述" prop="description">-->
					<!--<el-input v-model="form.description" placeholder="请输入描述" :maxlength="255" show-word-limit></el-input>-->
				<!--</el-form-item>-->
			<!--</el-col>-->

			<!--<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">-->
				<!--<el-form-item label="来源地址" prop="url">-->
					<!--<el-input v-model="form.url" placeholder="请输入来源地址"></el-input>-->
				<!--</el-form-item>-->
			<!--</el-col>-->

			<!--<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">-->
				<!--<el-form-item label="排序" prop="display">-->
					<!--<el-input-number v-model="form.display" placeholder="请输入排序"></el-input-number>-->
				<!--</el-form-item>-->
			<!--</el-col>-->

			<!--<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">-->
				<!--<el-form-item label="点击量" prop="hits">-->
					<!--<el-input-number v-model="form.hits" placeholder="请输入点击量"></el-input-number>-->
				<!--</el-form-item>-->
			<!--</el-col>-->

			<el-col :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="正文" prop="content">
					<quill-editor v-model="form.content">
					</quill-editor>
				</el-form-item>
			</el-col>

			<el-col :xs="24" :sm="24" :lg="24" class="el_form_btn_warp">
				<el-form-item>
					<el-col :xs="24" :sm="24" :lg="12" class="el_form_btn el_form_btn_1">
						<el-button style="width: 100%; float: left;" type="primary" @click="submit()">提交</el-button>
					</el-col>
					<el-col :xs="24" :sm="24" :lg="12" class="el_form_btn el_form_btn_2">
						<el-button style="width: 100%; float: right;" @click="cancel()">取消</el-button>
					</el-col>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "forum_id",
				url_add: "~/api/forum/add?",
				url_set: "~/api/forum/set?",
				url_get_obj: "~/api/forum/get_obj?",
				url_upload: "~/api/forum/upload?",

				query: {
					forum_id: 0
				},

				list_forum_type: [],

				form: {
					forum_id: 0,
					user_id: this.$store.state.user.user_id,
					display: 0,
					nickname: "",
					hits: 0,
					title: "",
					keywords: "",
					description: "",
					url: "",
					tag: "",
					img: "",
					content: "",
					avatar: ""
				},

			}
		},
		methods: {

			// submit_before(params) {
			// 	params.user_id = this.$store.state.user.user_id;
			// 	return params;
			// },

			submit_check(params) {
				var msg = "";
				if (params.title === "") {
					msg = "标题不能为空";
					return msg;
				}
				if (params.nickname === "") {
					msg = "昵称不能为空";
					return msg;
				}
				// if (params.url === "") {
				// 	msg = "来源地址不能为空";
				// 	return msg;
				// }
				// if (params.keywords === "") {
				// 	msg = "关键词不能为空";
				// 	return msg;
				// }
			},

			async get_forum_type() {
				var json = await this.$get("~/api/forum_type/get_list?");
				if(json.result){
					this.list_forum_type = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadAvatar(param) {
				this.uploadFile(param.file, "avatar");
			},

			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadImg(param) {
				this.uploadFile(param.file, "img");
			},

			/**
			 * 提交前事件
			 * @param {Object} param
			 */
			submit_before(param){
				console.log("提交前事件",param);
				return param;
			}

		},
		created(){
			this.get_forum_type();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
