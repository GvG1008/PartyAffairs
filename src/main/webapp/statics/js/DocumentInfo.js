/**
 * 2018-12-19
 * eachen
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var id=getUrlParam('id');//新闻公告id


var contents = new Vue({
	el:"#document_details",
	data:{
		content:[]
	},
	methods:{
		
	},
	created:function(){
		getDocumentDetails(id);
	}
})

/**
 * 根据文档ID获取文档内容
 */
function getDocumentDetails(documentID){
	$.ajax({
		type:"post",
		data:{
			document_id:documentID
		},
		url:"../../study/get_study_document_details.do",
		async:true,
		success:function(res){
			console.log(res)
			if(res.status == 0){
				contents.content = res.data;
			}
		}
	});
}
