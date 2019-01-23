/**
 * 个人信息
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var info_id=getUrlParam('id');//考试id
var info = new Vue({
	el :'#info',
	data :{
		"info":[]
	},
	created :function(){
		var self = this;
		$.ajax({
			type:"get",
			url: "../../userManage/PartyInfo/"+info_id,
			async : false,
			dataType: 'json',
			success: function(result){	
				console.log(result)
				self.info = result.data;
				self.init();
				//doreg(result.data.nativePlace);
			}
		});
	},
	methods:{
		init :function(){
			this.$nextTick(function () {
				doInit();
			})
		}
	}
})


function doInit(){//初始化
	//初始化婚姻状况
	var select = $("#hy").find("option");
	//console.log(select.length)
	for(var i=0;i<select.length;i++){
		if($(select[i]).val() == info.info.maritalStatus){
			$(select[i]).attr("selected", "selected");
		}
	}
	//初始化所属党支部
	var select1 = $("#zb").find("option");
	for(var i=0;i<select1.length;i++){
		if($(select1[i]).val() == info.info.branchId){
			$(select1[i]).attr("selected", "selected");
		}
	}
	
	var reg = /.+?(省|市|自治区|自治州|县|区|村|小区|花园|楼|栋|号)/g;
	//console.log(str.match(reg))
	var temp = info.info.nativePlace.match(reg)
	var birtemp = info.info.birthPlace.match(reg)
	var hktemp = info.info.placeRegistered.match(reg)
	var addrtemp = info.info.homeAddress.match(reg)
	console.log(addrtemp)
	$('#jiguan').citys({valueType:'name',province:temp[0],city:temp[1],area:temp[2]});
	$('#birthplace').citys({valueType:'name',province:birtemp[0],city:birtemp[1],area:birtemp[2]});
	$('#hukou').citys({valueType:'name',province:hktemp[0],city:hktemp[1],area:hktemp[2]});
	$('#address').citys({valueType:'name',province:addrtemp[0],city:addrtemp[1],area:addrtemp[2]});
	addrtemp.splice(0,3);
	console.log(addrtemp.toString())
	$("#detailadd").val(addrtemp.toString());
}

$(document).ready(function(){
	$("#submit").click(function(){
		var userId = info.info.userId;
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
		var zzsf = $("#zzsf").val();//组织身份,后端未提供对应元素
		var usermessage = {
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
			url:"../../userManage/update/partyInfo",
			async:true,
			success:function(res){
				if(res.status == 0){
					alert("更新成功")
					window.location.reload();
				}
				else{
					alert("请确保信息录入正确")
				}
			}
		});
		
	})
})
