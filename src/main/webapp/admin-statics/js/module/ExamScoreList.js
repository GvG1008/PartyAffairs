/**
 * 考试查看列表
 */
var ExamContent = new Vue({
	el:"#ExamContent",
	data:{
		examContentList:[]
	},
	created : function() {
		this.loadNewList();
	},
	methods : {
		loadNewList:function(){
			var self = this;
			$.ajax({
				type : "get",
				url : "../../examscore",
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.status == 0) {
						self.examContentList = result.data;
					} else {
						alert(result.msg);
					}
				}
			});
		}
	}
})