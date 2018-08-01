 
 //加载编辑器及自定义配置 
        $(document).ready(function() {  
            $('#summernote').summernote({  
                height: 800,//初始化默认高度    
                width:630,
                minHeight: null, //最小高度             
                maxHeight: null, //最大高度
                focus: true,//是否定位
                lang:'zh-CN',//注意这里，若要设置语言，则需要引入该语言配置js
                placeholder:"请在这里写下您的内容",
                fontSize:"16",
                toolbar: [
                    ['color', ['color']],
                    ['fontsize', ['fontsize']],
                    ['para', ['paragraph']],
                    ['style', ['bold','underline', 'clear']],
                    ['insert', ['picture', 'link']],
                    ['table', ['table']],
                    ['view',['codeview','fullscreen']],
                ]//配置工具栏
                //查看更多配置(https://summernote.org/deep-dive/)
                //下面重写上传图片方法
               /* callbacks: {
                    onImageUpload: function(files) {
                        //由于summernote上传图片上传的是二进制数据
                        //所以这里可以自己重新上传图片方法
                       var formData = new FormData();
                        formData.append('file',files[0]); 
                    }; 
                        /* $.ajax({
                            url : baseurl+"/upload/", //后台文件上传接口
                            type : 'POST',
                            data : formData,
                            processData : false,
                            contentType : false,
                            success : function(data) {
                                var imgJson = eval('(' + data + ')');
                                var imgStr = ' '+baseurl+imgJson.file_url;
                                //设置到编辑器中
                                $('#summernote').summernote('insertImage',imgStr,'img');
                            },
                            error:function(){
                                alert("上传失败...");
                            }
                        }); 
                    }
                }*/
            });
        });
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
       /*$("#icon-add").css("opacity","1");*/
     }
     imgArea.ondrop=function(ev){
       ev.preventDefault();
       var files=ev.dataTransfer.files;
       var fd=new FileReader();
       if(files[0].type.indexOf('image')!=-1){
    	  /*上传图片宽高比例：16：9*/
       fd.readAsDataURL(files[0]);
      
       var previewImg =document.getElementById("banner-pic");
       var Imgarea = document.getElementById("imgArea");
       var reloadBtn = document.getElementById("reloadbtn");
       var dragContainer = document.getElementById("dragContainer");
       var iconAdd = document.getElementById("icon-add");
       var fileBtn =  document.getElementById("filebtn");
       var fileUpload =  document.getElementById("file-upload");
       
       fd.onload=function(){
          
    	   previewImg.src= this.result;
           previewImg.style.height="355px";
           previewImg.style.opacity = "1";
          /* reloadBtn.style.opacity = "1";*/
           reloadBtn.style.display="flex";
           dragContainer.style.display="none";
           iconAdd.style.display="none";
           fileBtn.style.display="block";
           fileUpload.style.display="none";
           Imgarea.style.height="355px";
          
       };
       }else{alert("请选择图片上传");}
      }
 }
 $("#filebtn").on("change",function(){
	 var reads= new FileReader();
     f=document.getElementById('filebtn').files[0];
     reads.readAsDataURL(f);
     reads.onload=function (e) {
         document.getElementById('banner-pic').src=this.result;
     };
 });
 $("#file-upload").on("change",function(){
	 var reads= new FileReader();
     f=document.getElementById('file-upload').files[0];
     reads.readAsDataURL(f);
     reads.onload=function (e) {
         document.getElementById('banner-pic').src=this.result;
     };
     
     document.getElementById('banner-pic').style.height="355px";
     document.getElementById('banner-pic').style.opacity = "1";
     document.getElementById('reloadbtn').style.display="flex";
     document.getElementById('dragContainer').style.display="none";
     document.getElementById('icon-add').style.display="none";
     document.getElementById('filebtn').style.display="block";
     document.getElementById('file-upload').style.display="none";
     document.getElementById('imgArea').style.height="355px";
     
    /* alert("dssad");*/
 });
 
 $("#imgArea").hover(function(){
	    	$("#icon-add").css("opacity","0");
	    	$("#dragContainer").css("opacity","1");
	    	$("#reloadbtn").css("opacity","1");
	    
},function(){
	
		$("#icon-add").css("opacity","1");
    	$("#dragContainer").css("opacity","0");
    	$("#reloadbtn").css("opacity","0");
    	
});
 
 $(".WriteCover-deleteButton").click(function(){
	 var previewImg =document.getElementById("banner-pic");
	 var Imgarea = document.getElementById("imgArea");
     var reloadBtn = document.getElementById("reloadbtn");
     var dragContainer = document.getElementById("dragContainer");
     var iconAdd = document.getElementById("icon-add");
     var fileBtn =  document.getElementById("filebtn");
     var fileUpload =  document.getElementById("file-upload");
	 previewImg.src= "";
	 previewImg.style.height="150px";
	 Imgarea.style.height="150px";
     previewImg.style.opacity = "0";
     reloadBtn.style.display="none";
     dragContainer.style.display="block";
     iconAdd.style.display="block";
     fileBtn.style.display="none";
     fileUpload.style.display="block";
	
 });

       