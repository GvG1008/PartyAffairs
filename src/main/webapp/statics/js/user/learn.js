$(document).ready(function(){
	
	$(function() {
		$(':input').labelauty();
	});
	
	$("#learn-search").click(function(){
		var lable = document.getElementsByName("checkbox");
		var type = document.getElementsByName("radio");
		var claim = document.getElementsByName("radio1");
		var lables = new Array();
		lables[0] = 0;
		var y=0;
		var gtype = 1;//类型
		var gclaim = 0;//要求
		
		for(var i=0;i<lable.length;i++){
			if(lable[i].checked){
				lables[y++] = lable[i].value;
			}
		}
		for(var i=0;i<type.length;i++){
			if(type[i].checked){
				gtype = type[i].value;
			}
		}
		for(var i=0;i<claim.length;i++){
			if(claim[i].checked){
				gclaim = claim[i].value;
			}
		}
		
		var geturl = "";
		if(gclaim == 0 && gtype == 1 && lables[0] == 0){
			geturl = "../study/get_study_videos.do";
		}else if(gclaim == 0 && gtype == 2 && lables[0] == 0){
			geturl = "../study/get_study_documents.do";
		}else if(gclaim == 1 && gtype == 1 && lables[0] == 0){
			geturl = "../study/get_study_videos_must.do";
		}else if(gclaim == 1 && gtype == 2 && lables[0] == 0){
			geturl = "../study/get_study_documents_must.do";
		}
		alert(geturl);
	})
})
var Learning = new Vue({
	el: '#lable-list',
	data:{
		"learn":[]
	},
	created:function(){
		var self = this;
		$.ajax({
			type:"post",
			url:"../study/get_labels.do",
			dataType: "json", // 数据类型可以为 text xml json script jsonp
			success: function(result) { 
				if(result.status==0)
					self.learn = result.data;
				else{
					alert(result.msg);
				}
			}
		});
	},
	methods:{
		
	}
})
