(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-b5baad8e"],{"20b0":function(e,t,a){"use strict";function n(e){var t=new Date(e),a=t.getFullYear(),n=t.getMonth()+1<10?"0"+(t.getMonth()+1):t.getMonth()+1,r=t.getDate()<10?"0"+t.getDate():t.getDate(),s=t.getHours()<10?"0"+t.getHours():t.getHours(),o=t.getMinutes()<10?"0"+t.getMinutes():t.getMinutes(),i=t.getSeconds()<10?"0"+t.getSeconds():t.getSeconds();return a+"-"+n+"-"+r+" "+s+":"+o+":"+i}a.d(t,"a",(function(){return n}))},bb51:function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-main",{staticStyle:{"padding-top":"0px"}},[a("Navigator"),a("el-row",[a("el-card",{staticStyle:{height:"200px"},attrs:{shadow:"hover"}},[a("el-row",[a("el-col",{staticClass:"px-5",attrs:{span:4}},[a("el-avatar",{attrs:{size:100,src:e.circleUrl}})],1),a("el-col",{attrs:{span:16}},[a("h4",[e._v(e._s(e.user.username))]),a("h6",[e._v("邮箱: "+e._s(e.user.emailAddress))]),a("h6",[e._v("注册时间: "+e._s(e.formattedDate))])])],1),a("el-row",[a("el-menu",{staticClass:"el-menu-demo",attrs:{router:"","default-active":e.activeIndex,mode:"horizontal"},on:{select:e.handleSelect}},[a("el-menu-item",{attrs:{index:"/main/"}},[e._v("Home")]),a("el-menu-item",{attrs:{index:"/main/overview"}},[e._v("Overview")]),a("el-menu-item",{attrs:{index:"/main/research"}},[e._v("Research")]),a("el-menu-item",{attrs:{index:"/main/information"}},[e._v("Info")])],1)],1)],1)],1),a("router-view")],1)},r=[],s=a("4c60"),o=a("da46"),i=a.n(o),l=a("20b0"),c={data:function(){return{expert:this.$store.state.expert,user:this.$store.state.user,circleUrl:i.a,activeIndex:"1"}},computed:{formattedDate:function(){return Object(l["a"])(this.user.createdDate)}},methods:{handleSelect:function(e,t){console.log(e,t)}},components:{Navigator:s["default"]}},u=c,d=a("4e82"),m=Object(d["a"])(u,n,r,!1,null,null,null);t["default"]=m.exports}}]);