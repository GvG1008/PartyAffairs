$(document).ready(function(){
	$("#video").mouseenter(function(){
		document.getElementById("video").style.backgroundColor = "#E6E7EC";
		document.getElementById("video").style.color = "red";
		document.getElementById("docu").style.backgroundColor = "#FBFBFB";
		document.getElementById("docu").style.color = "#3365DA";
		$("#docu-record").hide();
		$("#video-record").show();
	})
	
	$("#docu").mouseenter(function(){
		document.getElementById("video").style.backgroundColor = "#FBFBFB";
		document.getElementById("video").style.color = "#3365DA";
		document.getElementById("docu").style.backgroundColor = "#E6E7EC";
		document.getElementById("docu").style.color = "red";
		$("#docu-record").show();
		$("#video-record").hide();
	})
})
