/**
 * 资料中间页
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var id=getUrlParam('id');//资料id
var document = new Vue({
	el :'#document',
	data :{
		document:[],
		state:-1
	},
	created:function(){
		var self = this;
		$.ajax({
			type:"post",
			url:"../study/get_study_document_details.do?document_id="+id,
			dataType:"json",
			success: function(result){
				if(result.status==0)
					self.document = result.data;
				else{
					alert(result.msg);
				}
			}
		});
		$.ajax({
			type:"get",
			url:"../study/get_study_document_already_state/"+id,
			async:true,
			success:function(res){
				if(res.status == 0){
					self.state = res.data.already
				}
			}
		});
	},
	methods:{
		learned:function(){
			var that = this;
			$.ajax({
				type:"post",
				url:"../study/set_study_document_already/"+id,
				async:true,
				success:function(res){
					location.reload();
				}
			});
		}
	}
})