/**
 * 考试查看列表
 */
var ExamContent = new Vue({
	el:"#ExamContent",
	data:{
		ExamContentList:[]
	},
	created : function() {
		//this.loadNewList();
	},
	methods : {
		loadNewList:function(){
			var self = this;
			$.ajax({
				type : "get",
				url : "../../examinfo/lists",
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.status == 0) {
						app.datas = result.data;
					} else {
						alert(result.msg);
					}
				}
			});
		}
	}
})