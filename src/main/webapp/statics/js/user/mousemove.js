/**
 * 
 */
$(document).ready(function(){
/*$("#info").mouseenter(function(){
		var left = document.getElementById("info").getBoundingClientRect().left;
		var top = document.getElementById("info").getBoundingClientRect().top;
		document.getElementById("ainfo").style.left = left-2+"px";
		document.getElementById("ainfo").style.top = top+55+"px";
		$(".info").show();
	});
	$("#ainfo").mouseenter(function(){
		$(".info").show();
	});
	$("#info").mouseleave(function(){
		$(".info").hide();
	});
	$("#ainfo").mouseleave(function(){
		$(".info").hide();
	});*/
	
	/*$("#manager").mouseenter(function(){
		console.log(123456789)
		var left = document.getElementById("manager").getBoundingClientRect().left;
		var top = document.getElementById("manager").getBoundingClientRect().top;
		document.getElementById("amanager").style.left = left+-2+"px";
		document.getElementById("amanager").style.top = top+55+"px";
		$(".manager").show();
	});
	$("#amanager").mouseenter(function(){
		$(".manager").show();
	});
	$("#manager").mouseleave(function(){
		$(".manager").hide();
	});
	$("#amanager").mouseleave(function(){
		$(".manager").hide();
	});*/
})
function showInfo(){
	var left = document.getElementById("info").getBoundingClientRect().left;
	var top = document.getElementById("info").getBoundingClientRect().top;
	document.getElementById("ainfo").style.left = left-2+"px";
	document.getElementById("ainfo").style.top = top+55+"px";
	$(".info").show();
	$(".manager").hide();
}
function hideInfo(){
	$(".info").hide();
}
function showManager(){
	var left = document.getElementById("manager").getBoundingClientRect().left;
	var top = document.getElementById("manager").getBoundingClientRect().top;
	document.getElementById("amanager").style.left = left+-2+"px";
	document.getElementById("amanager").style.top = top+55+"px";
	$(".manager").show();
	$(".info").hide();
}
function hideManager(){
	$(".manager").hide();
}