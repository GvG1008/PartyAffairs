/**
 * 视频中间页
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var id=getUrlParam('id');//视频id
$(document).ready(function(){
	var player = document.getElementById("my-video");
	
	//不可快进
	var last = 0;
	player.ontimeupdate = function () {
	    var current = player.currentTime;
	    if(current - last > 2) {
	        player.currentTime = last;
	    } else {
			last = current;
	    }
	};
	
	$(function(){
		if(player.paused){
			$("#play").show();
			$("#pause").hide();
		}
//		var current = player.currentTime; //获取视频进度
//		player.currentTime = 10; //设置视频进度
//		var duratime = player.duration; //获取视频总长度
	})
	
	$("#play").click(function(){
		player.play();
		$("#play").hide();
		$("#pause").show();
	})
	
	$("#pause").click(function(){
		player.pause();
		$("#play").show();
		$("#pause").hide();
	})
})
var dvideo = new Vue({
	el :'#dvideo',
	data :{
		dvideo:[]
	},
	created:function(){
		var self = this;
		$.ajax({
			type:"post",
			url:"../study/get_study_video_details.do?video_id="+id,
			dataType:"json",
			success: function(result){
				if(result.status==0)
					self.dvideo = result.data;
				else{
					alert(result.msg);
				}
			}
		})
	}
})