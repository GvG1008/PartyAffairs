/**
 * 考试中间页
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var id=getUrlParam('id');//考试id

var info = new Vue({
	el :'#examinfo',
	data :{
		"info":[]
	},
	created :function(){
		var self = this;
		$.ajax({
			type:"GET",
			url: "../examlist/"+id, 
			dataType: "json",
			success: function(result) { 
				if(result.status == 0)
					self.info = result.data;
				else
					alert(result.msg);
			}	
		})
	},
	methods:{
		GotoExam:function(id){
			doGotoExam(id);
		}
	}
})
function doGotoExam(id){
	window.location.href="exam.html?id="+id;
}