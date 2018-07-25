$(function(){
	//计算内容区域高度
	var calcHeight = function(){
		$('#mainFrameTabs').height(763);
	};
	//菜单点击
	$('a',$('#menuSideBar')).on('click', function(e) {
		e.stopPropagation();
		var li = $(this).closest('li');
		var menuId = $(li).attr('mid');
		var url = $(li).attr('funurl');
		var title = $(this).text();
		$('#mainFrameTabs').bTabsAdd(menuId,title,url);
//		alert(menuId);
//		alert(title);
//		alert(url);
//		var margin =$("#close").css('marginLeft');
		var height = $("#close").offset().top;
		var width = $(".btab11").width();
		
		if(width<25){
			alert( width );
			$(".btab11").css('padding-left','10px')
		}
		
		if(height>=79){
			$('.navTabsCloseBtn').css('marginTop','-18px');
			//alert($('.navTabsCloseBtn').css('marginTop','-18px')); 
		} 	
 	    
	});
	
	$('a',$('#menuSideBar1')).on('click', function(e) {
		e.stopPropagation();
		var li = $(this).closest('li');
		var menuId = $(li).attr('mid');
		var url = $(li).attr('funurl');
		var title = $(this).text();
		$('#mainFrameTabs').bTabsAdd(menuId,title,url);
		var height = $("#close").offset().top;
		if(height>=79){
			$('.navTabsCloseBtn').css('marginTop','-18px');
		}
        var width = $(".btab11").width();
		if(width<25){
			$(".btab11").css('padding-left','10px')
		}
	});
	$('a',$('#menuSideBar2')).on('click', function(e) {
		e.stopPropagation();
		var li = $(this).closest('li');
		var menuId = $(li).attr('mid');
		var url = $(li).attr('funurl');
		var title = $(this).text();
		$('#mainFrameTabs').bTabsAdd(menuId,title,url);
		var height = $("#close").offset().top;
		if(height>=79){
			$('.navTabsCloseBtn').css('marginTop','-18px');
		} 
		var width = $(".btab11").width();
		if(width<25){
			$(".btab11").css('padding-left','10px')
		}
	});
	$('a',$('#menuSideBar3')).on('click', function(e) {
		e.stopPropagation();
		var li = $(this).closest('li');
		var menuId = $(li).attr('mid');
		var url = $(li).attr('funurl');
		var title = $(this).text();
		$('#mainFrameTabs').bTabsAdd(menuId,title,url);
		var height = $("#close").offset().top;
		if(height>=79){
			$('.navTabsCloseBtn').css('marginTop','-18px');
		}  
		var width = $(".btab11").width();
		if(width<25){
			$(".btab11").css('padding-left','10px')
		}
	});
	$('a',$('#menuSideBar4')).on('click', function(e) {
		e.stopPropagation();
		var li = $(this).closest('li');
		var menuId = $(li).attr('mid');
		var url = $(li).attr('funurl');
		var title = $(this).text();
		$('#mainFrameTabs').bTabsAdd(menuId,title,url);
		var height = $("#close").offset().top;
		if(height>=79){
			$('.navTabsCloseBtn').css('marginTop','-18px');
		}
		var width = $(".btab11").width();
		if(width<25){
			$(".btab11").css('padding-left','10px')
		}
	});
	$('a',$('#menuSideBar5')).on('click', function(e) {
		e.stopPropagation();
		var li = $(this).closest('li');
		var menuId = $(li).attr('mid');
		var url = $(li).attr('funurl');
		var title = $(this).text();
		$('#mainFrameTabs').bTabsAdd(menuId,title,url);
		var height = $("#close").offset().top;
		if(height>=79){
			$('.navTabsCloseBtn').css('marginTop','-18px');
		}    
		var width = $(".btab11").width();
		if(width<25){
			$(".btab11").css('padding-left','10px')
		}
	});
	$('a',$('#menuSideBar6')).on('click', function(e) {
		e.stopPropagation();
		var li = $(this).closest('li');
		var menuId = $(li).attr('mid');
		var url = $(li).attr('funurl');
		var title = $(this).text();
		$('#mainFrameTabs').bTabsAdd(menuId,title,url);
		var height = $("#close").offset().top;
		if(height>=79){
			$('.navTabsCloseBtn').css('marginTop','-18px');
		}  
		var width = $(".btab11").width();
		if(width<25){
			$(".btab11").css('padding-left','10px')
		}
	});
	$('a',$('#menuSideBar7')).on('click', function(e) {
		e.stopPropagation();
		var li = $(this).closest('li');
		var menuId = $(li).attr('mid');
		var url = $(li).attr('funurl');
		var title = $(this).text();
		$('#mainFrameTabs').bTabsAdd(menuId,title,url);
		var height = $("#close").offset().top;
		if(height>=79){
			$('.navTabsCloseBtn').css('marginTop','-18px');
		}  
		var width = $(".btab11").width();
		if(width<25){
			$(".btab11").css('padding-left','10px')
		}
	});
	$('a',$('#menuSideBar8')).on('click', function(e) {
		e.stopPropagation();
		var li = $(this).closest('li');
		var menuId = $(li).attr('mid');
		var url = $(li).attr('funurl');
		var title = $(this).text();
		$('#mainFrameTabs').bTabsAdd(menuId,title,url);
		var height = $("#close").offset().top;
		if(height>=79){
			$('.navTabsCloseBtn').css('marginTop','-18px');
		}  
		var width = $(".btab11").width();
		if(width<25){
			$(".btab11").css('padding-left','10px')
		}
	});
	$('a',$('#menuSideBar9')).on('click', function(e) {
		e.stopPropagation();
		var li = $(this).closest('li');
		var menuId = $(li).attr('mid');
		var url = $(li).attr('funurl');
		var title = $(this).text();
		$('#mainFrameTabs').bTabsAdd(menuId,title,url);
		var height = $("#close").offset().top;
		if(height>=79){
			$('.navTabsCloseBtn').css('marginTop','-18px');
		}   
		var width = $(".btab11").width();
		if(width<25){
			$(".btab11").css('padding-left','10px')
		}
	});
	$('a',$('#menuSideBar10')).on('click', function(e) {
		e.stopPropagation();
		var li = $(this).closest('li');
		var menuId = $(li).attr('mid');
		var url = $(li).attr('funurl');
		var title = $(this).text();
		$('#mainFrameTabs').bTabsAdd(menuId,title,url);
		var height = $("#close").offset().top;
		if(height==97){
			$('.navTabsCloseBtn').css('marginTop','-18px');
		} 
		var width = $(".btab11").width();
		if(width<25){
			$(".btab11").css('padding-left','10px')
		}
	});
	$('a',$('#menuSideBar11')).on('click', function(e) {
		e.stopPropagation();
		var li = $(this).closest('li');
		var menuId = $(li).attr('mid');
		var url = $(li).attr('funurl');
		var title = $(this).text();
		$('#mainFrameTabs').bTabsAdd(menuId,title,url);
		var height = $("#close").offset().top;
		if(height>=79){
			$('.navTabsCloseBtn').css('marginTop','-18px');
		} 
		var width = $(".btab11").width();
		if(width<25){
			$(".btab11").css('padding-left','10px')
		}
 	   
	});
	
	//初始化
	$('#mainFrameTabs').bTabs({
		resize : calcHeight
	});
	
	/**********关闭全部tab*************/
	$(".allTabsCloseBtn").click(
		function(){
			//询问是否关闭全部
			if (confirm("是否要关闭所有选项卡 ?")){ 
			   window.location.href='index.html';
			    	
			    }
			});
});