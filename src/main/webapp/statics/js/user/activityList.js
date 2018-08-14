$(document).ready(function(){
	
	$("#cancal").click(function(){
		$(".sign-up").hide();
	})
	
	/*
	 * 校验手机号码
	 */
	$("#sure-sign").click(function(){
		var check = /^\d{11}$/;
		var phone = document.getElementById("phone").value;
		
		if(!check.test(phone)){
			alert("请输入正确的手机号码!");
		}
		else{
			activity.enroll();
			//location.reload();
		}
	})
	
})

var activity = new Vue({
	el :'#activity-list',
	data :{
		"list":[],
		"info":[],
		"phone":[]
	},
	created :function(){
		var self = this;
		$.ajax({
			type:"get",
			url:"../partyActivity/menu/1/1000",
			dataType: "json", // 数据类型可以为 text xml json script jsonp
			success: function(result) { 
				if(result.status==0)
					self.list = result.data.list;
				else{
					alert(result.msg);
				}
			}
		});
	},
	methods :{
		getInfo :function(id){//获取活动报名详情
			doGetInfo(id);
		},
		enroll :function(){//报名
			doEnroll();
		},
		details :function(activityId){
			doGetInfo(activityId);
			var width = document.body.clientWidth;
			var height = document.body.clientHeight;
			var awidth = document.getElementById("activity-list").clientWidth;
			var aheight = document.getElementById("activity-list").clientHeight;
			var left = (width-500)/2-(width-awidth)/2;
			var top = (height-330)/2-(height-aheight)/2;
			//alert(width+" "+height+" "+left+" "+top+" "+awidth+" "+aheight);
			document.getElementById("asign-up").style.left=left+"px";
			document.getElementById("asign-up").style.top=top+"px";
			$(".sign-up").show();
		}
	}
})

function doGetInfo(id){
	$.ajax({
		type:"GET",
		url: "../partyActivity/info/"+id, 
		dataType: "json",
		success: function(result) { 
			if(result.status == 0)
				activity.info = result.data;
			else
				alert(result.msg);
		}	
	})
}


function doEnroll(){
	$.ajax({
		type:"post",
		url: "../partyActivity/apply", 
		data:{
			"phoneNum":activity.phone,
			"activityId":activity.info.activityId
		},
		dataType: "json",
		success: function(result) { 
			alert(result.msg);
			$(".sign-up").hide();
		}	
	})
}