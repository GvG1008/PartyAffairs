$(function(){
	//计算内容区域高度1500
	var calcHeight = function(){
		$('#mainFrameTabs').height(1500);
		
	};
	//菜单点击
	$('a',$('#menuSideBar')).on('click', function(e) {
        console.log(
            "sdasdassd"
        );
		e.stopPropagation();
		var li = $(this).closest('li');
		var menuId = $(li).attr('mid');
		var url = $(li).attr('funurl');
		var title = $(this).text();
		$('#mainFrameTabs').bTabsAdd(menuId,title,url);
		/*alert(menuId);
		alert(title);
		alert(url);*/
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
            

            // iframe子页面-首页按钮打开新标签页
function aclick (flag) {
    //党员档案
    if(flag==='info'){
      $('#info').click();
    }
    //文章发布
    if(flag==='ap'){
      $('#ap').click();
    }
    //资料管理
    if(flag==='data'){
      $('#data').click();
    }
    //学习情况
    if(flag==='study'){
      $('#study').click();
    }
    //考卷信息
    if(flag==='exam'){
      $('#exam').click();
    }
    //成绩查看
    if(flag==='score'){
      $('#score').click();
    }
    //所有会议
    if(flag==='meeting'){
      $('#meeting').click();
    }
    //投票发布
    if(flag==='vote'){
      $('#vote').click();
    }
    //支部活动
    if(flag==='branch'){
      $('#branch').click();
    }
    //思想反馈
    if(flag==='report'){
      $('#report').click();
    }
}
});




