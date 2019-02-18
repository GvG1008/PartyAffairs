/**
 * 个人信息页面
 */
var info = new Vue({
	el :'#personinfo',
	data: {
		"personinfo":[],
		"partyinfo":[]
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
					}else{
						alert(result.msg);
					}
				}
			});
		},
		save:function(){
			save();
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
				console.log(result)
				app.partyinfo = result.data;
			}else{
				alert(result.msg);
			}
		}
	});
}