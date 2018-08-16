 
 //加载编辑器及自定义配置 
$(document).ready(function() {  
    $('#summernote').summernote({  
        height: 400,//初始化默认高度    
        width:630,
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
        ],//配置工具栏      
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
    formData.append("uploadIMG", file[0]); 
    $.ajax({                            
         type:'post',        
         url:'../../publicityManage/uploadIMG',                        
         cache: false,
         data:formData, 
         processData: false,
         contentType: false,
         dataType:'json',
         success: function(picture) { 
        	 //console.log(picture);
        	 if(picture.status == 0){        		
        		 $('#summernote').summernote('insertImage',picture.data); 
        	 }
         },  
         error:function(){                                                  
            alert("上传失败");                                                     
         } 
    });
}
function sendarticle(){
	var formData = new FormData();
	var sendFile = $('#file-upload').get(0).files[0];
	if (typeof (sendFile) != "undefined") {
		formData.append("coverpath", sendFile);
	}
	var title = $('#title').val();
	var content = $('#summernote').summernote('code');
	formData.append("title", title);
	formData.append("content", content);
	formData.append("source", "计算机学院");
	var url = "../../publicityManage/insertNews";
	submit(formData,url);
}
function submit(data,url){
	$.ajax({
		type : 'post',
		url : url,
		cache : false,
		data : data,
		processData : false,
		contentType : false,
		dataType : 'json', //请求成功后，后台返回图片访问地址字符串，故此以text格式获取，而不是json格式
		success : function(data) {
			alert(data.msg);
		},
		error : function() {
			alert("发布失败");
		}
	});
}
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
      
     
       var Max_Width = 630;
       var Max_Height = 350;
       var isAllow =false;
       
       fd.onload=function(){
    	   //获取上传图片宽高
    	   var data = this.result;
    	   var img = new Image();
    	   img.onload = function(){
    		 var w = img.width;
    		 var h = img.height;
    		 //判断是否符合大小
    		 isAllow = w >= Max_Width && h >= Max_Height;
    		 showTip(isAllow,data);
    		 console.log(w+":"+h);
    	   };
    	   img.src = data;
       };
      }else{alert("请选择图片上传");}
      }
 }
	function showTip(isAllow,url){
		var previewImg =document.getElementById("banner-pic");
	    var Imgarea = document.getElementById("imgArea");
	    var reloadBtn = document.getElementById("reloadbtn");
	    var dragContainer = document.getElementById("dragContainer");
	    var iconAdd = document.getElementById("icon-add");
	    var fileBtn =  document.getElementById("filebtn");
	    var fileUpload =  document.getElementById("file-upload");
	    if(isAllow){
	    	 //预览图片
	    	   previewImg.src= url;
	           previewImg.style.height="355px";
	           previewImg.style.opacity = "1";
	           reloadBtn.style.display="flex";
	           dragContainer.style.display="none";
	           iconAdd.style.display="none";
	           fileBtn.style.display="block";
	           fileUpload.style.display="none";
	           Imgarea.style.height="355px";
	    }else{
	    	$("#myModal").modal('show');
	    }
	   
	}
 $("#filebtn").on("change",function(){
	 var Max_Width = 630;
     var Max_Height = 350;
     var isAllow =false;
	 var reads= new FileReader();
     f=document.getElementById('filebtn').files[0];
     reads.readAsDataURL(f);
     reads.onload=function (e) {
    	//获取上传图片宽高
  	   var data = this.result;
  	   var img = new Image();
  	   img.onload = function(){
  		 var w = img.width;
  		 var h = img.height;
  		 //判断是否符合大小
  		 isAllow = w >= Max_Width && h >= Max_Height;
  		 showTip(isAllow,data);
  		 console.log(w+":"+h);
  	   };
  	   img.src = data;
     };
 });
 $("#file-upload").on("change",function(){
	 var Max_Width = 630;
     var Max_Height = 350;
     var isAllow =false;
	 var reads= new FileReader();
     f=document.getElementById('file-upload').files[0];
     reads.readAsDataURL(f);
     reads.onload=function (e) {
    	//获取上传图片宽高
  	   var data = this.result;
  	   var img = new Image();
  	   img.onload = function(){
  		 var w = img.width;
  		 var h = img.height;
  		 //判断是否符合大小
  		 isAllow = w >= Max_Width && h >= Max_Height;
  		 showTip(isAllow,data);
  		 console.log(w+":"+h);
  	   };
  	   img.src = data;
     };
     
    /* document.getElementById('banner-pic').style.height="355px";
     document.getElementById('banner-pic').style.opacity = "1";
     document.getElementById('reloadbtn').style.display="flex";
     document.getElementById('dragContainer').style.display="none";
     document.getElementById('icon-add').style.display="none";
     document.getElementById('filebtn').style.display="block";
     document.getElementById('file-upload').style.display="none";
     document.getElementById('imgArea').style.height="355px";*/
     
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

 $(".positionbtn").click(function(){
	$("#pModal").modal('show');
 });
 
 $(function() {

     var defaultData = [
       {
         text: '当前站点',
         href: '#source',
         tags: ['4'],
         nodes: [
           {
             text: '通知公示',
             href: '#notice',
             tags: ['0']
           },
           {
             text: '党内要闻',
             href: '#news',
             tags: ['0']
           },
           {
               text: '党内公示',
               href: '#news',
               tags: ['0']
           },
           {
                text: '院组织架构',
                href: '#yunit',
                tags: ['0'] 
           },
           {
            	text: '党支部组织架构',
                href: '#djunit',
               tags: ['0'] 
            }
         ]
       }
     ];

     var $checkableTree = $('#treeview-checkable').treeview({
       data: defaultData,
       showIcon: false,
       showCheckbox: true,
       onNodeChecked: function(event, node) {
    	   var selectNodes = getChildNodeIdArr(node); //获取所有子节点
    	   var flag = 1;
           if (selectNodes) { //子节点不为空，则选中所有子节点
               $('#treeview-checkable').treeview('checkNode', [selectNodes, { silent: true }]);
               
               for (x in node.nodes) {
            	   flag=0;
            	   console.log("length:"+node.nodes.length);
            	   if (!node.nodes[x].nodes) {
            		   //通过节点数组长度判断当前所选节点为全选还是组织结构节点，为4是全选，2为组织架构
            		   if(node.nodes.length==2){
            			   $("#"+node.nodes[x].nodeId).detach();
                		   $('#checkable-output').prepend('<li id='+node.nodes[x].nodeId+'> <b>当前站点 </b>下的  <b>组织架构</b>下的   <b>'+ node.nodes[x].text + '</b> 栏目 </li>');
                       }else{
	            		   $("#"+node.nodes[x].nodeId).detach();
	            		   $('#checkable-output').prepend('<li id='+node.nodes[x].nodeId+'> <b>当前站点 </b>下的  <b>'+ node.nodes[x].text + '</b> 栏目 </li>');
                       }
            		   console.log("if(!!node.nodes[x].nodes)::"+node.nodes[x].text );
            	   }
            	   if (node.nodes[x].nodes) {
                       var getNodeDieDai = node.nodes[x];
                       for (j in getNodeDieDai.nodes) {
                    	   console.log("if(node.nodes[x].nodes)::"+getNodeDieDai.nodes[j].text );
                    	   $("#"+node.nodes[x].nodes[j].nodeId).detach();
                    	   $('#checkable-output').prepend('<li id='+node.nodes[x].nodes[j].nodeId+'> <b>当前站点 </b>下的  <b>'+ node.nodes[x].text +'</b>下的<b> '+getNodeDieDai.nodes[j].text + '</b> 栏目 </li>');
                    	    
                       }
                   }  
               }
               if(flag==1){
            	   console.log("node:"+node.nodeId+":"+node.text); 
            	   $("#"+node.nodeId).detach();
            	   $("#n["+node.nodeId+"]").detach();
            	   $('#checkable-output').prepend('<li id='+node.nodeId+'> <b>当前站点 </b>下的  <b>'+ node.text + '</b> 栏目 </li>');
            	  
               }
            }
           var parentNode = $("#treeview-checkable").treeview("getNode", node.parentId);
           setParentNodeCheck(node);
           
       },
       onNodeUnchecked: function (event, node) {
    	   var selectNodes = getChildNodeIdArr(node); //获取所有子节点
    	   var flag = 1;
           if (selectNodes) { //子节点不为空，则取消选中所有子节点
               $('#treeview-checkable').treeview('uncheckNode', [selectNodes, { silent: true }]);
               
               for (x in node.nodes) {
            	   flag=0;
            	  if (!node.nodes[x].nodes) {
            		   $("#"+node.nodes[x].nodeId).detach();
                   }
            	   if (node.nodes[x].nodes) {
                       var getNodeDieDai = node.nodes[x];
                       for (j in getNodeDieDai.nodes) {
                    	    $("#"+node.nodes[x].nodes[j].nodeId).detach();
                       }
                   }  
               }
               if(flag==1){
            	   console.log("node:"+node.nodeId+":"+node.text);  
            	   $("#"+node.nodeId).detach();
               }
           }
       }
     });

    
    
     // Check/uncheck/toggle nodes
     $('#input-check-node').on('keyup', function (e) {
       checkableNodes = findCheckableNodess();
       $('.check-node').prop('disabled', !(checkableNodes.length >= 1));
     });

     $('#positionbtn').on('click', function (e) {
    	 $(".ptext").each(function() {

    	   var checkableNodes = $checkableTree.treeview('search', [$(this).text(), { ignoreCase: false, exactMatch: false } ]);
    	   $checkableTree.treeview('checkNode', [ checkableNodes, { silent: $('#chk-check-silent').is(':checked') }]);
	       
		    
    	 } );
    });

     $('#btn-uncheck-node.check-node').on('click', function (e) {
       $checkableTree.treeview('uncheckNode', [ checkableNodes, { silent: $('#chk-check-silent').is(':checked') }]);
     });

     $('#btn-toggle-checked.check-node').on('click', function (e) {
       $checkableTree.treeview('toggleNodeChecked', [ checkableNodes, { silent: $('#chk-check-silent').is(':checked') }]);
     });

     // Check/uncheck all
     $('#btn-check-all').on('click', function (e) {
       $checkableTree.treeview('checkAll', { silent: $('#chk-check-silent').is(':checked') });
     });

     $('#btn-uncheck-all').on('click', function (e) {
       $checkableTree.treeview('uncheckAll', { silent: $('#chk-check-silent').is(':checked') });
     });
     
     function getChildNodeIdArr(node) {
         var ts = [];
         if (node.nodes) {
             for (x in node.nodes) {
                 ts.push(node.nodes[x].nodeId);
                 if (node.nodes[x].nodes) {
                     var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);
                     for (j in getNodeDieDai) {
                         ts.push(getNodeDieDai[j]);
                     }
                 }
             }
         } else {
             ts.push(node.nodeId);
         }
         
         return ts;
     }

     function setParentNodeCheck(node) {
         var parentNode = $("#treeview-checkable").treeview("getNode", node.parentId);
         if (parentNode.nodes) {
             var checkedCount = 0;
             for (x in parentNode.nodes) {
                 if (parentNode.nodes[x].state.checked) {
                     checkedCount ++;
                 } else {
                     break;
                 }
             }
             if (checkedCount === parentNode.nodes.length) {
                 $("#treeview-checkable").treeview("checkNode", parentNode.nodeId);
                 setParentNodeCheck(parentNode);
             }
         }
     }
     
     $("#modalbtn").click(function(){
    	 /*console.log("#modalbtn");*/
    	 var vnode = $('#treeview-checkable').treeview('getChecked');
    	 $('#ppp').empty();
    	 for(x in vnode){
    		 console.log("x["+x+"]");
    		 if(vnode[x].text!="当前站点"&&vnode[x].text!="组织架构"){
    		   if(vnode[x].text=="院组织架构"){
    			   $('#ppp').prepend(
    	                     '<label class="position" id=x['+x+']><i class="fa fa-circle"></i>当前站点    —— 组织架构   —— <span class="ptext">'+vnode[x].text+'</span><span class="minusbtn"></span></label>');
    	    		
    		   }else if(vnode[x].text=="党支部组织架构"){
    			   $('#ppp').prepend(
  	                     '<label class="position" id=x['+x+']><i class="fa fa-circle"></i>当前站点    —— 组织架构   —— <span class="ptext">'+vnode[x].text+'</span><span class="minusbtn"></span></label>');
  	    		
  		      }else{
    		   $('#ppp').prepend(
                     '<label class="position" id=x['+x+']><i class="fa fa-circle"></i>当前站点    ——  <span class="ptext">'+vnode[x].text+'</span><span class="minusbtn"></span></label>');
    		   }
    		}
    	     console.log("treeview-node:"+vnode[x].text);
         }
     });
     
     $("#modalclose").click(function(){
    	 $checkableTree.treeview('uncheckAll', { silent: $('#chk-check-silent').is(':checked') });
     });
     
    /*$("span.minusbtn").each(function(){
    	
        $(this).click(function(){
        	$(this).parent("label").detach();
    	 });
     });*/
     $("#ppp").on("click",".minusbtn", function() {
         //do something here
    	 $(this).parent("label").detach();
     });
     
     

});

       