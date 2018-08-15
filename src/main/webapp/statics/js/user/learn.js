var geturl = "../study/get_study_videos.do?page=1&pageNum=9";
var lables = new Array();//标签
lables[0] = 0;
var gtype = 1;//类型
var gclaim = 0;//要求
$(document).ready(function(){
	
	$(function() {
		$(':input').labelauty();
	});
	
	$("#learn-search").click(function(){
		var lable = document.getElementsByName("checkbox");
		var type = document.getElementsByName("radio");
		var claim = document.getElementsByName("radio1");
		var y=0;
		
		lables = new Array();//标签
		lables[0] = 0;
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
		doPageto(1);
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
	}
})
var limit = 9;//每页显示条数
var DataList = new Vue({
	el: '#data',
	data:{
		"list":[],
		"currentNum":1,
		"totalPage":[],
		"pagetoNum":[],
		"studyflag":1
	},
	created:function(){
		doPageto(1);		
	},
	methods :{
		pageto : function(currentNum) {
			doPageto(currentNum);
		}
	}
})
function doPageto(currentNum){
	if(gclaim == 0 && gtype == 1 && lables[0] == 0){//要求全部；标签全部；类型视频
		geturl = "../study/get_study_videos.do?page="+currentNum+"&pageNum="+limit;
	}else if(gclaim == 0 && gtype == 2 && lables[0] == 0){//要求全部；标签全部；类型资料
		geturl = "../study/get_study_documents.do?page="+currentNum+"&pageNum="+limit;
	}else if(gclaim == 1 && gtype == 1 && lables[0] == 0){//要求必学；标签全部；类型视频
		geturl = "../study/get_study_videos_must.do?page="+currentNum+"&pageNum="+limit;
	}else if(gclaim == 1 && gtype == 2 && lables[0] == 0){//要求必学；标签全部；类型资料
		geturl = "../study/get_study_documents_must.do?page="+currentNum+"&pageNum="+limit;
	}else if(gclaim == 0 && gtype == 2 && lables[0] != 0){//要求全部；标签自选；类型资料
		geturl = "../study/get_study_documents_by_label_id.do?label_id="+lables+"&page="+currentNum+"&pageNum="+limit;
	}else if(gclaim == 1 && gtype == 2 && lables[0] != 0){//要求必学；标签自选；类型资料
		geturl = "../study/get_study_documents_must_by_label_id.do?label_id="+lables+"&page="+currentNum+"&pageNum="+limit;
	}else if(gclaim == 0 && gtype == 1 && lables[0] != 0){//要求全部；标签自选；类型视频
		geturl = "../study/get_study_videos_by_label_id.do?label_id="+lables+"&page="+currentNum+"&pageNum="+limit;
	}else if(gclaim == 1 && gtype == 1 && lables[0] != 0){//要求必学；标签自选；类型视频
		geturl = "../study/get_study_videos_must_by_label_id.do?label_id="+lables+"&page="+currentNum+"&pageNum="+limit;
	}
	
	//alert(geturl);
	$.ajax({
		type:"post",
		url:geturl,
		dataType: "json", // 数据类型可以为 text xml json script jsonp
		success: function(result) { 
			if(result.status==0){
				DataList.list = result.data.list;
				DataList.currentNum = result.data.page;
				DataList.totalPage = result.data.totalPage;
				if(gtype == 1){
					DataList.studyflag=1;
				}else{
					DataList.studyflag=2;
				}
			} else{
				alert(result.msg);
			}
		}
	});
}