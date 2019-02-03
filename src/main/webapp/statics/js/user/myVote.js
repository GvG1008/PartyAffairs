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
			url:"../voteinfo/votinglist",
			async:true,
			success:function(res){
				console.log(res)
				app.content = res.data
			}
		});
	}
})