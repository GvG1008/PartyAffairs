/**
 * 新闻公告详情页
 */
/*
 * 取路径参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var typename=getUrlParam('location');//新闻公告类型
var id=getUrlParam('id');//新闻公告id

var publicity = new Vue({
	el : '#publicity',
	data :{
		datas:[]
	},
	created	: function(){
		getDetails();
	},
	methods	:{
		updateMessage: function (){
			var self = this;
			this.$nextTick(function () {
				document.getElementById("details").innerHTML = publicity.datas.content; 				
              })
		}
	}
})
function getDetails(){//获取详情
	if(typename == "djyw"){
		typeusl = "../news/"+id;
	}
	else if(typename == "tzgs"){
		typeusl = "../notices/public/"+id;
	}
	else if(typename == "dngs"){
		typeusl = "../notices/party/"+id;
	}
	else if(typename == "xzzq"){
		typeusl = "../news/"+id;
	}
	else if(typename == "sxhb"){
		typeusl = "../report/detail/"+id;
	}
	$.ajax({
		type : "GET",// 请求方式
		url : typeusl,// 地址，就是json文件的请求路径
		dataType : "json",// 数据类型可以为 text xml json script jsonp
		success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
			if (result.status == 0) {
				console.log(result)
				publicity.datas = result.data;
				publicity.updateMessage();
			} else {
				alert(result.msg);
			}
		}
	});
}