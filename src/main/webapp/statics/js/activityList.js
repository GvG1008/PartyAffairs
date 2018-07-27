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
			alert("报名成功，请等待报名结果！");
			location.reload();
		}
	})
	
})
