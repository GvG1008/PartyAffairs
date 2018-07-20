/*
 * 取路径参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}

$(function(){
	/*
	 * 设置板块名
	 */
	var name = getUrlParam('location');
	if(name == "djyw"){
		document.getElementById("list-title").innerHTML = "党建要闻";
	}
	else if(name == "tzgs"){
		document.getElementById("list-title").innerHTML = "通知公示";
	}
	else if(name == "dngs"){
		document.getElementById("list-title").innerHTML = "党内公示";
	}
	else if(name == "xzzq"){
		document.getElementById("list-title").innerHTML = "下载专区";
	}

})
