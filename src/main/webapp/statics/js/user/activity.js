/**
 * 活动详情页
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var id=getUrlParam('id');//新闻公告id
var activity = new Vue({
	el : '#activity',
	data :{
		datas:[]
	},
	created	: function(){
		getDetails();
	},
	methods	:{
		updateMessage: function (){
			var self = this;
			this.$nextTick(function () {
				document.getElementById("details").innerHTML = activity.datas.content; 				
              })
		}
	}
})
function getDetails(){//获取详情
	$.ajax({
		type:"GET",
		url: "../partyActivity/info/"+id, 
		dataType: "json",
		success: function(result) { 
			if (result.status == 0) {
				activity.datas = result.data;
				activity.updateMessage();
			} else {
				alert(result.msg);
			}
		}	
	})
}