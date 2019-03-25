/**
 * 个人信息页面
 */
var info = new Vue({
	el :'#personinfo',
	data: {
		"personinfo":[],
		"partyinfo":[],
		"headimg":[]
	},
	created:function(){
		this.loadPersonInfo();
		this.loadPartyInfo();
	},
	methods:{
		loadPersonInfo:function(){
			var app = this;
			$.ajax({
				type:"get",
				url: "../userInfo/personInfo",
				async : false,
				dataType: 'json',
				success: function(result){
					if (result.status == 0) {
						app.personinfo = result.data;
						app.headimg = result.data.imgHead;
						//console.log(result)
					}else{
						alert(result.msg);
					}
				}
			});
		},
		loadPartyInfo:function(){
			var app = this;
			$.ajax({
				type:"get",
				url: "../userInfo/partyInfo",
				async : false,
				dataType: 'json',
				success: function(result){
					if (result.status == 0) {
						app.partyinfo = result.data;
						//console.log(result)
					}else{
						alert(result.msg);
					}
				}
			});
		},
		save:function(){
			save();
		},
		saveimg:function(){
			saveimg();
		}
	}
})
function save(){
	var email = $('#email').val();
	var phonenum = $('#phonenum').val();
	var profile = $('#profile').val();
	var userId = $('#userId').val();
	console.log(userId)
	var info = {
		email:email,
		profile:profile,
		userId:userId
	};
	$.ajax({
		type:"post",
		url: "../userInfo/updatePerson",
		data: {"email":email,"profile":profile,"userId":userId},
//		data:{
//			info:info
//		},
		dataType: 'json',
		success: function(result){
			if (result.status == 0) {
				alert("修改成功")
				app.partyinfo = result.data;
			}else{
				alert(result.msg);
			}
		}
	});
}


$("#updateheadimg").change(function(){  
		 var objUrl = getObjectURL(this.files[0]) ;//获取文件信息  
		 console.log("objUrl = "+objUrl);  
		  if (objUrl) {  
		  //$("#img0").attr("src", objUrl);
		  info.headimg = objUrl;
		 }   
}) ;  
function getObjectURL(file) {  
		 var url = null;   
		 if (window.createObjectURL!=undefined) {  
		  url = window.createObjectURL(file) ;  
		 } else if (window.URL!=undefined) { // mozilla(firefox)  
		  url = window.URL.createObjectURL(file) ;  
		 } else if (window.webkitURL!=undefined) { // webkit or chrome  
		  url = window.webkitURL.createObjectURL(file) ;  
		 }  
		 return url ;  
		}

function saveimg(){
	var formData = new FormData();
	var imgHeadFile = $("#updateheadimg").get(0).files[0];
	if(imgHeadFile != "undefined")
	{
		formData.append("imgHeadFile",imgHeadFile)
	}
	
	$.ajax({
		type:"post",
		url:"../userInfo/updateImgHead",
		cache : false,
		data : formData,
		processData : false,
		contentType : false,
		dataType : 'json',
		async:true,
		success:function(res){
			if(res.status == 0){
				alert("头像已更新")
			}
		}
	});
}
