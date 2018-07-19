$(document).ready(function(){ 
	/*
	 * 加载首页
	 */
	$(function(){
		$("#frame").load("homeNews.html");
		
		var top = document.getElementById("search-btn").getBoundingClientRect().top;
		top = top-10;
		document.getElementById("search").style.top = top+"px";
	});
	
	/*
	 * 导航栏
	 */
	$("#news").mouseenter(function(){
		var left = document.getElementById("news").getBoundingClientRect().left;
		var tleft = left+25;
		document.getElementById("anews").style.left = tleft+"px";
		$(".news").show();
	});
	$("#anews").mouseenter(function(){
		$(".news").show();
	});
	$("#news").mouseleave(function(){
		$(".news").hide();
	});
	$("#anews").mouseleave(function(){
		$(".news").hide();
	});
	
	$("#party").mouseenter(function(){
		var left = document.getElementById("party").getBoundingClientRect().left;
		var tleft = left+25;
		document.getElementById("aparty").style.left = tleft+"px";
		$(".party").show();
	});
	$("#aparty").mouseenter(function(){
		$(".party").show();
	});
	$("#party").mouseleave(function(){
		$(".party").hide();
	});
	$("#aparty").mouseleave(function(){
		$(".party").hide();
	});
	
	$("#league").mouseenter(function(){
		var left = document.getElementById("league").getBoundingClientRect().left;
		var tleft = left+25;
		document.getElementById("aleague").style.left = tleft+"px";
		$(".league").show();
	});
	$("#aleague").mouseenter(function(){
		$(".league").show();
	});
	$("#league").mouseleave(function(){
		$(".league").hide();
	});
	$("#aleague").mouseleave(function(){
		$(".league").hide();
	});
	
	$("#meeting").mouseenter(function(){
		var left = document.getElementById("meeting").getBoundingClientRect().left;
		var tleft = left+25;
		document.getElementById("ameeting").style.left = tleft+"px";
		$(".meeting").show();
	});
	$("#ameeting").mouseenter(function(){
		$(".meeting").show();
	});
	$("#meeting").mouseleave(function(){
		$(".meeting").hide();
	});
	$("#ameeting").mouseleave(function(){
		$(".meeting").hide();
	});
	
	$("#organ").mouseenter(function(){
		var left = document.getElementById("organ").getBoundingClientRect().left;
		var tleft = left+25;
		document.getElementById("aorgan").style.left = tleft+"px";
		$(".organ").show();
	});
	$("#aorgan").mouseenter(function(){
		$(".organ").show();
	});
	$("#organ").mouseleave(function(){
		$(".organ").hide();
	});
	$("#aorgan").mouseleave(function(){
		$(".organ").hide();
	});
	
	$("#info").mouseenter(function(){
		var left = document.getElementById("info").getBoundingClientRect().left;
		document.getElementById("ainfo").style.left = left+"px";
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
	});
	
	/*
	 * 回车搜索
	 */
	$("#search").keydown(function(e){
		var keyCode =window.event? e.keyCode:e.which;
		if(keyCode == 13){
			$("#search-btn").trigger("click");
		}
	});
	
	/*
	 * 登录窗口
	 */
	$("#login").click(function(event){
		var width = document.body.clientWidth;
		var height = document.body.scrollHeight;
		var left = (width-400)/2;
		var top = (height-400)/2;
		document.getElementById("alogin").style.left=left+"px";
		document.getElementById("alogin").style.top=top+"px";
		document.getElementById("laside").style.height=height+"px";
		$("#laside").fadeIn(500);
	});
	
	/*
	 * 修改密码窗口
	 */
	$("#change").click(function(event){
		var width = document.body.clientWidth;
		var height = document.body.scrollHeight;
		var left = (width-400)/2;
		var top = (height-400)/2;
		document.getElementById("achange").style.left=left+"px";
		document.getElementById("achange").style.top=top+"px";
		document.getElementById("caside").style.height=height+"px";
		$("#caside").fadeIn(500);
	});
	
	$(".content-button").click(function(){
		$("#laside").fadeOut(500);
		$("#caside").fadeOut(500);
	});
	
	/*
	 * 跳转
	 */
	$("#party-news").click(function(){
		$("#frame").load("news.html");
	});
	
})
