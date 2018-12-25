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
}