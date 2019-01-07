 var checkedLabelID = new Array();
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
		formData.append("upload_img", sendFile);
	}else{
		formData.append("upload_img", "");
	}
	
	var fileOfDoc = $("#lefile").get(0).files[0];
	if (typeof (fileOfDoc) != "undefined") {
		formData.append("upload_file", fileOfDoc);
	}else{
		formData.append("upload_file", "");
	}
	
	var title = null;
	var content = null;
	title = $('#title').val();
	content = $('#summernote').summernote('code');
	if(title == ""){
		alert("标题不能为空！");
		return;
	}
	
	
	//获取所选的用户id
	var userID = new Array();
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
	.getCheckedNodes(true), v = "";
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].check_Child_State == -1 && nodes[i].id >=100){//可能存在的bug
			userID.push(parseInt(nodes[i].id));
		}       
    }
	formData.append("userId",userID);
	
	
	if(content == "<p><br></p>"){
		alert("内容不能为空！");
		return;
	}
	formData.append("label_id",checkedLabelID);
	formData.append("video_title", title);
	formData.append("video_introduction", content);
	var url = "../../study/upload_study_video.do";
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
		success : function(res) {
			if(res.status == 0)
				{
					alert(res.data);
				location.reload();
}
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

var labels = new Vue({
	el:"#lebel_block",
	data:{
		labels:[]
	},
	methods:{
		change:function(e){
			changeLabelState(e);
		}
	},
	created:function(){
		var that = this;
		$.ajax({
		type:"POST",
		url:"../../study/get_labels.do",
		async:true,
		success:function(res){
			if(res.status == 0)
				console.log(res.data)
				that.labels = res.data;
		}
	});
	}
})


/**
 * 选择标签
 * @param {Object} e
 */
function changeLabelState(e){
	//console.log(e.target.dataset.id)
	//console.log(e)
	//console.log((e.target.className))
	
	if(e.target.className == "span-em")
		{
			e.target.className = "span-em active";
			checkedLabelID.push(e.target.dataset.id);
		}
	else if(e.target.className == "span-em active")
	{
		e.target.className = "span-em";
		//var arr = ['a','b','c','d'];
		checkedLabelID.splice($.inArray(e.target.dataset.id,checkedLabelID),1);
	}
	console.log(checkedLabelID,checkedLabelID.length)
}


$(function() {
	var setting = {
		check : {
			enable : true,
		/* chkboxType: {"Y":"", "N":""} */
		},
		view : {
			dblClickExpand : false,
			nameIsHTML : true, // 允许name支持html
			selectedMulti : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			beforeClick : beforeClick,
			onCheck : onCheck
		}
	};
	
	var zNodes = [ {
		id : 1,
		pId : 0,
		name : "软件工程专业党支部",
		open : true
	}, {
		id : 2,
		pId : 0,
		name : "计算机科学与技术专业党支部",
		open : true
	}, {
		id : 3,
		pId : 0,
		name : "网络工程专业党支部",
		open : true
	}, {
		id : 4,
		pId : 0,
		name : "物联网专业党支部",
		open : true
	}, {
		id : 5,
		pId : 0,
		name : "数据科学与大数据技术专业党支部",
		open : true
	}, {
		id : 11,
		pId : 1,
		name : "正式党员"
	}, {
		id : 12,
		pId : 1,
		name : "预备党员"
	}, {
		id : 13,
		pId : 1,
		name : "发展对象"
	}, {
		id : 14,
		pId : 1,
		name : "积极分子"
	}, {
		id : 21,
		pId : 2,
		name : "正式党员"
	}, {
		id : 22,
		pId : 2,
		name : "预备党员"
	}, {
		id : 23,
		pId : 2,
		name : "发展对象"
	}, {
		id : 24,
		pId : 2,
		name : "积极分子"
	}, {
		id : 31,
		pId : 3,
		name : "正式党员"
	}, {
		id : 32,
		pId : 3,
		name : "预备党员"
	}, {
		id : 33,
		pId : 3,
		name : "发展对象"
	}, {
		id : 34,
		pId : 3,
		name : "积极分子"
	}, {
		id : 41,
		pId : 4,
		name : "正式党员"
	}, {
		id : 42,
		pId : 4,
		name : "预备党员"
	}, {
		id : 43,
		pId : 4,
		name : "发展对象"
	}, {
		id : 44,
		pId : 4,
		name : "积极分子"
	}, {
		id : 51,
		pId : 5,
		name : "正式党员"
	}, {
		id : 52,
		pId : 5,
		name : "预备党员"
	}, {
		id : 53,
		pId : 5,
		name : "发展对象"
	}, {
		id : 54,
		pId : 5,
		name : "积极分子"
	}, 

	];
	
	$.ajax({
		type : "get",
		url : "../../userManage/userListByBranch",
		async : false,
		dataType : 'json',
		success : function(result) {
			if (result.status == 0) {
				data = result.data;
				//alert("查询成功");
				$.each(data,function(index,item){
					//alert(item.userId);
					if(item.roleId!=0){
						parentId = item.branchId*10+item.roleId;					
						zNodes.push({
			                id:item.userId,  //本身id
			                pId:parentId, //父级id
			                name:item.realName//显示的名称
			            });	
					}
				})
				      
			} else {
				alert(result.msg);
			}
		}
	});

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		fuzzySearch('treeDemo', '#key', null, true); // 初始化模糊搜索方法

	});
	
})

function beforeClick(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function onCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
			.getCheckedNodes(true), v = "";
	var parentId = -1;
	var flag = 0;
	/*console.log(nodes.length + ":" + nodes[0].name + ":"+ nodes[nodes.length - 1].name);*/

	for (var i = 0, l = nodes.length; i < l; i++) {
		/*console.log("nodes[i]：" + nodes[i].name + "::::" + i);
		console.log("nodes[i].check_Child_State：" + nodes[i].check_Child_State);*/
		if (nodes[i].level == 0) {
			flag = 0;
		}
		if (nodes[i].check_Child_State == 2) {// 子节点全被选中

			//console.log("node[" + i + "].getParentNode():"+ nodes[i].getParentNode());
			if (nodes[i].level == 0) {
				v += nodes[i].name + ",";
				flag = 1;
			}

			if (flag == 1) {
				parentId = i;
			} else {
				v += nodes[i].name + ",";
				parentId = i;
			}

		} else if (nodes[i].check_Child_State == -1) {// 子节点没被选中
			if (nodes[parentId] != nodes[i].getParentNode())
				v += nodes[i].name + ",";
		} else if (nodes[i].check_Child_State == 1) {
			v += nodes[i].name + ",";

		}
	}
	if (v.length > 0)
		v = v.substring(0, v.length - 1);
	var cityObj = $("#citySel");
	cityObj.attr("value", v);
}

function showMenu() {
	var cityObj = $("#citySel");
	var cityOffset = $("#citySel").offset();
	$("#menuContent").css({
		left : cityOffset.left + "px",
		top : cityOffset.top + cityObj.outerHeight() + "px",
		"z-index" : 99999
	}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "citySel"
			|| event.target.id == "menuContent" || $(event.target).parents(
			"#menuContent").length > 0)) {
		hideMenu();
	}
}
       