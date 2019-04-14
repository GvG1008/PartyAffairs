/*
 * 取路径参数
 */
var ftpurl = "http://47.106.122.123:2019";
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var id = getUrlParam('id');
$(document).ready(function(){
	$(function(){
		var cl = getUrlParam('cl');
		if(cl == 1){
			document.body.style.backgroundColor="rgba(56, 182, 230, 0.7)";
		}
		else if(cl == 2){
			document.body.style.backgroundColor="rgba(237, 84, 76, 0.7)";
		}
		else if(cl == 3){
			document.body.style.backgroundColor="rgba(234, 163, 25, 0.7)";
		}
		else if(cl == 4){
			document.body.style.backgroundColor="rgba(22, 181, 163, 0.7)";
		}
		else if(cl == 5){
			document.body.style.backgroundColor="rgba(68, 127, 202, 0.7)";
		}
	})
})
var photo = new Vue({
	el :'#photo',
	data :{
		"photo":[],
		"title":[],
		"description":[]
	},
	created:function(){
		var self = this;
		$.ajax({
			type : "GET",// 请求方式
			url : "../partyalbum/picture/"+id,// 地址，就是json文件的请求路径
			dataType : "json",// 数据类型可以为 text xml json script jsonp
			success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
				if (result.status == 0) {
					self.photo = result.data.pictures;
					self.title = result.data.albumTitle;
					self.description = result.data.description;
				} else {
					alert(result.msg);
				}
			}
		})
	},
	methods:{
		getImageUrl:function(url){
			return ftpurl+url;
		},
		initclass:function(index){
			if(index==0)
				return "carousel-item active";
			else
				return "carousel-item";
		}
	}
})