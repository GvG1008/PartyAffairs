var myPlayer = videojs('my-video');
videojs("my-video").ready(function(){
	var myPlayer = this;
	myPlayer.play();
	//	var whereYouAt = myPlayer.currentTime();//获取播放进度
	myPlayer.currentTime(10);//设置播放进度
});