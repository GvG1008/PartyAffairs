/*
 * 取路径参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var typename = getUrlParam('location');

$(document).ready(function() {
	$(function(){
		if(typename == "rp"){
			document.getElementById("rp-title").innerHTML = "思想汇报";
			$("#type").hide();
		}
		else if(typename == "fb"){
			document.getElementById("rp-title").innerHTML = "反馈";
		}
	})
	
    $('#summernote').summernote({
        height:400,
    	lang: 'zh-CN'
  	});
});