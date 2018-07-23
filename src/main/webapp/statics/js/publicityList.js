/*
 * 取路径参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var typename = getUrlParam('location');
var typeusl;
$(function(){
	/*
	 * 设置板块名
	 */
	if(typename == "djyw"){
		document.getElementById("list-title").innerHTML = "党建要闻";
	}
	else if(typename == "tzgs"){
		document.getElementById("list-title").innerHTML = "通知公示";
	}
	else if(typename == "dngs"){
		document.getElementById("list-title").innerHTML = "党内公示";
	}
	else if(typename == "xzzq"){
		document.getElementById("list-title").innerHTML = "下载专区";
	}

})

var list = new Vue({
	el : '#list',
	data: {
		pageLimit: 15,
		currentNum: 1,
		totalPageNum: [],
		datas: [],
		pagetoNum :[],
	},
	created : function() {
		doPageto(1);
	},
	methods :{
		pageto : function(currentNum) {
			doPageto(currentNum);
		}
	}
})
function doPageto(currentNum){
	if(typename == "djyw"){
		typeusl = "../newsMenu/"+currentNum;
	}
	else if(typename == "tzgs"){
		typeusl = "../noticesMenu/public/"+currentNum;
	}
	else if(typename == "dngs"){
		typeusl = "../noticesMenu/party/"+currentNum;
	}
	else if(typename == "xzzq"){
		typeusl = "../newsMenu/"+currentNum;
	}
	$.ajax({
		type : "GET",// 请求方式
		url : typeusl,// 地址，就是json文件的请求路径
		dataType : "json",// 数据类型可以为 text xml json script jsonp
		success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
			if (result.status == 0) {
				list.totalPageNum = result.data.totalPageNum;
				list.currentNum = result.data.pageNum;
				list.datas = result.data.list;
			} else {
				alert(result.msg);
			}
		}
	});
}