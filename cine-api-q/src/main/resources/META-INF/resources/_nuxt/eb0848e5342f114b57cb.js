(window.webpackJsonp=window.webpackJsonp||[]).push([[4],{255:function(t,e,o){var content=o(260);"string"==typeof content&&(content=[[t.i,content,""]]),content.locals&&(t.exports=content.locals);(0,o(11).default)("f492022a",content,!0,{sourceMap:!1})},259:function(t,e,o){"use strict";var r=o(255);o.n(r).a},260:function(t,e,o){(t.exports=o(10)(!1)).push([t.i,".fundo[data-v-acc419ae]{width:100%;background-size:cover;background-repeat:no-repeat;background-position:50% 50%;padding:0}html[data-v-acc419ae]{font-size:16px}.movie-title[data-v-acc419ae]{font-size:45px;font-weight:700}.sub-title[data-v-acc419ae]{font-size:18px;color:#777}.rated[data-v-acc419ae]{padding:5px;background-color:#777;color:#000;margin-left:5px;border-radius:5px}.back[data-v-acc419ae]{text-decoration:none}.elevation-white-24[data-v-acc419ae]{box-shadow:0 11px 15px -7px hsla(0,0%,100%,.2),0 24px 38px 3px hsla(0,0%,100%,.14),0 9px 46px 8px hsla(0,0%,100%,.12)!important}",""])},291:function(t,e,o){"use strict";o.r(e);var r=o(110),n=o.n(r),c=(o(254),{data:function(){return{loading:!0,movie:null}},mounted:function(){var t=this;n.a.get("http://localhost:8080/movie/"+this.$nuxt._route.params.id+"?append_video_to_response=true").then(function(e){t.movie=e.data,t.loading=!1}).catch(function(t){console.log(t)})},computed:{posterPath:function(){return _.isNil(this.movie.poster_path)?"https://via.placeholder.com/342x513":"https://image.tmdb.org/t/p/w342"+this.movie.poster_path},backdrop:function(){return _.isNil(this.movie.backdrop_path)?"":"backgroundImage: url(https://image.tmdb.org/t/p/w1280"+this.movie.backdrop_path+")"}}}),l=(o(259),o(21)),v=o(36),d=o.n(v),m=o(98),h=o(100),f=o(293),x=o(103),y=o(285),k=o(97),w=o(101),component=Object(l.a)(c,function(){var t=this,e=t.$createElement,o=t._self._c||e;return t.loading?o("v-container",[o("div",{staticClass:"text-xs-center"},[o("v-progress-circular",{attrs:{indeterminate:"",size:150,width:8,color:"orange"}})],1)]):o("v-container",{style:t.backdrop,attrs:{"grid-list-xl":"",fluid:"","my-5":"",fundo:""}},[o("v-container",{staticStyle:{"background-color":"#0a1014",opacity:"0.85"},attrs:{fluid:""}},[o("v-container",{staticStyle:{"max-width":"1200px"}},[o("v-layout",{staticStyle:{color:"#fff"},attrs:{"align-center":"","justify-center":"",row:"",wrap:""}},[o("v-flex",{attrs:{sm12:"",md8:""}},[o("n-link",{staticClass:"back",attrs:{title:"Back",to:"/"}},[o("v-icon",{attrs:{medium:"",color:"#777"}},[t._v("keyboard_backspace")])],1),t._v(" "),o("p",[o("span",[t._v(t._s(t.movie.runtime)+" Minutes")]),t._v(" "),o("span",{staticClass:"rated",attrs:{title:"Popularity"}},[t._v(t._s(t.movie.popularity))])]),t._v(" "),o("p",[o("span",{staticClass:"movie-title"},[t._v(t._s(t.movie.title))]),t._v(" "),o("span",[t._v("("+t._s(t._f("date")(t.movie.release_date))+")")])]),t._v(" "),o("div",{staticClass:"mb-4"},[o("p",{staticClass:"sub-title"},[t._v("Overview")]),t._v(" "),o("p",[t._v(t._s(t.movie.overview))])]),t._v(" "),o("v-layout",{staticStyle:{padding:"15px"},attrs:{"justify-space-between":""}},[o("div",[o("p",{staticClass:"sub-title"},[t._v("Genre")]),t._v(" "),t._l(t.movie.genres,function(e){return o("p",{key:e.id},[t._v(t._s(e.name))])})],2),t._v(" "),o("div",[o("p",{staticClass:"sub-title"},[t._v("Vote Average")]),t._v(" "),o("p",[t._v(t._s(t.movie.vote_average))])]),t._v(" "),o("div",[o("p",{staticClass:"sub-title"},[t._v("Vote Count")]),t._v(" "),o("p",[t._v(t._s(t.movie.vote_count))])])])],1),t._v(" "),o("v-flex",{attrs:{sm12:"",md4:""}},[o("v-hover",{scopedSlots:t._u([{key:"default",fn:function(e){var r=e.hover;return o("v-img",{class:r?"elevation-white-24":"",attrs:{"aspect-ratio":16/9,src:t.posterPath,"max-width":"342",height:"513"},scopedSlots:t._u([{key:"placeholder",fn:function(){return[o("v-layout",{attrs:{"fill-height":"","align-center":"","justify-center":"","ma-0":""}},[o("v-progress-circular",{attrs:{indeterminate:"",color:"grey lighten-5"}})],1)]},proxy:!0}])})}}])})],1)],1)],1)],1)],1)},[],!1,null,"acc419ae",null);e.default=component.exports;d()(component,{VContainer:m.a,VFlex:h.a,VHover:f.a,VIcon:x.a,VImg:y.a,VLayout:k.a,VProgressCircular:w.a})}}]);