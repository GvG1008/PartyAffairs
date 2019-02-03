function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var id=getUrlParam('id');//新闻公告id

var app = new Vue({
	el:"#app",
	data:{
		content:[]
	},
	methods:{
		
	},
	created:function(){
		$.ajax({
			type:"get",
			url:"../meeting/"+id,
			async:true,
			success:function(res){
				if(res.status == 0){
					app.content = res.data;
				}
			}
		});
	}
})