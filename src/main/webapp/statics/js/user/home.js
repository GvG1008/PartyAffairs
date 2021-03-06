/*
 * 取路径参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}

$(document).ready(function(){
	/*
	 * 跳转
	 */
	$(function(){
		var name=getUrlParam('location');
		var id=getUrlParam('id');
		if(name==null){
			$("#frame").load("homeNews.html");
		}
		else if(name=="djyw"||name=="tzgs"||name=="dngs"||name=="xzzq"||name=="sxhb"){
			if(id==null){
				$("#frame").load("publicityList.html");
			}
			else{
				$("#frame").load("publicity.html");
			}
		}
		else if(name=="meeting"){
            if(id==null){
                $("#frame").load("meetingList.html");
            }else{
               $("#frame").load("meetingInfo.html");
            }
		}
		else if(name=="test"){
			$("#frame").load("examList.html");
		}
		else if(name=="activity"){
			if(id==null){
				$("#frame").load("activityList.html");
			}
			else{
				$("#frame").load("activity.html");
			}
		}
		else if(name=="info"){
			$("#frame").load("personal.html");
		}
		else if(name=="fb"||name=="rp"){
			$("#frame").load("report.html");
		}
		else if(name=="learn"){
			$("#frame").load("learn.html");
		}
		else if(name=="coll"){
			$("#frame").load("collection.html");
		}
		else if(name=="video"){
			$("#frame").load("videolearn.html");
		}
		else if(name=="docu"){
			$("#frame").load("documentlearn.html");
		}
		else if(name=="record"){
			$("#frame").load("record.html");
		}
		else if(name == "myvote"){
			$("#frame").load("myVote.html");
		}
		else if(name == "votepage"){
			$("#frame").load("votePage.html");
		}
		
		$(".loading").fadeOut(500);
	});
	
	/*
	 * 导航栏
	 */
	$("#news").mouseenter(function(){
		var left = document.getElementById("news").getBoundingClientRect().left;
//		var tleft = left+25;
		var tleft = left-22;
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
	
//	$("#info").mouseenter(function(){
//		var left = document.getElementById("info").getBoundingClientRect().left;
//		var top = document.getElementById("info").getBoundingClientRect().top;
//		document.getElementById("ainfo").style.left = left-2+"px";
//		document.getElementById("ainfo").style.top = top+55+"px";
//		$(".info").show();
//	});
//	$("#ainfo").mouseenter(function(){
//		$(".info").show();
//	});
//	$("#info").mouseleave(function(){
//		$(".info").hide();
//	});
//	$("#ainfo").mouseleave(function(){
//		$(".info").hide();
//	});
//	
//	$("#manager").mouseenter(function(){
//		var left = document.getElementById("manager").getBoundingClientRect().left;
//		var top = document.getElementById("manager").getBoundingClientRect().top;
//		document.getElementById("amanager").style.left = left+-2+"px";
//		document.getElementById("amanager").style.top = top+55+"px";
//		$(".manager").show();
//	});
//	$("#amanager").mouseenter(function(){
//		$(".manager").show();
//	});
//	$("#manager").mouseleave(function(){
//		$(".manager").hide();
//	});
//	$("#amanager").mouseleave(function(){
//		$(".manager").hide();
//	});
	
	$("#party").mouseenter(function(){
		var left = document.getElementById("party").getBoundingClientRect().left;
//		var tleft = left+25;
		var tleft = left-22;
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
//		var tleft = left+25;
		var tleft = left-22;
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
	
	$("#organ").mouseenter(function(){
		var left = document.getElementById("organ").getBoundingClientRect().left;
//		var tleft = left+25;
		var tleft = left-22;
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
	
	
	
	/*
	 * 登录窗口
	 */
	$("#login").click(function(event){
		var width = document.body.clientWidth;
		var height = document.body.scrollHeight;
		var aheight = document.body.clientHeight;
		var left = (width-400)/2;
		var top = (aheight-400)/2;
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
		var aheight = document.body.clientHeight;
		var left = (width-400)/2;
		var top = (aheight-400)/2;
		document.getElementById("achange").style.left=left+"px";
		document.getElementById("achange").style.top=top+"px";
		document.getElementById("caside").style.height=height+"px";
		$("#caside").fadeIn(500);
	});
	
	$(".content-button").click(function(){
		$("#laside").fadeOut(500);
		$("#caside").fadeOut(500);
	});
	
})
var head = new Vue({
	el : '#head',
	data :{
		"user":[],
		"username":null,
		"password":null,
		"permission":0,
		"realName":""
	},
	methods:{
		Login : function(){
			doLogin();
		},
		search(){
			alert("待开发");
		},
		changepwd:function(){
			changepwd();
		}
	},
	created: function(){
		var self = this;
		$.ajax({
			type:"GET",
			url: "../loginInfo",
			async:true,
			dataType: "json",
			success: function(result) { 
				//console.log(result)				
				if (result.status == 0){
					self.realName = result.data.realName;
					self.user = result.data;
				}
			}			
		});
		$.ajax({
			type:"get",
			url:"../adminManager/checkAdmin",
			async:true,
			success:function(res){
				if(res.status == 0){
					self.permission = res.data;
				}
			}
		});
	}
})
function doLogin(){
	$.ajax({
		type:"post",
		url:"../login",
		data: {"userId":head.username,"password":head.password},
		dataType: "json", // 数据类型可以为 text xml json script jsonp
		success: function(result) { 
			if(result.status==0){
				//alert(result.msg);
				head.user = result.data;
				location.reload();				
			}else{
				alert(result.msg);
			}
		}
	});
}

function changepwd(){
	var old_password = $("#oldpass").val();
	var newpass = $("#newpass").val();
	var repass = $("#repass").val();
	if(newpass == repass){
		$.ajax({
			type:"post",
			url:"../userInfo/update_mine_password",
			data:{
				old_password:old_password,
				new_password:repass
			},
			async:true,
			success:function(res){
				console.log(res)
				if(res.status == 1){
					alert(res.msg)
				}else if(res.status == 0){
					$("#laside").fadeOut(500);
					$("#caside").fadeOut(500);
					alert("修改成功，请重新登录")
					location.href="../doLogout"
				}
			}
		});
	}else{
		alert("请确认新密码")
	}
}