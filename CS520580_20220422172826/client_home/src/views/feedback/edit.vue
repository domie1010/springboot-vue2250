<template>
	<div class="diy_edit page_feedback" id="feedback_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
					<div v-if="$check_field('set','theme') || $check_field('add','theme') || $check_field('get','theme')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>主题:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_theme" v-model="form['theme']" placeholder="请输入主题" v-if="(form['theme'] && $check_field('set','theme')) || (!form['theme'] && $check_field('add','theme'))"  :disabled="disabledObj['theme_isDisabled']"/>
							<span v-else-if="$check_field('get','theme')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','relevant_vouchers') || $check_field('add','relevant_vouchers') || $check_field('get','relevant_vouchers')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>相关凭证:
							</span>
						</div>
						<!-- 图片 -->
						<input type="file" :disabled="disabledObj['relevant_vouchers_isDisabled']" style="display: none;" id="form_img_relevant_vouchers" title="relevant_vouchers" @change="change_file($event.target.files,'relevant_vouchers')"/>
						<!-- 修改权限 -->
						<div class="diy_field diy_img" v-if="form['relevant_vouchers'] && $check_field('set','relevant_vouchers')">
							<label for="form_img_relevant_vouchers">
								<img :src="$fullUrl(form['relevant_vouchers'])" />
							</label>
						</div>
						<!-- 添加权限 -->
						<div class="diy_field diy_img" v-else-if="!form['relevant_vouchers'] && $check_field('add','relevant_vouchers')">
							<label for="form_img_relevant_vouchers">
								<div class="btn_add_img">
									<span>+</span>
								</div>
							</label>
						</div>
						<!-- 查询权限 -->
						<div class="diy_field diy_img" v-else-if="$check_field('get','relevant_vouchers')">
							<img :src="$fullUrl(form['relevant_vouchers'])" />
						</div>
					</div>
					<div v-if="$check_field('set','user_name') || $check_field('add','user_name') || $check_field('get','user_name')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>用户名:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_user_name" :disabled="disabledObj['user_name_isDisabled']" v-model="form['user_name']" v-if="(form['user_name'] && $check_field('set','user_name')) || (!form['user_name'] && $check_field('add','user_name'))" >
								<option v-for="o in list_user_user_name" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','user_name')">{{ form['user_name'] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','contact_number') || $check_field('add','contact_number') || $check_field('get','contact_number')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>联系电话:
							</span>
						</div>
						<!-- 文本 -->
						<div class="diy_field diy_text">
							<input type="text" id="form_contact_number" v-model="form['contact_number']" placeholder="请输入联系电话" v-if="(form['contact_number'] && $check_field('set','contact_number')) || (!form['contact_number'] && $check_field('add','contact_number'))"  :disabled="disabledObj['contact_number_isDisabled']"/>
							<span v-else-if="$check_field('get','contact_number')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="$check_field('set','content') || $check_field('add','content') || $check_field('get','content')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>内容:
							</span>
						</div>
						<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_content" v-model="form['content']" v-if="(form['content'] && $check_field('set','content')) || (!form['content'] && $check_field('add','content'))" :disabled="disabledObj['content_isDisabled']" />
							<span v-else-if="$check_field('get','content')">{{ form[''] }}</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核状态:
							</span>
						</div>
						<div class="diy_field diy_select" v-if="$check_action('/feedback/edit','examine')">
							<!--<span> {{ form['examine_state'] }} </span>-->
							<select v-model="form['examine_state']">
								<option value="未审核">
									未审核
								</option>
								<option value="已通过">
									已通过
								</option>
								<option value="未通过">
									未通过
								</option>
							</select>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_state'] }}
							</span>
						</div>
					</div>
					<div v-if="user_group === '管理员' || $check_examine()" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								审核回复:
							</span>
						</div>
						<div class="diy_field diy_desc" v-if="$check_action('/feedback/edit','examine')">
							<textarea v-model="form['examine_reply']"></textarea>
						</div>
						<div class="diy_field diy_text" v-else>
							<span>
								{{ form['examine_reply'] }}
							</span>
						</div>
					</div>
				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/feedback/get_obj?",
				url_add: "~/api/feedback/add?",
				url_set: "~/api/feedback/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
					"theme": "",
					"relevant_vouchers": "",
					"user_name": 0,
					"contact_number": "",
					"content": "",
					"feedback_id": 0,
				},

				obj: {
					"theme":'', // 主题
					"relevant_vouchers":'', // 相关凭证
					"user_name": 0, // 用户名
					"contact_number":'', // 联系电话
					"content":'', // 内容
					"examine_state": "未审核",
					"examine_reply": "",
					"feedback_id": 0,
				},

				// 表单字段
				form: {
					"theme":'', // 主题
					"relevant_vouchers":'', // 相关凭证
					"user_name": 0, // 用户名
					"contact_number":'', // 联系电话
					"content":'', // 内容
					"examine_state": "未审核",
					"examine_reply": "",
					"feedback_id": 0,
				},
				disabledObj:{
					"theme_isDisabled": false,
					"relevant_vouchers_isDisabled": false,
					"user_name_isDisabled": false,
					"contact_number_isDisabled": false,
					"content_isDisabled": false,
				},
				// 用户列表
				list_user_user_name: [],

				// ID字段
				field: "feedback_id",
			}
		},
		methods: {
			/**
			 * 获取注册用户用户列表
			 */
			async get_list_user_user_name() {
				// if(this.user_group !== "管理员" && this.form["user_name"] === 0) {
				//     this.form["user_name"] = this.user.user_id;
				// }
				var json = await this.$get("~/api/user/get_list?user_group=注册用户");
				if(json.result && json.result.list){
					this.list_user_user_name = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			async get_user_session_user_name(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=注册用户");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["user_name"] = user_id
								_this.disabledObj['user_name' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "user_name") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},

			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/feedback/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				if (form) {
					this.obj = $.push(this.obj ,form);
					this.form = $.push(this.form ,form);
				}
				var arr = []
				for (let key in form) {
					arr.push(key)
				}
				for (var i=0;i<arr.length;i++){
					this.disabledObj[arr[i] + '_isDisabled'] = true
				}
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				var form = $.db.get("form");
				var obj = Object.assign({} ,form ,this.obj);
				if (obj) {
          delete(obj.examine_state)
          delete(obj.examine_reply)
					this.obj = $.push(this.obj ,obj);
				}
				if (form) {
          delete(form.examine_state)
          delete(form.examine_reply)
					this.form = $.push(this.form ,form);
				}
				if(func){
					func(json);
				}
			},

		},
		created() {
			this.get_user_session_user_name();
			this.get_list_user_user_name();
		},
	}
</script>

<style>
</style>
