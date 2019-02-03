var app = new Vue({
	el:"#app",
	data:{
		content:[],
		pagenow:[],
		totalpage:[]
	},
	methods:{
		
	},
	created:function(){
		$.ajax({
			type:"get",
			url:"../meetingMenu_1/1/10",
			async:true,
			success:function(res){
				app.pagenow = res.data.pageNum;
				app.totalpage = res.data.totalPageNum;
				app.content = res.data.list;
			}
		});
	}
})