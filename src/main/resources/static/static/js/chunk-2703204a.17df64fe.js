(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2703204a"],{"0dc6":function(e,t,s){"use strict";var o=s("aad5"),n=s.n(o);n.a},"5c9c":function(e,t,s){"use strict";s.r(t);var o=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"wrap",staticStyle:{"padding-top":"10%","padding-left":"35%"}},[s("center",[s("el-card",{staticClass:"register-card"},[s("div",{staticClass:"register-card-header",attrs:{slot:"header"},slot:"header"},[s("div",[s("el-button",{attrs:{type:"info",plain:"",id:"back"},on:{click:e.ToLogin}},[s("span",[e._v("返回")])])],1),s("div",{attrs:{id:"name"}},[s("span",[e._v("用户注册")])])]),s("div",{staticClass:"register-card-input"},[s("el-input",{attrs:{placeholder:"请输入用户名",type:"text"},model:{value:e.loginInfoVo.username,callback:function(t){e.$set(e.loginInfoVo,"username",t)},expression:"loginInfoVo.username"}}),s("el-input",{attrs:{placeholder:"请输入6~20位密码",type:"password"},model:{value:e.loginInfoVo.password,callback:function(t){e.$set(e.loginInfoVo,"password",t)},expression:"loginInfoVo.password"}}),s("el-input",{attrs:{disabled:e.inputed,placeholder:"请输入邮箱",type:"text"},model:{value:e.loginInfoVo.emailAddress,callback:function(t){e.$set(e.loginInfoVo,"emailAddress",t)},expression:"loginInfoVo.emailAddress"}}),s("el-row",{attrs:{align:"top",type:"flex"}},[s("el-input",{attrs:{placeholder:"请输入验证码",type:"text"},model:{value:e.loginInfoVo.code,callback:function(t){e.$set(e.loginInfoVo,"code",t)},expression:"loginInfoVo.code"}}),s("el-button",{staticClass:"bottomControl",attrs:{disabled:e.inputed,name:"codeButton",type:"primary",plain:""},on:{click:e.sendPin}},[e.inputed?s("span",[e._v(e._s(this.auth_time))]):s("span",[e._v("获取验证码")])])],1)],1),s("el-button",{staticClass:"register-bottomcontrol",on:{click:e.register}},[e._v("注册")])],1)],1)],1)},n=[],i=(s("31f7"),{data:function(){return{loginInfoVo:{username:"",password:"",emailAddress:"",code:""},responseResult:[],code:"",emailAddress:"",inputed:!1,auth_time:""}},mounted:function(){window.sessionStorage.getItem("username")&&this.$router.replace("/home")},methods:{ToMain:function(){this.$router.push("/main")},ToLogin:function(){this.$router.replace({path:"/signIn"})},sendPin:function(){var e=this;this.$axios.get("sendPin",{params:{emailAddress:this.loginInfoVo.emailAddress}}).then((function(t){if(305==t.data.code)e.$notify.error({title:"错误",message:"邮箱不合法"});else if(306==t.data.code)e.$notify.error({title:"错误",message:"邮箱已被注册"});else if(200==t.data.code){e.code=t.data.message,e.emailAddress=e.loginInfoVo.emailAddress,e.inputed=!0,e.$notify({title:"成功",message:"验证码已发送",type:"success"}),e.auth_time=60;var s=setInterval((function(){e.auth_time--,e.auth_time<=0&&(e.inputed=!1,clearInterval(s))}),1e3)}})).catch((function(e){console.log(e)}))},register:function(){var e=this;this.code!=this.loginInfoVo.code?this.$notify.error({title:"错误",message:"验证码有误或已过期"}):""==this.loginInfoVo.emailAddress?this.$notify.error({title:"错误",message:"请输入邮箱"}):0==this.inputed?this.$notify.error({title:"提示",message:"请重新获取验证码"}):this.$axios.get("register",{params:{username:this.loginInfoVo.username,password:this.loginInfoVo.password,emailAddress:this.emailAddress}}).then((function(t){e.responseResult=JSON.stringify(t.data),200===t.data.code?(e.$notify({title:"成功",message:"注册成功！",type:"success"}),e.$axios.get("getUser",{params:{username:e.loginInfoVo.username}}).then((function(t){e.$store.dispatch("setUser",t.data)})),e.$router.replace("home")):201===t.data.code?e.$notify.error({title:"错误",message:"该用户已存在"}):400===t.data.code?e.$notify.error({title:"错误",message:"输入不合法"}):402===t.data.code&&e.$notify.error({title:"错误",message:"输入不合法"})})).catch((function(e){console.log(e)}))}}}),a=i,r=(s("0dc6"),s("4e82")),l=Object(r["a"])(a,o,n,!1,null,null,null);t["default"]=l.exports},aad5:function(e,t,s){}}]);