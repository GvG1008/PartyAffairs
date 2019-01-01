$(document).ready(function(){
	$("#submit").click(function(){
		var userId = $("#userId").val();
		var grade = $("#grade").val();
		var className = $("#className").val();
		var name = $("#realname").val();
		var nameFormer = $("#nameFormer").val();
		var sex = $("#gender").val();
		var national = $("#mz").val();
		var idCard = $("#idcard").val();
		var birthDate = $("#birthdate").val();
		var maritalStatus = $("#hy").val();
		var familyBackground = $("#famiback").val();
		var personalIdentity = $("#sf").val();
		var jiguansheng = $("#jiguansheng").val();
		var jiguanshi = $("#jiguanshi").val();
		var jiguanxian = $("#jiguanxian").val();
		var nativePlace = jiguansheng+jiguanshi+jiguanxian;
		var brsheng = $("#brsheng").val();
		var brshi = $("#brshi").val();
		var brxian = $("#brxian").val();
		var birthPlace =brsheng+brshi+brxian;
		var hksheng = $("#hksheng").val();
		var hkshi = $("#hkshi").val();
		var hkxian = $("#hkxian").val();
		var placeRegistered = hksheng+hkshi+hkxian;
		var tel = $("#telephone").val();
		var addrsheng = $("#addrsheng").val();
		var addrshi = $("#addrshi").val();
		var addrxian = $("#addrxian").val();
		var addrzhen = $("#addrzhen").val();
		var detailadd = $("#detailadd").val();
		var homeAddress = addrsheng+addrshi+addrxian+addrzhen+detailadd;
		var educationalBackground = $("#xl").val();
		var professional = $("#zy").val();
		var graduateSchool = $("#graduate").val();
		var organizationUnit = $("#organizationUnit").val();
		var branchId = $("#zb").val();
		var typeDevelopment = $("#fz").val();
		var timeApplicationforparty = $("#timeApplicationforparty").val();
		var timeIntoparty = $("#timeIntoparty").val();
		var timePositive = $("#timePositive").val();
		var totot = $("#totot").val();
		var outUnit = $("#outUnit").val();
		var usermessage = {
			className:className,
			grade:grade,
			userId:userId,
			name:name,
			nameFormer:nameFormer,
			sex:sex,
			national:national,
			idCard:idCard,
			birthDate:birthDate,
			maritalStatus:maritalStatus,
			familyBackground:familyBackground,
			personalIdentity:personalIdentity,
			nativePlace:nativePlace,
			birthPlace:birthPlace,
			placeRegistered:placeRegistered,
			tel:tel,
			homeAddress:homeAddress,
			educationalBackground:educationalBackground,
			professional:professional,
			graduateSchool:graduateSchool,
			organizationUnit:organizationUnit,
			branchId:branchId,
			typeDevelopment:typeDevelopment,
			timeApplicationforparty:timeApplicationforparty,
			timeIntoparty:timeIntoparty,
			timePositive:timePositive,
			totot:totot,
			outUnit:outUnit
		}
		console.log(usermessage)
		$.ajax({
			type:"post",
			data:JSON.stringify(usermessage),
			contentType: "application/json",
			url:"../../userManage/insertUser",
			async:true,
			success:function(res){
				if(res.status == 0){
					alert("录入信息成功")
					window.location.reload();
				}
				else{
					alert("请确保信息录入正确")
				}
			}
		});
		
	})
})
