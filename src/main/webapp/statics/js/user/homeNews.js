$(document).ready(function(){
	/*
	 * 风采展示轮播
	 */
	$(function(){
		var imgOuter = $('.bottom-right');
		var imgDiv = $('.pic');
		var timeId = null;
		var edgeDistance = 90;
		var imgNow = 0;
		var imgMouse = 0;
		var imgOuterWidth = imgOuter.width();
		
		function autoSlide(){
			if(imgNow == imgDiv.length-1){
				imgNow = 0;
			}else{
				imgNow ++;
			}
			
			if(imgNow == 0){
				for(var i=imgDiv.length-1;i>0;i--){
					imgDiv.eq(i).animate({'left':imgOuterWidth-(imgDiv.length-i)*edgeDistance+'px'},2000);
				}
			}else{
				imgDiv.eq(imgNow).animate({'left':edgeDistance*imgNow+'px'},2000);
			}
		}
		
		timeId = setInterval(autoSlide,3000);
		
		function mouseSlide(){
			if(imgMouse > imgNow){
				for(var i= imgNow+1;i<=imgMouse;i++){
					imgDiv.eq(i).stop().animate({'left':edgeDistance*i+'px'},2000);
				}
				imgNow = imgMouse;
			}else{
				for(var i= imgNow;i>imgMouse;i--){
					imgDiv.eq(i).stop().animate({'left':imgOuterWidth-(imgDiv.length-i)*edgeDistance+'px'},2000);
				}
				imgNow = imgMouse;
			}
		}
		
		imgDiv.hover(function(){
			clearInterval(timeId);
			imgMouse = $(this).index();
			if(imgMouse != imgNow){
				mouseSlide();
			}		
		},function(){
			timeId = setInterval(autoSlide,3000);
		}).bind('click',function(){
			imgNow = $(this).index();
		});
	});
	
	/*
	 * 党建要闻自动轮播
	 */
	$('#myCarousel').carousel({
		interval: 3000
	});
	
	/*
	 * 选项卡显示
	 */
	
	$("#notice-publicity").mouseenter(function(){
		document.getElementById("party-news").style.borderBottomColor = "#426ab3";
		document.getElementById("party-publicity").style.borderBottomColor = "#426ab3";
		document.getElementById("notice-publicity").style.borderBottomColor = "red";
		$("#np-list").show();
		$("#pn-list").hide();
		$("#pp-list").hide();
	})
	$("#party-news").mouseenter(function(){
		document.getElementById("party-news").style.borderBottomColor = "red";
		document.getElementById("party-publicity").style.borderBottomColor = "#426ab3";
		document.getElementById("notice-publicity").style.borderBottomColor = "#426ab3";
		$("#pn-list").show();
		$("#np-list").hide();
		$("#pp-list").hide();
	})
	$("#party-publicity").mouseenter(function(){
		document.getElementById("party-news").style.borderBottomColor = "#426ab3";
		document.getElementById("party-publicity").style.borderBottomColor = "red";
		document.getElementById("notice-publicity").style.borderBottomColor = "#426ab3";
		$("#pp-list").show();
		$("#pn-list").hide();
		$("#np-list").hide();
	})
})
var mien = new Vue({
	el: '#mien',
	data: {
		image: []
	},
	created: function() {
		this.doMienTo(1);
	},
	methods:{
		doMienTo:function(id){
			var self = this;
			$.ajax({
				type: "GET", // 请求方式
				url: "../statics/json/image.json", // 地址，就是json文件的请求路径
				dataType: "json", // 数据类型可以为 text xml json script jsonp
				success: function(result) { 
					self.image = result.image;
				}			
			});
		}
	}

})
var branch = new Vue({
	el: '#branch',
	data: {
		branch: []
	},
	created: function(){
		var self = this;
		$.ajax({
			type:"GET",
			url: "../statics/json/branch.json", // 地址，就是json文件的请求路径
			dataType: "json", // 数据类型可以为 text xml json script jsonp
			success: function(result) { 
				self.branch = result.branch;
			}			
		})
	},
	methods:{
		MienTo : function(id){//支部风采选择
			mien.doMienTo(id);
		}
	}
})
var newslength = 5;
var News = new Vue({
	el: '#FocusNews',
	data:{
		news:[]
	},
	created:function(){
		var self = this;
		$.ajax({
			type:"get",
			url:"../homelist/newslist/"+newslength,
			dataType: "json", // 数据类型可以为 text xml json script jsonp
			success: function(result) { 
				if(result.status==0){
					self.news = result.data;
				}else{
					alert(result.msg);
				}
				
			}
		});
	},
	methods:{
		generateClass:function(index){
			if(index==0){
				return "item active";
			}else{
				return "item";
			}
		}
	}
})
var PartyNews = new Vue({
	el: '#PartyNews',
	data:{
		"party_news":[]
	},
	created:function(){
		var self = this;
		$.ajax({
			type:"get",
			url:"../homelist/newslist/"+newslength,
			dataType: "json", // 数据类型可以为 text xml json script jsonp
			success: function(result) { 
				if(result.status==0)
					self.party_news = result.data;
				else{
					alert(result.msg);
				}
			}
		});
	}
})
var NoticePublicity = new Vue({
	el: '#NoticePublicity',
	data:{
		"notice":[]
	},
	created:function(){
		var self = this;
		$.ajax({
			type:"get",
			url:"../homelist/noticeslist/public/"+newslength,
			dataType: "json", // 数据类型可以为 text xml json script jsonp
			success: function(result) { 
				if(result.status==0)
					self.notice = result.data;
				else{
					alert(result.msg);
				}
			}
		});
	}
})
var PartyPublicity = new Vue({
	el: '#PartyPublicity',
	data:{
		"public":[]
	},
	created:function(){
		var self = this;
		$.ajax({
			type:"get",
			url:"../homelist/noticeslist/party/"+newslength,
			dataType: "json", // 数据类型可以为 text xml json script jsonp
			success: function(result) { 
				if(result.status==0)
					self.public = result.data;
				else{
					alert(result.msg);
				}
			}
		});
	}
})
var Learning = new Vue({
	el: '#Learning',
	data:{
		"learn":[]
	},
	created:function(){
		var self = this;
		$.ajax({
			type:"post",
			url:"../study/get_study_documents.do?page=1&pageNum=5",
			dataType: "json", // 数据类型可以为 text xml json script jsonp
			success: function(result) { 
				if(result.status==0)
					self.learn = result.data.list;
				else{
					alert(result.msg);
				}
			}
		});
	},
	methods:{
		initTime:function(time){
			return time.substring(5,10);
		}
	}
})
var Download = new Vue({
	el: '#Download',
	data:{
		"download":[]
	},
	created:function(){
		var self = this;
		$.ajax({
			type:"post",
			url:"../study/get_study_documents.do?page=1&pageNum=5",
			dataType: "json", // 数据类型可以为 text xml json script jsonp
			success: function(result) { 
				if(result.status==0)
					self.download = result.data.list;
				else{
					alert(result.msg);
				}
			}
		});
	},
	methods:{
		initTime:function(time){
			return time.substring(5,10);
		}
	}
})