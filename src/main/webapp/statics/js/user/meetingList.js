var app = new Vue({
	el:"#app",
	data:{
		content:[],
		pagenow:[],
		totalpage:[],
		pagetoNum:""
	},
	methods:{
		pageto : function(currentNum) {
			doPageto(currentNum);
		}
	},
	created:function(){
		doPageto(1)
	}
})

function doPageto(currentNum){
	$.ajax({
			type:"get",
			url:"../meetingMenu_1/"+currentNum+"/2",
			async:true,
			success:function(res){
                if (res.status == 0){
                    app.pagenow = res.data.pageNum;
                    app.totalpage = res.data.totalPageNum;
                    app.content = res.data.list;
                }else{
                    alert(res.msg);
                }
				
			}
		});
}
