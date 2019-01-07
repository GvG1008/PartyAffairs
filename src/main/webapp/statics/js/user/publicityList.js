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
var length = 5;//每页显示条数
var list = new Vue({
	el : '#list',
	data: {
		pageLimit: 15,
		currentNum: 1,
		totalPageNum: [],
		datas: [],
		downloadDatas: [],
		pagetoNum :[],
		type: [],
		downloadFalg: 1
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
		typeusl = "../newsMenu/"+currentNum+"/"+length;
		getList(typeusl);
	}
	else if(typename == "tzgs"){
		typeusl = "../noticesMenu/public/"+currentNum+"/"+length;
		getList(typeusl);
	}
	else if(typename == "dngs"){
		typeusl = "../noticesMenu/party/"+currentNum+"/"+length;
		getList(typeusl);
	}
	else if(typename == "xzzq"){
		//typeusl = "../newsMenu/"+currentNum+"/"+length;
		typeusl = "../study/get_study_documents.do?page="+currentNum+"&pageNum="+length;
		getDownloadList(typeusl);
	}
}
function getList(url){
	$.ajax({
		type : "GET",// 请求方式
		url : url,// 地址，就是json文件的请求路径
		dataType : "json",// 数据类型可以为 text xml json script jsonp
		success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
			if (result.status == 0) {
				list.totalPageNum = result.data.totalPageNum;
				list.currentNum = result.data.pageNum;
				list.datas = result.data.list;
				list.type = typename;
				list.downloadFalg = 1;
			} else {
				alert(result.msg);
			}
		}
	});
}
function getDownloadList(url){
	$.ajax({
		type : "post",// 请求方式
		url : url,// 地址，就是json文件的请求路径
		dataType : "json",// 数据类型可以为 text xml json script jsonp
		success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
			if (result.status == 0) {
				list.totalPageNum = result.data.totalPage;
				list.currentNum = result.data.page;
				list.downloadDatas = result.data.list;
				list.type = typename;
				list.downloadFalg = 2;
			} else {
				alert(result.msg);
			}
		}
	});
}