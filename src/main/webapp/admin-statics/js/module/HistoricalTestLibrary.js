$(function(){
	/*var curMenu = null, zTree_Menu = null;*/
	var setting = {
		view: {
			showLine: false,
			showIcon: false,
			selectedMulti: false,
			dblClickExpand: false,
			addDiyDom: addDiyDom
		},
		edit: {
			enable: true,
			showRemoveBtn: false,
			showRenameBtn: false
		},
		data: {
			keep: {
				parent:true,
				leaf:true
			},
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			beforeDrag: beforeDrag,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRemove: onRemove
		}
	};

	var zNodes =[
		{ id:1, pId:0, name:"所有题库", open:true},
		{ id:11, pId:1, name:"两学一做"},
		{ id:12, pId:1, name:"十九大报告"},
		{ id:13, pId:1, name:"习近平重要讲话"},
		{ id:14, pId:1, name:"党章"},
		{ id:15, pId:1, name:"党史"}
	];

	function addDiyDom(treeId, treeNode) {
		var spaceWidth = 5;
		var switchObj = $("#" + treeNode.tId + "_switch"),
		icoObj = $("#" + treeNode.tId + "_ico");
		switchObj.remove();
		icoObj.before(switchObj);

		if (treeNode.level > 1) {
			var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
			switchObj.before(spaceStr);
		}
	}

	function beforeClick(treeId, treeNode) {
		if (treeNode.level == 0 ) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandNode(treeNode);
			return false;
		}
		return true;
	}
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
	}
	function onRemove(e, treeId, treeNode) {
		showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	}
	function beforeRename(treeId, treeNode, newName) {
		if (newName.length == 0) {
			alert("节点名称不能为空.");
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			setTimeout(function(){zTree.editName(treeNode)}, 10);
			return false;
		}
		return true;
	}
	function showLog(str) {
		if (!log) log = $("#log");
		log.append("<li class='"+className+"'>"+str+"</li>");
		if(log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	function getTime() {
		var now= new Date(),
		h=now.getHours(),
		m=now.getMinutes(),
		s=now.getSeconds(),
		ms=now.getMilliseconds();
		return (h+":"+m+":"+s+ " " +ms);
	}

	//编辑
	function edit() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (nodes.length == 0) {
			alert("请先选择一个节点");
			return;
		}
		zTree.editName(treeNode);
		$("input[id*='treeDemo_']").height(25);
		$("input[id*='treeDemo_']").width(135);
		$("input[id*='treeDemo_']").change(function(){ //题库分类修改监听
			console.log("$('input[id*='treeDemo_']').val()"+$(this).val());
		});
	};
	function remove(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (nodes.length == 0) {
			alert("请先选择一个节点");
			return;
		}
		var callbackFlag = $("#callbackTrigger").attr("checked");
		zTree.removeNode(treeNode, callbackFlag);
		
		alert("是否确认删除"+treeNode.name);
	};
	$(document).ready(function(){
		var treeObj = $("#treeDemo");
		$.fn.zTree.init(treeObj, setting, zNodes);
		$("#edit").bind("click", edit);
		$("#remove").bind("click", remove);
		$("span[id*='treeDemo_']").click(function(){
			console.log($(this).text()+"was clicked");
			var data = {    
			        "branchId": null,
			        "categoryId": 1,
			        "userId": null,
			        "singleQuestion": [
			            {
			                "questionContent": "在习近平新时代中国特色社会主义思想指导下，中国共产党领导全国各族人民，统揽（），推动中国特色社会主义进入了新时代。",
			                "choice": [
			                    "A.伟大斗争、伟大工程、伟大事业、伟大梦想 ",
			                    "B.伟大斗争、伟大建设、伟大事业、伟大梦想",
			                    "C.伟大斗争、伟大工程、伟大发展、伟大梦想",
			                    "D.伟大斗争、伟大工程、伟大事业、伟大理想"
			                ],
			                "answer": [
			                    1
			                ]
			            },
			            {
			                "questionContent": "在（）指导下，中国共产党领导全国各族人民，统揽伟大斗争、伟大工程、伟大事业、伟大梦想，推动中国特色社会主义进入了新时代。",
			                "choice": [
			                    "A.毛泽东思想",
			                    "B.马克思列宁主义",
			                    "C.习近平新时代中国特色社会主义思想",
			                    "D.“三个代表”重要思想"
			                ],
			                "answer": [
			                    3
			                ]
			            }
			          ],
			          "multipleQuestion": [
				            {
				                "questionContent": "在习近平新时代中国特色社会主义思想指导下，中国共产党领导全国各族人民，统揽（），推动中国特色社会主义进入了新时代。",
				                "choice": [
				                    "A.伟大斗争、伟大工程、伟大事业、伟大梦想 ",
				                    "B.伟大斗争、伟大建设、伟大事业、伟大梦想",
				                    "C.伟大斗争、伟大工程、伟大发展、伟大梦想",
				                    "D.伟大斗争、伟大工程、伟大事业、伟大理想"
				                ],
				                "answer": [
				                    1
				                ]
				            },
				            {
				                "questionContent": "在（）指导下，中国共产党领导全国各族人民，统揽伟大斗争、伟大工程、伟大事业、伟大梦想，推动中国特色社会主义进入了新时代。",
				                "choice": [
				                    "A.毛泽东思想",
				                    "B.马克思列宁主义",
				                    "C.习近平新时代中国特色社会主义思想",
				                    "D.“三个代表”重要思想"
				                ],
				                "answer": [
				                    1,3
				                ]
				            }
				          ]
			           
	            };
			var name =$(this).text();
			previewLib(data,name);
		})
	});
	
	//上传题库
	$("#upload").click(function(){
		$("myModal").modal("show");
	})
	
	//自定义文件分类和文件分类选择切换
	var customGroup = document.getElementById("custom-group");
	var selectGroup = document.getElementById("select-group");
	function custom(){
		
		customGroup.style.display="flex";
		selectGroup.style.display="none";
	};

	function addsort(){
		customGroup.style.display="none";
		selectGroup.style.display="flex";

	};
	
		
		$('#tikutitle').bind("change",function(){	
		    /*console.log("$('#tikutitle') was clicked");*/
			checkisnull();
		});
		$("#chooseExam").change(function(){
			/*var filename = $(this).val().substring($(this).val().lastIndexOf("\\")+1);*/
			//检测上传文件的类型 
		
			var file = $(this).val();
			var strFileName=file.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名，不带后缀
			var FileExt=file.replace(/.+\./,"");//后缀名
			
			   
			       if (FileExt != 'xls' && FileExt != 'xlsx' ){
			            	$("#chooseExam").val("");
			            	$('#tikutitle').val("");
			                alert("只能上传.xls  .xlsx 类型的文件!"); 
			                return;  
			             
			        } else { 
			        	if(!$('#tikutitle').val()){
			     		   $('#tikutitle').val(strFileName);
			     		}
			     		$(".fl").text($(this).val());
			     		checkisnull();
			            return;
			        }   
			    
		});
		$("#mySelect").bind("change",function(){
	        /*console.log("$('#mySelect') was clicked");*/
	        checkisnull();
	    });
		//检查输入框是否为空
		function checkisnull(){
			var title = $('#tikutitle').val();
			var select = $("#mySelect").val();
			var file =$("#chooseExam").val();
			
			/*console.log("$('#tikutitle').val():"+title+":"
				    +"$('#chooseExam').val():"+file+":"
				    +"$('#mySelect').val():"+select
				   );*/
			
			if(title&&select!="请选择文件分类"&&file){
				$('#mUploadbtn').attr('disabled', false);
			}else{
			    $('#mUploadbtn').attr('disabled', true);
			}
		};

		//题库上传按钮
		$("#mUploadbtn").click(function(){
			$("#previewModal").modal('show');
			console.log("#mUploadbtn was clicked");	
		});
		//清除弹窗原数据
		$("#previewModal").on("hidden.bs.modal", function() {
		    $(this).removeData("bs.modal");
		});
		$('#previewModal').on('hide.bs.modal', function () {
			$(this).removeData("bs.modal");
			})
			
	   $("tbody tr").on('click' , function(){
		var check = $(this).find("input[type='checkbox']");
		if ($(check).is(':checked')) {
			$(check).prop('checked', false);
		} else {
			$(check).prop('checked', true);
		}
	   })
	   
	 //文件上传限制
	   function fileChange(target){  
	   	//检测上传文件的类型 
	   	var fileName = document.all.up_file.value;
	   	     var ext,idx;   
	   	    if (fileName == ''){  
	   	       /*document.all.submit_upload.disabled=true; 
	   	        alert("请选择需要上传的文件!"); */ 
	   	        return; 
	   	    } else {   
	   	        idx = fileName.lastIndexOf(".");   
	   	        if (idx != -1){   
	   	            ext = imgName.substr(idx+1).toUpperCase();   
	   	            ext = ext.toLowerCase( ); 
	   	           // alert("ext="+ext);
	   	            if (ext != 'xls' && ext != 'xlsx' ){
	   	            	target.value="";
	   	                alert("只能上传.xls  .xlsx 类型的文件!"); 
	   	                return;  
	   	            }   
	   	        } else { 
	   	        	console.log("target.value="+target.value); 
	   	            return;
	   	        }   
	   	    }
	   }
		
		//预览题库
		function previewLib(data,name){
			var test_id1 = name;
			console.log("name:"+name+":"+test_id1)
            test1(test_id1,data);
		}
		
		
		function edittest1(test_id,data) {
		    if (test_id != "") {
		        console.log(data);
		        var titleB = test_id;
		        var sq = data.singleQuestion;
		        var mq = data.multipleQuestion;
		        
		        var test_box = '';
		        var sqtopic_box ='';
		        $.each(sq, function (h, sq) {
		            var title = sq.questionContent;
		            var options = sq.choice;
		            var answer = sq.answer;
		            var option_box = '';
		                $.each(options, function (j, option) {
		                        var op = convert(j);
		                        option_box +='<div class = "jxz-option radio" >' 
		                        	        +'<label class="opl">'
		                        	        +'<input name = "test'+h+''+j+'" type = "radio" value = "'+op+'" > '
		                        	        +'<input type = "text" class="form-control optiontext" value="'+option+'"/>'
		                        	        /*+'<span class="opclosebtn">x</span>*/+'</label>'
		                        	        +'</div >';
		                });
		             var answer_op = '';
		             if(answer==1){
		            	 answer_op +='A';
		             }else if(answer==2){
		            	 answer_op += 'B';
		             }else if(answer==3){
		            	 answer_op += 'C';
		             }else if(answer==4){
		            	 answer_op += 'D';
		             }
		             
		             sqtopic_box += '<div class = "edittestCon"  data-type = "sq" data-answer="'+answer_op+'">'
		               + '<textarea  class = " form-control jxz-title">'+sq.questionContent +'</textarea>'
		               + '<span class="closediv" onclick="closediv(this)">X</span>'+option_box
			  		   + '<div class="topic-answer">正确答案：<input type="text" class="form-control answer_op" value="'+answer_op+'"/>' 
			  		   + '</div><span class="savediv" onclick="savediv(this)"><i class="check"></i></span></div>';
		             
		        });
		       
		        var mqtopic_box ='';
		        $.each(mq, function (h, mq) {
		            var title = mq.questionContent;
		            var options = mq.choice;
		            var answer = mq.answer;
		            var option_box = '';
		                $.each(options, function (j, option) {
		                        var op = convert(j);
		                        option_box +='<div class = "jxz-option radio" >' 
		                        	        +'<label class="opl">'
		                        	        +'<input name = "test'+h+''+j+'" type = "radio" value = "'+op+'" /> '
		                        	        +'<input type = "text" class="form-control optiontext"  value="'+option+'"/>'
		                        	        /*+'<span class="opclosebtn">x</span>*/+'</label>'
		                        	        +'</div >';
		                });
		                var answer_op = '';
		                
		                $.each(answer, function (i, aw) {
		                	  if(aw==1){
		                      	 answer_op +=(i + 1) ? 'A':'A ';
		                       }else if(aw==2){
		                      	 answer_op += (i + 1) ? 'B':'B ';
		                       }else if(aw==3){
		                      	 answer_op += (i + 1) ? 'C':'C ';
		                       }else if(aw==4){
		                      	 answer_op += (i + 1) ? 'D':'D ';
		                       } 
		                      /*answer_op += answer.length == (i + 1) ? aw : aw + " ";*/
		                }); 
		                
		                mqtopic_box += '<div class = "edittestCon"  data-type = "sq" data-answer="'+answer_op+'">'
		                + '<textarea  class = " form-control jxz-title">'+mq.questionContent +'</textarea>'
		                + '<span class="closediv" title="删除" onclick="closediv(this)">X</span>'+option_box
		 	  		    + '<div class="topic-answer">正确答案：<input type="text" class="form-control answer_op" value="'+answer_op+'"/>' 
				  		   + '</div><span class="savediv "title="保存" onclick="savediv(this)"><i class="check"></i></span></div>';
		     
		          });
		          test_box+='<div class="jxz-box"><h4 class="tesTitle">单项选择题</h4 >'+sqtopic_box 
		                   +'</div>'
		                   +'<div class="jxz-box"><h4 class="tesTitle">多项选择题</h4 >'+mqtopic_box 
		                   +'</div>';
		          
		          var test_html='<div class="page-header"><h3 class="text-center">'+ titleB +'</h3>'
		                     +'<div class="btngroup">'
		                     +'<button type="button" class="return"><i class="fa fa-sign-out"></i>退出修改</button>'
		                     +'<button type="button" class="saveedit"><i class="fa fa-save"></i>保存修改</button></div></div>'
		                     +'<div class="test-form-box" >'+test_box+'</div>';
		            $('#previewArea').html(test_html)
		            
		          }else{alert("试题获取失败！");}
		    
		    $(".return").click(function(){
	        	 test1(titleB,data);
	    	 });
		 } 
		function test1(test_id,data) {
		    if (test_id != "") {
		        console.log(data);
		        var titleB = test_id;
		        var sq = data.singleQuestion;
		        var mq = data.multipleQuestion;
		        
		        var test_box = '';
		        var sqtopic_box ='';
		        $.each(sq, function (h, sq) {
		            var title = sq.questionContent;
		            var options = sq.choice;
		            var answer = sq.answer;
		            var option_box = '';
		                $.each(options, function (j, option) {
		                        var op = convert(j);
		                        option_box +='<div class = "jxz-option radio" >' 
		                        	 +'<label >'
		                 	         +'<input name = "test'+h+''+j+'" type = "radio" value = "'+op+'" > '+option + '</label>'
		                             +'</div >';
		                });
		             var answer_op = '';
		             if(answer==1){
		            	 answer_op +='A';
		             }else if(answer==2){
		            	 answer_op += 'B';
		             }else if(answer==3){
		            	 answer_op += 'C';
		             }else if(answer==4){
		            	 answer_op += 'D';
		             }
		             
		             sqtopic_box += '<div class = "testCon"  data-type = "sq" data-answer="'+answer_op+'">'
					  		   + '<h4 class = "jxz-title" >'+sq.questionContent +'</h4>'+option_box
					  		   + '<div class="topic-answer"><p>正确答案：'+answer_op+'</p>' 
					  		   + '</div></div>';
		             
		        });
		       
		        var mqtopic_box ='';
		        $.each(mq, function (h, mq) {
		            var title = mq.questionContent;
		            var options = mq.choice;
		            var answer = mq.answer;
		            var option_box = '';
		                $.each(options, function (j, option) {
		                        var op = convert(j);
		                        option_box +='<div class = "jxz-option radio" >' 
		                        	        +'<label >'
		                        	        +'<input name = "test'+h+''+j+'" type = "radio" value = "'+op+'" > '+option + '</label>'
		                                    +'</div >';
		                });
		                var answer_op = '';
		                
		                $.each(answer, function (i, aw) {
		                	  if(aw==1){
		                      	 answer_op +=(i + 1) ? 'A':'A ';
		                       }else if(aw==2){
		                      	 answer_op += (i + 1) ? 'B':'B ';
		                       }else if(aw==3){
		                      	 answer_op += (i + 1) ? 'C':'C ';
		                       }else if(aw==4){
		                      	 answer_op += (i + 1) ? 'D':'D ';
		                       } 
		                      /*answer_op += answer.length == (i + 1) ? aw : aw + " ";*/
		                }); 
		                
		                mqtopic_box += '<div class = "testCon"  data-type = "sq" data-answer="'+answer_op+'">'
				  		   + '<h4 class = "jxz-title" >'+mq.questionContent +'</h4>'+option_box
				  		   + '<div class="topic-answer"><p>正确答案：'+answer_op+'</p>' 
				  		   + '</div></div>';
		     
		          });
		          test_box+='<div class="jxz-box"><h4 class="tesTitle">单项选择题</h4 >'+sqtopic_box +'</div>'
		                   +'<div class="jxz-box"><h4 class="tesTitle">多项选择题</h4 >'+mqtopic_box +'</div>';
		          
		          var test_html='<div class="page-header"><h3 class="text-center">'+ titleB +'</h3></div>'
		                     +'<div class="test-form-box" title="双击进入编辑">'+test_box+'</div>';
		            $('#previewArea').html(test_html)
		            }else{alert("试题获取失败！");}
		         $(".test-form-box").dblclick(function(){
		        	 edittest1(titleB,data);
		    	 });
		}

		
})

		

