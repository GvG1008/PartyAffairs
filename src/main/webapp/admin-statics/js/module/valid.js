$(function() {
	/*
	 * 得到所有的错误信息，循环遍历之。调用一个方法来确定是否显示错误信息！
	 */
    $(".tip").each(function() {
		showtip($(this));//遍历每个元素，使用每个元素来调用show-tip方法
	});
	
	/*
	 * 输入框得到焦点隐藏错误信息
	 */
	$(".form-input").focus(function() {
		var labelId = $(this).attr("id") + "-tip";//通过输入框找到对应的label的id
		$("#" + labelId).text(" ");//把label的内容清空！
		/*$('#'+id+":hover,:focus").css("borderBottom","1.5px solid #337ab7;");
		$('#'+id).css("borderBottom","solid 0.5px #9d9d9db8");
		*/showtip($("#" + labelId));//隐藏没有信息的label
	});

	
	/*
	 * 输入框失去焦点进行校验
	 */
	$(".form-input").blur(function() {
		var id = $(this).attr("id");//获取当前输入框的id
		if(id!=""){
		var funName = "validate" + id.substring(0,1).toUpperCase() + id.substring(1) + "()";//得到对应的校验函数名
		eval(funName);//执行函数调用
		}
	});
	
//	$("#submit").click(function() {
//		if($("#detailadd-tip").text==""&&$("#telephone-tip").text==""&&$("#realname-tip").text==""&&$("#idcard-tip").text==""&&$("#famiback-tip").text==""&&$("#graduate-tip").text=="")
//	    {
//	        /*window.location.href="#";*/
//			 alert('保存成功！'); 
//	       
//	    }
//		else{
//			
//	        alert('存在信息验证有误，请重新输入！！！');
//	        eval(validateDetailadd());
//			eval(validateTelephone());
//			eval(validateRealname());
//			eval(validateGraduate());
//			eval(validateIdcard());
//			eval(validateFamiback());
//			
//		}
//			
//	});
});

function validateDetailadd() {
	var id = "detailadd";
	var value = $("#" + id).val();//获取输入框内容

	if(!value) {
		
		$("#" + id + "-tip").text("详细地址不能为空！");
	/*	$('#'+id+":hover").css("borderBottom","2px solid #337ab7;");
		$('#'+id).css("borderBottom","solid 2px #ff716e");
		*/showtip($("#" + id + "-tip"));
		
		return false;
	}
	
	return true;
	
}
function validateRealname() {
	var id = "realname";
	var value = $("#" + id).val();//获取输入框内容
	
	if(!value) {
		$("#" + id + "-tip").text("姓名不能为空！");
		/*$('#'+id+":hover").css("borderBottom","2px solid #337ab7;");
		$('#'+id).css("borderBottom","solid 2px #ff716e");
		*/showtip($("#" + id + "-tip"));
		
		return false;
	}
	
	return true;	
}
function validateFamiback() {
	var id = "famiback";
	var value = $("#" + id).val();//获取输入框内容
	
	if(!value) {
		$("#" + id + "-tip").text("家庭出身不能为空！");
		/*$('#'+id+":hover").css("borderBottom","2px solid #337ab7;");
		$('#'+id).css("borderBottom","solid 2px #ff716e");
		*/showtip($("#" + id + "-tip"));
		
		return false;
	}
	
	return true;	
}
function validateGraduate() {
	var id = "graduate";
	var value = $("#" + id).val();//获取输入框内容
	
	if(!value) {
		$("#" + id + "-tip").text("毕业院校不能为空！");
		/*$('#'+id+":hover").css("borderBottom","2px solid #337ab7;");
		$('#'+id).css("borderBottom","solid 2px #ff716e");
		*/showtip($("#" + id + "-tip"));
		
		return false;
	}
	
	return true;	
}
function validateTelephone() {
	var id = "telephone";
	var value = $("#" + id).val();//获取输入框内容
    
	if(!value) {
		
		$("#" + id + "-tip").text("联系方式不能为空！");
		showtip($("#" + id + "-tip"));
		return false;
	}
	/*
	 * 规范校验
	 */
	if(!(/^1[3|4|5|7|8][0-9]\d{4,8}$/.test(value))) 
	{
		$("#" + id + "-tip").text("请输入11位正确的手机号码！");
		showtip($("#" + id + "-tip"));
		false;
	}
	return true;
}
function validateIdcard() {
	var id = "idcard";
	var value = $("#" + id).val();//获取输入框内容
	if(!value) {
		$("#" + id + "-tip").text("身份证不能为空！");
		/*$('#'+id+":hover").css("borderBottom","2px solid #337ab7;");
		$('#'+id).css("borderBottom","solid 2px #ff716e");
		*/showtip($("#" + id + "-tip"));
		return false;
	}
	/*
	 * 规范校验
	 */
	if(!(/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/.test(value))) 
	{
		$("#" + id + "-tip").text("请输入18位正确的身份证！");
		/*$('#'+id+":hover").css("borderBottom","2px solid #337ab7;");
		$('#'+id).css("borderBottom","solid 2px #ff716e");
		*/showtip($("#" + id + "-tip"));
		false;
	}
	return true;
}

function showtip(ele) {
	var text = ele.text();//获取元素的内容
	if(!text) {//如果没有内容
		ele.css("display", "none");//隐藏元素
	} else {//如果有内容
		ele.css("display", "");//显示元素
		
	}
}
