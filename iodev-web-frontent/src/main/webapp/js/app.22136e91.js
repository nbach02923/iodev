(function(e){function a(a){for(var t,o,u=a[0],c=a[1],l=a[2],h=0,s=[];h<u.length;h++)o=u[h],Object.prototype.hasOwnProperty.call(i,o)&&i[o]&&s.push(i[o][0]),i[o]=0;for(t in c)Object.prototype.hasOwnProperty.call(c,t)&&(e[t]=c[t]);p&&p(a);while(s.length)s.shift()();return r.push.apply(r,l||[]),n()}function n(){for(var e,a=0;a<r.length;a++){for(var n=r[a],t=!0,o=1;o<n.length;o++){var u=n[o];0!==i[u]&&(t=!1)}t&&(r.splice(a--,1),e=c(c.s=n[0]))}return e}var t={},o={app:0},i={app:0},r=[];function u(e){return c.p+"js/"+({}[e]||e)+"."+{"chunk-7fc83f64":"b9e445eb","chunk-0943ce22":"d87e7e5e","chunk-44f76a2d":"e85ffa38","chunk-41883a9e":"e72670ba","chunk-59bef27d":"601ab007","chunk-3c310826":"6712de46","chunk-5734273a":"f00b2276","chunk-adb45e24":"93988a7a","chunk-0e2c76d8":"c5c78a72","chunk-77a1d69a":"b2a65d66"}[e]+".js"}function c(a){if(t[a])return t[a].exports;var n=t[a]={i:a,l:!1,exports:{}};return e[a].call(n.exports,n,n.exports,c),n.l=!0,n.exports}c.e=function(e){var a=[],n={"chunk-7fc83f64":1,"chunk-0943ce22":1,"chunk-44f76a2d":1,"chunk-41883a9e":1,"chunk-59bef27d":1,"chunk-3c310826":1,"chunk-5734273a":1,"chunk-adb45e24":1,"chunk-0e2c76d8":1,"chunk-77a1d69a":1};o[e]?a.push(o[e]):0!==o[e]&&n[e]&&a.push(o[e]=new Promise((function(a,n){for(var t="css/"+({}[e]||e)+"."+{"chunk-7fc83f64":"fa10b878","chunk-0943ce22":"1fd92f14","chunk-44f76a2d":"c130c76b","chunk-41883a9e":"bd431dd4","chunk-59bef27d":"65c7232d","chunk-3c310826":"a097910f","chunk-5734273a":"e3884748","chunk-adb45e24":"814abb42","chunk-0e2c76d8":"d2de4f61","chunk-77a1d69a":"ce138006"}[e]+".css",i=c.p+t,r=document.getElementsByTagName("link"),u=0;u<r.length;u++){var l=r[u],h=l.getAttribute("data-href")||l.getAttribute("href");if("stylesheet"===l.rel&&(h===t||h===i))return a()}var s=document.getElementsByTagName("style");for(u=0;u<s.length;u++){l=s[u],h=l.getAttribute("data-href");if(h===t||h===i)return a()}var p=document.createElement("link");p.rel="stylesheet",p.type="text/css",p.onload=a,p.onerror=function(a){var t=a&&a.target&&a.target.src||i,r=new Error("Loading CSS chunk "+e+" failed.\n("+t+")");r.code="CSS_CHUNK_LOAD_FAILED",r.request=t,delete o[e],p.parentNode.removeChild(p),n(r)},p.href=i;var d=document.getElementsByTagName("head")[0];d.appendChild(p)})).then((function(){o[e]=0})));var t=i[e];if(0!==t)if(t)a.push(t[2]);else{var r=new Promise((function(a,n){t=i[e]=[a,n]}));a.push(t[2]=r);var l,h=document.createElement("script");h.charset="utf-8",h.timeout=120,c.nc&&h.setAttribute("nonce",c.nc),h.src=u(e);var s=new Error;l=function(a){h.onerror=h.onload=null,clearTimeout(p);var n=i[e];if(0!==n){if(n){var t=a&&("load"===a.type?"missing":a.type),o=a&&a.target&&a.target.src;s.message="Loading chunk "+e+" failed.\n("+t+": "+o+")",s.name="ChunkLoadError",s.type=t,s.request=o,n[1](s)}i[e]=void 0}};var p=setTimeout((function(){l({type:"timeout",target:h})}),12e4);h.onerror=h.onload=l,document.head.appendChild(h)}return Promise.all(a)},c.m=e,c.c=t,c.d=function(e,a,n){c.o(e,a)||Object.defineProperty(e,a,{enumerable:!0,get:n})},c.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},c.t=function(e,a){if(1&a&&(e=c(e)),8&a)return e;if(4&a&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(c.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&a&&"string"!=typeof e)for(var t in e)c.d(n,t,function(a){return e[a]}.bind(null,t));return n},c.n=function(e){var a=e&&e.__esModule?function(){return e["default"]}:function(){return e};return c.d(a,"a",a),a},c.o=function(e,a){return Object.prototype.hasOwnProperty.call(e,a)},c.p="/",c.oe=function(e){throw console.error(e),e};var l=window["webpackJsonp"]=window["webpackJsonp"]||[],h=l.push.bind(l);l.push=a,l=l.slice();for(var s=0;s<l.length;s++)a(l[s]);var p=h;r.push([0,"chunk-vendors"]),n()})({0:function(e,a,n){e.exports=n("56d7")},"1f55":function(e,a,n){},"56d7":function(e,a,n){"use strict";n.r(a);var t=n("2b0e"),o=function(){var e=this,a=e.$createElement,n=e._self._c||a;return n("v-app",{directives:[{name:"resize",rawName:"v-resize",value:e.onResize,expression:"onResize"}]},[n("confirm-dialog"),n("router-view")],1)},i=[],r=function(){var e=this,a=e.$createElement,n=e._self._c||a;return n("transition",{attrs:{name:"fade"}},[e.isShow?n("div",{staticClass:"vc-overlay",attrs:{id:"vueConfirm"},on:{click:e.handleClickOverlay}},[n("transition",{attrs:{name:"zoom"}},[e.isShow?n("div",{ref:"vueConfirmDialog",staticClass:"vc-container"},[n("span",{staticClass:"vc-text-grid"},[e.dialog.title?n("h4",{staticClass:"vc-title",domProps:{innerHTML:e._s(e.dialog.title)}}):e._e(),e.dialog.message?n("p",{staticClass:"vc-text",domProps:{innerHTML:e._s(e.dialog.message)}}):e._e(),e.dialog.auth?n("span",[n("input",{directives:[{name:"focus",rawName:"v-focus"},{name:"model",rawName:"v-model",value:e.password,expression:"password"}],staticClass:"vc-input",attrs:{type:"password",name:"vc-password",placeholder:"Password",autocomplete:"off"},domProps:{value:e.password},on:{keyup:function(a){return a.type.indexOf("key")||13===a.keyCode?function(a){return e.handleClickButton(a,!0)}.apply(null,arguments):null},input:function(a){a.target.composing||(e.password=a.target.value)}}})]):e._e()]),n("div",{staticClass:"vc-btn-grid",class:{isMono:!e.dialog.button.no||!e.dialog.button.yes}},[e.dialog.button.no?n("button",{staticClass:"vc-btn left",attrs:{type:"button"},on:{click:function(a){return a.stopPropagation(),function(a){return e.handleClickButton(a,!1)}.apply(null,arguments)}}},[e._v(" "+e._s(e.dialog.button.no)+" ")]):e._e(),e.dialog.button.yes?n("button",{staticClass:"vc-btn",attrs:{disabled:!!e.dialog.auth&&!e.password,type:"button"},on:{click:function(a){return a.stopPropagation(),function(a){return e.handleClickButton(a,!0)}.apply(null,arguments)}}},[e._v(" "+e._s(e.dialog.button.yes)+" ")]):e._e()])]):e._e()])],1):e._e()])},u=[];const c={name:"VueConfirmDialog",data(){return{password:null}},props:{params:{type:Object,default(){return{}}}},methods:{handleClickButton({target:e},a){a?this.dialog.callback():this.$store.commit("SET_CONFIG_CONFIRM_DIALOG",{auth:!1,title:"",message:"",button:{},callback:()=>{}}),this.$store.commit("SET_SHOWCONFIRM",!1)},handleClickOverlay({target:e}){}},computed:{isShow(){return this.$store.getters.getIsShowConfirm},dialog(){return this.$store.getters.getDialogConfirm}},mounted(){},beforeDestroy(){}};var l=c,h=l,s=(n("880d"),n("2877")),p=Object(s["a"])(h,r,u,!1,null,null,null),d=p.exports,m={name:"App",components:{"confirm-dialog":d},data:()=>({}),created(){this.$cookies.get("Token")||window.location.href.indexOf("/dang-ky")||window.location.href.indexOf("/dang-nhap")||this.$router.push({path:"/"})},computed:{},methods:{onResize:function(){let e=this.$vuetify.breakpoint.name;this.$store.commit("SET_BREAKPOINTNAME",e)}}},g=m,f=n("6544"),T=n.n(f),y=n("7496"),C=n("269a"),v=n.n(C),k=n("dc22"),b=Object(s["a"])(g,o,i,!1,null,null,null),_=b.exports;T()(b,{VApp:y["a"]}),v()(b,{Resize:k["a"]});var S=n("f309");n("1f55");t["a"].use(S["a"]);var D=new S["a"]({theme:{themes:{light:{primary:"#2161B1"}}}}),B=n("8c4f");t["a"].use(B["a"]);var N=new B["a"]({mode:"hash",base:"/",routes:[{path:"/",component:()=>Promise.all([n.e("chunk-7fc83f64"),n.e("chunk-0943ce22"),n.e("chunk-44f76a2d")]).then(n.bind(null,"bb51")),children:[{path:"/",name:"DashBoard",component:()=>Promise.all([n.e("chunk-7fc83f64"),n.e("chunk-41883a9e"),n.e("chunk-0943ce22"),n.e("chunk-59bef27d"),n.e("chunk-adb45e24")]).then(n.bind(null,"7277")),props:!0},{path:"/cuoc-thi/:id",name:"ChiTietCuocThi",component:()=>Promise.all([n.e("chunk-7fc83f64"),n.e("chunk-41883a9e"),n.e("chunk-0943ce22"),n.e("chunk-59bef27d"),n.e("chunk-5734273a")]).then(n.bind(null,"4dd0")),props:!0},{path:"/dang-ky/:id",name:"DangKyThi",component:()=>Promise.all([n.e("chunk-7fc83f64"),n.e("chunk-41883a9e"),n.e("chunk-0943ce22"),n.e("chunk-59bef27d"),n.e("chunk-3c310826")]).then(n.bind(null,"f40c")),props:!0}]},{name:"Login",path:"/dang-nhap",component:()=>Promise.all([n.e("chunk-7fc83f64"),n.e("chunk-41883a9e"),n.e("chunk-0e2c76d8")]).then(n.bind(null,"a55b"))},{name:"DangKy",path:"/dang-ky",component:()=>Promise.all([n.e("chunk-7fc83f64"),n.e("chunk-41883a9e"),n.e("chunk-77a1d69a")]).then(n.bind(null,"cea3"))}]}),P=n("2f62"),M=n("bc3a"),w=n.n(M),E=n("1157"),x=n.n(E);t["a"].use(P["a"]);var A=new P["a"].Store({state:{indexTab:0,apiSso:"http://103.9.211.82:8080",drawer:!1,breakpointName:"lg",isShowConfirm:!1,dialogConfirm:{auth:!1,title:"",message:"",button:{},callback:()=>{}},formData:"",formThongKe:""},getters:{getIndexTab:e=>e.indexTab,getIsSigned:e=>e.isSigned,getBreakpointName:e=>e.breakpointName,getIsShowConfirm:e=>e.isShowConfirm,getDialogConfirm:e=>e.dialogConfirm,getFormData:e=>e.formData,getFormThongKe:e=>e.formThongKe},mutations:{SET_INDEXTAB(e,a){e.indexTab=a},SET_ISSIGNED(e,a){e.isSigned=a},SET_DRAWER(e,a){e.drawer=a},SET_BREAKPOINTNAME(e,a){e.breakpointName=a},SET_SHOWCONFIRM(e,a){e.isShowConfirm=a},SET_CONFIG_CONFIRM_DIALOG(e,a){e.dialogConfirm=a},SET_FORM_DATA(e,a){e.formData=a},SET_FORM_THONGKE(e,a){e.formThongKe=a}},actions:{collectionCreate({commit:e,state:a},n){return new Promise((e,a)=>{let t=JSON.stringify(n.data),o={method:"post",url:"/api/"+n.collectionName,headers:{Accept:"application/json","Content-Type":"application/json"},data:t};w()(o).then((function(a){let n=a.data;e(n)})).catch((function(e){a(e.response)}))})},collectionCreateChild({commit:e,state:a},n){return new Promise((e,a)=>{let t=JSON.stringify(n.data),o={method:"post",url:"/api/"+n.collectionName+"/"+n.collectionId+"/"+n.collectionChildName,headers:{Accept:"application/json","Content-Type":"application/json"},data:t};w()(o).then((function(a){let n=a.data;e(n)})).catch((function(e){a(e.response)}))})},collectionUpdate({commit:e,state:a},n){return new Promise((e,a)=>{let t=JSON.stringify(n.data),o={method:"put",url:"/api/"+n.collectionName+"/"+n.id,headers:{Accept:"application/json","Content-Type":"application/json"},data:t};w()(o).then((function(a){let n=a.data;e(n)})).catch((function(e){a(e)}))})},collectionDelete({commit:e,state:a},n){return new Promise((e,a)=>{let t={method:"delete",url:"/api/"+n.collectionName+"/"+n.id,headers:{Accept:"application/json","Content-Type":"application/json"},data:{}};w()(t).then((function(a){let n=a.data;e(n)})).catch((function(e){a(e)}))})},collectionFilter({commit:e,state:a},n){return new Promise((e,a)=>{let t={method:"get",url:"/api/"+n.collectionName,headers:{Accept:"application/json","Content-Type":"application/json"},data:{},params:n.data};w()(t).then((function(a){let n=a.data;e(n)})).catch((function(e){a(e)}))})},collectionFilterLevel2({commit:e,state:a},n){return new Promise((e,a)=>{let t={method:"get",url:"/api/"+n.collectionName+"/"+n.collectionId+"/"+n.collectionNameChild,headers:{Accept:"application/json","Content-Type":"application/json"},data:{},params:n.data};w()(t).then((function(a){let n=a.data;e(n)})).catch((function(e){a(e)}))})},collectionFilterLevel3({commit:e,state:a},n){return new Promise((e,a)=>{let t={method:"get",url:"/api/"+n.collectionName+"/"+n.collectionId+"/"+n.collectionNameChild+"/"+n.collectionChildId+"/"+n.collectionNameChild2,headers:{Accept:"application/json","Content-Type":"application/json"},data:{},params:n.data};w()(t).then((function(a){let n=a.data;e(n)})).catch((function(e){a(e)}))})},collectionDetail({commit:e,state:a},n){return new Promise((e,a)=>{let t={method:"get",url:"/api/"+n.collectionName+"/"+n.id,data:{},headers:{Accept:"application/json","Content-Type":"application/json"}};w()(t).then((function(a){let n=a.data;e(n)})).catch((function(e){a(e)}))})},collectionDetailSearch({commit:e,state:a},n){return new Promise((e,a)=>{let t={method:"get",url:"/api/"+n.collectionName+"/"+n.keySearch+"/"+n.id,data:{},headers:{Accept:"application/json","Content-Type":"application/json"}};w()(t).then((function(a){let n=a.data;e(n)})).catch((function(e){a(e)}))})},login({commit:e,state:a},n){return new Promise((e,t)=>{let o={url:a.apiSso+"/api/authenticate",method:"POST",headers:{Accept:"application/json","Content-Type":"application/json"},data:JSON.stringify(n.data)};x.a.ajax(o).done((function(a){let n=a;e(n)})).fail((function(e){t(e)}))})},getThongTinUserDangNhap({commit:e,state:a},n){return new Promise((e,t)=>{let o={method:"get",url:a.apiSso+"/api/taikhoans/"+n.email,headers:{Accept:"application/json","Content-Type":"application/json",Authorization:n.token},data:{},params:{}};x.a.ajax(o).done((function(a){let n=a;e(n)})).fail((function(e){t(e)}))})},signUp({commit:e,state:a},n){return new Promise((e,t)=>{let o={url:a.apiSso+"/api/auth/register",method:"POST",headers:{Accept:"application/json","Content-Type":"application/json"},data:JSON.stringify(n.data)};x.a.ajax(o).done((function(a){let n=a;e(n)})).fail((function(e){t(e)}))})},activeTaiKhoan({commit:e,state:a},n){return new Promise((e,t)=>{let o={url:a.apiSso+"/api/auth/"+n.email+"/verify-email?maKichHoat="+n.maKichHoat,method:"PUT",headers:{Accept:"application/json","Content-Type":"application/json"},data:JSON.stringify({})};x.a.ajax(o).done((function(a){let n=a;e(n)})).fail((function(e){t(e)}))})}}}),L=n("2b27"),O=n.n(L),I=n("854a"),j=n.n(I),H=(n("d304"),n("f962")),V=n("cacf"),Q=n.n(V);j.a.options={closeButton:!0,timeOut:"5000",positionClass:"toast-top-center"},t["a"].use(O.a),t["a"].use(Q.a),t["a"].config.productionTip=!1;const G="http://103.9.211.82:8080";"undefined"!==typeof G&&(w.a.defaults.baseURL=G),w.a.interceptors.response.use(e=>e,e=>{if(401==e.response.status&&(A.commit("SET_ISSIGNED",""),t["a"].$cookies.set("Token",""),t["a"].$cookies.get("RefreshToken"))){let e={code:t["a"].$cookies.get("RefreshToken"),redirect_uri:Object({NODE_ENV:"production",VUE_APP_BASE_COLOR:"#2161B1",VUE_APP_BASE_TITLE:"Hệ thống thông tin - IODEV",VUE_APP_IMG_BANNER:"/images/bg-banner.png",VUE_APP_IMG_BG_LOGIN:"/images/bg-login.png",VUE_APP_PATH_API:"http://103.9.211.82:8080",VUE_APP_PATH_API_SSO:"http://103.9.211.82:8080",VUE_APP_PULIC_PATH:"",VUE_APP_TITLE_LOGIN:"",BASE_URL:"/"}).VUE_APP_PATH_REDIRECT_SSO};A.dispatch("getRefreshTokenKeyCloak",e).then(e=>{t["a"].$cookies.set("Token",e.accessToken,e.expires_in),t["a"].$cookies.set("RefreshToken",e.refreshToken,e.refresh_expires_in),w.a.defaults.headers["Authorization"]="Bearer "+e.accessToken,j.a.remove(),j.a.error("Thao tác thất bại. Vui lòng thử lại.")}).catch((function(){}))}return e.response?parseError(e.response.data):Promise.reject(e)}),t["a"].$cookies.get("Token")?(A.commit("SET_ISSIGNED",!0),w.a.defaults.headers["Authorization"]="Bearer "+t["a"].$cookies.get("Token")):(A.commit("SET_ISSIGNED",!1),localStorage.setItem("user",null)),t["a"].mixin({data:()=>({dataLocal:H}),methods:{checkRole(e){let a=this,n=a.$cookies.get("Roles","");if(!e||!n)return!1;let t=n.split(","),o=t.find((function(a){return a==e}));return!!o}},computed:{groupId(){return this.$cookies.get("groupId","")},userId(){return this.$cookies.get("userId","")},isAdmin(){return this.$cookies.get("admin","")},userLogin(){let e=this,a="";try{a=e.$cookies.get("UserInfo","")}catch(n){}return a},isMobile(){let e=this.$store.getters.getBreakpointName;return"xs"===e||"sm"===e}}}),new t["a"]({vuetify:D,router:N,store:A,render:e=>e(_)}).$mount("#app")},"880d":function(e,a,n){"use strict";n("eb19")},eb19:function(e,a,n){},f962:function(e){e.exports=JSON.parse('{"danhSachBaoCao":[{"maDinhDanh":"231241233","maBaoCao":"BC-983123","tenBaoCao":"Số lượng văn bản quy phạm pháp luật liên quan đến thích ứng biển đổi khí hậu được xây dựng và ban hành","quyTrinh":{"@type":"T_QuyTrinh","maQuyTrinh":"QT-001"},"mauBaoCao":{"@type":"T_MauBaoCao","maDinhDanh":"","maMauBaoCao":"MAU_001","nhapNhieuThanhPhan":1},"kyBaoCao":{"@type":"C_KyBaoCao","maMuc":"1","tenMuc":"Theo tháng"},"hanXuLy":"02-09-2022","coQuanThucHien":{"@type":"T_CoQuanDonVi","maHanhChinh":"STNMT","tenGoi":"Sở Tài nguyên và môi trường"},"tinhTrang":{"@type":"C_TrangThaiXuLy","tenMuc":"Đang xử lý","maMuc":"1"},"thoiGianTao":"12-08-2022","thoiGianCapNhat":"","nguoiTaoLap":{"@type":"T_DanhTinhDienTu","tenDinhDanh":"","maSoID":""}},{"maDinhDanh":"23123","maBaoCao":"BC-154312","tenBaoCao":"Số lượng văn bản quy phạm pháp luật liên quan đến thích ứng biển đổi khí hậu được xây dựng và ban hành","quyTrinh":{"@type":"T_QuyTrinh","maQuyTrinh":"QT-002"},"mauBaoCao":{"@type":"T_MauBaoCao","maDinhDanh":"","maMauBaoCao":"MAU_002","nhapNhieuThanhPhan":1},"kyBaoCao":{"@type":"C_KyBaoCao","maMuc":"1","tenMuc":"Theo tuần"},"hanXuLy":"08-08-2022","coQuanThucHien":{"@type":"T_CoQuanDonVi","maHanhChinh":"STTTT","tenGoi":"Sở Thông tin và truyền thông"},"tinhTrang":{"@type":"C_TrangThaiXuLy","tenMuc":"Chờ duyệt","maMuc":"2"},"thoiGianTao":"01-08-2022","thoiGianCapNhat":"","nguoiTaoLap":{"@type":"T_DanhTinhDienTu","tenDinhDanh":"","maSoID":""}}],"danhSachThanhPhan":[{"maDinhDanh":"231241233","maBaoCao":"BC-983123","tenBaoCao":"Số lượng văn bản quy phạm pháp luật liên quan đến thích ứng biển đổi khí hậu được xây dựng và ban hành","quyTrinh":{"@type":"T_QuyTrinh","maQuyTrinh":"QT-001"},"noiDung":{"loaiVanBan":{"name":"Quyết định","value":"1"},"hienTrang":[{"name":"Dự thảo","value":"1"},{"name":"Đã ban hành","value":"2"}],"coQuanBanHanh":[{"name":"Sở thông tin và truyền thông","value":"1"}],"ngayBanHanh":"2022-11-11T00:00:00.000Z","tenVanBan":"Tôi là tên văn bản","tomTatNoiDung":"Tôi là tên tóm tắt nội dung","tinhTrang":{"tenMuc":"Đang xử lý","maMuc":"1"}},"mauBaoCao":{"@type":"T_MauBaoCao","maDinhDanh":"","maMauBaoCao":"MAU_001","nhapNhieuThanhPhan":1},"kyBaoCao":{"@type":"C_KyBaoCao","maMuc":"1","tenMuc":"Theo tháng"},"hanXuLy":"02-09-2022","coQuanThucHien":{"@type":"T_CoQuanDonVi","maHanhChinh":"STNMT","tenGoi":"Sở Tài nguyên và môi trường"},"tinhTrang":{"@type":"C_TrangThaiXuLy","tenMuc":"Đang xử lý","maMuc":"1"},"thoiGianTao":"12-08-2022","thoiGianCapNhat":"","nguoiTaoLap":{"@type":"T_DanhTinhDienTu","tenDinhDanh":"","maSoID":""}},{"maDinhDanh":"23123","maBaoCao":"BC-154312","tenBaoCao":"Số lượng văn bản quy phạm pháp luật liên quan đến thích ứng biển đổi khí hậu được xây dựng và ban hành","quyTrinh":{"@type":"T_QuyTrinh","maQuyTrinh":"QT-002"},"mauBaoCao":{"@type":"T_MauBaoCao","maDinhDanh":"","maMauBaoCao":"MAU_002","nhapNhieuThanhPhan":1},"noiDung":{"loaiVanBan":{"name":"Quyết định","value":"1"},"hienTrang":[{"name":"Dự thảo","value":"1"},{"name":"Đã ban hành","value":"2"}],"coQuanBanHanh":[{"name":"Sở thông tin và truyền thông","value":"1"}],"ngayBanHanh":"2022-11-11T00:00:00.000Z","tenVanBan":"Tôi là tên văn bản","tomTatNoiDung":"Tôi là tên tóm tắt nội dung","tinhTrang":{"tenMuc":"Đang xử lý","maMuc":"1"}},"kyBaoCao":{"@type":"C_KyBaoCao","maMuc":"1","tenMuc":"Theo tuần"},"hanXuLy":"08-08-2022","coQuanThucHien":{"@type":"T_CoQuanDonVi","maHanhChinh":"STTTT","tenGoi":"Sở Thông tin và truyền thông"},"tinhTrang":{"@type":"C_TrangThaiXuLy","tenMuc":"Chờ duyệt","maMuc":"2"},"thoiGianTao":"01-08-2022","thoiGianCapNhat":"","nguoiTaoLap":{"@type":"T_DanhTinhDienTu","tenDinhDanh":"","maSoID":""}}],"chiTietBaoCao":{"maDinhDanh":"231241233","maBaoCao":"BC-983123","tenBaoCao":"Số lượng văn bản quy phạm pháp luật liên quan đến thích ứng biển đổi khí hậu được xây dựng và ban hành","quyTrinh":{"@type":"T_QuyTrinh","maQuyTrinh":"QT-001"},"mauBaoCao":{"@type":"T_MauBaoCao","maDinhDanh":"","maMauBaoCao":"MAU_001","nhapNhieuThanhPhan":1},"kyBaoCao":{"@type":"C_KyBaoCao","maMuc":"1","tenMuc":"Theo tháng"},"hanXuLy":"02-09-2022","coQuanThucHien":{"@type":"T_CoQuanDonVi","maHanhChinh":"STNMT","tenGoi":"Sở Tài nguyên và môi trường"},"tinhTrang":{"@type":"C_TrangThaiXuLy","tenMuc":"Đang xử lý","maMuc":"1"},"thoiGianTao":"12-08-2022","thoiGianCapNhat":"","nguoiTaoLap":{"@type":"T_DanhTinhDienTu","tenDinhDanh":"","maSoID":""}},"mauBaoCao":{"maDinhDanh":"","tenGoi":"","trangThai":{"@type":"C_TrangThaiDuLieu","maMuc":"","tenMuc":""},"maMauBaoCao":"","mauTaoMaBaoCao":"","quyTrinhXuLy":{"@type":"T_QuyTrinh","maQuyTrinh":""},"nhapNhieuThanhPhan":true,"mauNhap":[{"name":"loaiVanBan","title":"Loại văn bản","type":"select","multiple":false,"itemText":"name","itemValue":"value","minLength":0,"maxLength":0,"required":true,"rules":[],"fieldClass":"col-6","placeHolder":"","defaultValue":"","dataType":"","dataSource":[{"name":"Quyết định","value":"1"},{"name":"Công văn","value":"2"}],"autoEvent":""},{"name":"hienTrang","title":"Hiện trạng","type":"select","multiple":true,"itemText":"name","itemValue":"value","minLength":0,"maxLength":0,"required":false,"rules":"","fieldClass":"col-6","placeHolder":"","defaultValue":"","dataType":"","dataSource":[],"autoEvent":"","api":"http://10.3.16.185:8080/api/danhmuc/1","responseDataApi":"content"},{"name":"coQuanBanHanh","title":"Cơ quan ban hành","type":"select","multiple":true,"itemText":"name","itemValue":"value","minLength":0,"maxLength":0,"required":true,"rules":"","fieldClass":"col-6","placeHolder":"","defaultValue":"","dataType":"","dataSource":[{"name":"Sở thông tin và truyền thông","value":"1"},{"name":"Sở tài nguyên và môi trường","value":"2"}],"autoEvent":""},{"name":"ngayBanHanh","title":"Ngày ban hành","type":"date","minLength":0,"maxLength":0,"required":true,"rules":"","fieldClass":"col-6","placeHolder":"ddmmyyyy, dd/mm/yyyy","defaultValue":"20221108","dataType":"","dataSource":"","autoEvent":""},{"name":"tenVanBan","title":"Tên văn bản","type":"textfield","minLength":0,"maxLength":0,"required":true,"rules":"","fieldClass":"col-12","placeHolder":"","defaultValue":"","dataType":"","dataSource":"","autoEvent":""},{"name":"tomTatNoiDung","title":"Tóm tắt nội dung","type":"textarea","minLength":0,"maxLength":0,"required":true,"rules":"","fieldClass":"col-12","placeHolder":"","defaultValue":"","dataType":"","dataSource":"","autoEvent":""},{"name":"files","title":"Giấy tờ đính kèm","type":"attack","multiple":true,"minLength":0,"maxLength":0,"required":true,"rules":"","fieldClass":"col-12","placeHolder":"","defaultValue":"","dataType":"","dataSource":"","autoEvent":""}],"mauThongKe":{"maMau":"","tenMau":"","groupBy":[{"name":"","title":""}],"orderBy":[{"name":"","type":["asc","desc"]}],"limit":"","query":"","searchHidden":{"@type":"S_Query","where":""},"searchForm":[{"operator":["=","!=",">=","<=","like"],"keySearch":"","keyUrl":{"@type":"S_TruongDuLieu","name":"","title":"","type":{"@type":"C_KieuDuLieu","maMuc":"","tenMuc":""},"minLength":0,"maxLength":0,"required":false,"fieldClass":"","placeHolder":"","defaultValue":"","dataType":"","dataSource":""}}],"listView":[{"@type":"S_DanhSach","title":"","name":"","fieldClass":""}]},"mauHienThi":{"headers":[{"sortable":false,"text":"STT","value":"index","type":"index","class":"td-center","width":"50"},{"sortable":false,"text":"Tên văn bản","value":"tenVanBan","class":"th-center"},{"sortable":false,"text":"Loại văn bản","value":"loaiVanBan","class":"th-center td-center","type":"object","mapping":"name"},{"sortable":false,"text":"Đơn vị thực hiện","value":"coQuanBanHanh","type":"array","mapping":"name","class":"th-center"},{"sortable":false,"text":"Ngày ban hành","value":"ngayBanHanh","class":"th-center td-center","type":"date"},{"sortable":false,"width":"120","text":"Tình trạng","value":"tinhTrang","type":"calculator","calculator":"dataInput[\'tinhTrang\'][\'tenMuc\']","class":"th-center td-center"},{"sortable":false,"text":"Thao tác","value":"action","type":"action","class":"th-center td-center","width":"150"}],"itemsPerPage":15}}}')}});