/**
 * 视频中间页
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var id=getUrlParam('id');//视频id
var dvideo = new Vue({
	el :'#dvideo',
	data :{
		dvideo:[],
		url:[],
		state:-1
	},
	created:function(){
		var self = this;
		$.ajax({
			type:"post",
			url:"../study/get_study_video_details.do?video_id="+id,
			dataType:"json",
			success: function(result){
				if(result.status==0){
					self.dvideo = result.data;
					self.loadUrl(result.data.videoPath);
				}
				else{
					alert(result.msg);
				}
			}
		});
		$.ajax({
			type:"get",
			url:"../study/get_study_video_already_state/"+id,
			async:true,
			success:function(res){
				if(res.status == 0){
					self.state = res.data.already
				}
			}
		});
	},
	methods: {
        loadUrl: function(path){
        	var self = this;
			this.$nextTick(function () {
				self.url = path;				
              })
        },
        learned:function(){
			var that = this;
			$.ajax({
				type:"post",
				url:"../study/set_study_video_already/"+id,
				async:true,
				success:function(res){
					location.reload();
				}
			});
		}
      }
})
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
