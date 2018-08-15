/*
 * 取路径参数
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var typename = getUrlParam('location');
var ftpurl = "http://172.21.95.5:19091/";

$(document).ready(function() {
	$(function(){
		if(typename == "rp"){
			document.getElementById("rp-title").innerHTML = "思想汇报";
			$("#type").hide();
		}
		else if(typename == "fb"){
			document.getElementById("rp-title").innerHTML = "反馈";
		}
	})
	
    $('#summernote').summernote({
        height:400,
    	lang: 'zh-CN',
    	callbacks: {  
            onImageUpload: function(file) {  //图片默认以二进制的形式存储到数据库，调用此方法将请求后台将图片存储到服务器，返回图片请求地址到前端
            	sendfile(file);           	
            }  
        }
  	});
});
function sendfile(file){
	//将图片放入Formdate对象中                                         
    var formData = new FormData();  
    //‘picture’为后台获取的文件名，file[0]是要上传的文件
    formData.append("upload_file", file[0]); 
    $.ajax({                            
         type:'post',        
         url:'../study/upload.do',                        
         cache: false,
         data:formData, 
         processData: false,
         contentType: false,
         dataType:'json',
         success: function(picture) {  
        	 if(picture.status == 0){
        		 $('#summernote').summernote('insertImage',ftpurl+picture.data.uri); 
        	 }
         },  
         error:function(){                                                  
            alert("上传失败");                                                     
         } 
    });
}
function submit(){
	if(typename == "rp"){
		rpsubmit();
	}
	else if(typename == "fb"){
		fbsubmit();
	}
}
function rpsubmit(){
	var title = $("#title").val();
	var content = $('#summernote').summernote('code');
	var select = $('#selected').val();
	$.ajax({
		type:'post',        
        url:'../report/insertReport',
        data: JSON.stringify({
        	"title":title,
        	"content":content
        }),
        contentType: "application/json;charset=utf-8",
        dataType:'json',
        success: function(result){			
			alert(result.msg);
		}
	})
}
function fbsubmit(){
	var title = $("#title").val();
	var content = $('#summernote').summernote('code');
	$.ajax({
		type:'post',        
		url:'../report/insertReport',
	    data: JSON.stringify({
	        "title":title,
	        "content":content
	    }),
	    contentType: "application/json;charset=utf-8",
        dataType:'json',
        success: function(result){			
			alert(result.msg);
		}
	})
}