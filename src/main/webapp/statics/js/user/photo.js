/*
 * 取路径参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}

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
	})
})
