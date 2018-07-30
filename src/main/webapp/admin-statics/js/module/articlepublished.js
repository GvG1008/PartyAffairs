 window.onload=function(){
     var imgArea=document.getElementById("imgArea");
     imgArea.ondragenter=function(){
       /*this.innerHTML="可以释放了";*/
     }
     imgArea.ondragover=function(ev){
       ev.preventDefault();
     }
     imgArea.ondragleave=function(){
     /*this.innerHTML="将文件拖拽到此区域"; */
       $("#icon-add").css("opacity","1");
     }
     imgArea.ondrop=function(ev){
       ev.preventDefault();
       var files=ev.dataTransfer.files;
       var fd=new FileReader();
       if(files[0].type.indexOf('image')!=-1){
       fd.readAsDataURL(files[0]);
       var previewImg =document.getElementById("banner-pic");
       fd.onload=function(){
         /*var li1=document.createElement("li");
         var img1=document.createElement("img");*/
    	 previewImg.src=this.result;
         /*li1.appendChild(img1);
         ul1.appendChild(li1);*/
       }
       }else{alert("请选择图片上传");}
      }
      // alert(files.length);
     
 }
 $("#imgArea").hover(function(){
	    if($("#banner-pic").src){
	    	$("#icon-add").css("opacity","0");
	    	$("#banner-pic").css("opacity","1");
	    	$("#dragContainer").css("opacity","0");
	    	$("#file-upload").css("opacity","0");
	 	   
	    }else{
	    	$("#icon-add").css("opacity","0");
	    	$("#banner-pic").css("opacity","0");
	    	$("#dragContainer").css("opacity","1");
	    	$("#file-upload").css("opacity","1");
		 	   
	    }
	    
},function(){
	   if($("#banner-pic").src){
    	$("#icon-add").css("opacity","0");
    	$("#banner-pic").css("opacity","1");
    	$("#dragContainer").css("opacity","0");
    	$("#file-upload").css("opacity","0");
    }else{
    	$("#icon-add").css("opacity","1");
    	$("#banner-pic").css("opacity","0");
    	$("#dragContainer").css("opacity","0");
    	$("#file-upload").css("opacity","0");
    }
});