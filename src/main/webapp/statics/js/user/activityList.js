$(document).ready(function(){
	/*
	 * 报名弹窗位置
	 */
	$(".sign-btn").click(function(){
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
	})
	
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
			location.reload();
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
		
	},
	methods :{
		getInfo :function(id){//获取活动报名详情
			doGetInfo(id);
		},
		enroll :function(){//报名
			doEnroll();
		}
	}
})

function doGetInfo(id){
	$.ajax({
		type:"GET",
		url: "../../activity/"+id, 
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
	/*$.ajax({
		type:"post",
		url: "../activity", 
		data:{
			"phone":activity.phone,
			"id":info.id
		},
		dataType: "json",
		success: function(result) { 
			if(result.status == 0)
				alert("报名成功，请等待报名结果！");
			else
				alert(result.msg);
		}	
	})*/
	alert("报名成功，请等待报名结果！");
}




















