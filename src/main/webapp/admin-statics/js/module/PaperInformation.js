/*  new Vue({
		el : "#msg",
	data: {
		newMessages: []
	},
	methods:{
		loadNewMessages: function() {
			var app = this;
			var m = {};
			$.ajax({
				type:"get",
				url: "",
				async : false,
				dataType: 'json',
				success: function(result){
					if (result.status == 0) {
						app.newMessages = result.data;
					}else{
						alert(result.msg);
					}
				}
			});
		},
		timeFormat: function(ms){
			// 毫秒转日期时间
			return millisecondsToDateTime(ms);
		},
		titleFormat: function(msg){
			// 长度超过12，截取12个字符
			if(msg.length<=12){
				return msg;
			}				
			return msg.substr(0,11)+"···";
		}
	},
	created: function () {
		this.loadNewMessages();
	}
});*/
  $(document).ready(function() {
	var table = $('#msg').DataTable({
        responsive: true,
        "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
        "bAutoWidth" : true, //是否自适应宽度  
        "aLengthMenu" : [5, 10, 20], //更改显示记录数选项 
        "iDisplayLength" : 5, //默认显示的记录数  
        "aoColumnDefs": [ { "bSortable": false, "aTargets": [ 0,10 ] }],
        "order": [[ 1, 'asc' ]],
        "sDom": '<"row" <"col-sm-8" <"#add">> <"col-sm-4" <"row" <"col-sm-6" l> <"col-sm-6" f>>>>t<"row" <"col-sm-6" i> <"col-sm-6" p>>',
        language: {
            search: "搜索:",
            sInfo: "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            sLengthMenu: "显示 _MENU_ 项结果",
            sZeroRecords: "没有匹配结果",
            sInfoEmpty: "显示第 0 至 0 项结果，共 0 项",
            sInfoFiltered: "(由 _MAX_ 项结果过滤)",
            sEmptyTable: "表中数据为空",
            oPaginate: {
                sPrevious: "上页",
                sNext: "下页",
            }
        },
        "fnDrawCallback": function() {
        	$(this).find('input[type=checkbox]').removeAttr('checked');
		},
        
       
		
    });
	
	var button = "<a class='btn btn-info ts' onclick='addlibary()'><span class='fa fa-upload icon'></span><span class='caption'>上传题库</span></a> &nbsp;&nbsp;&nbsp;&nbsp;"
		+"<a class='btn btn-success ts' id='addexam' href='../../admin-view/workbench/ExamAdd.html'><span class='glyphicon glyphicon-plus icon'></span><span class='caption'>发布考试</span></a> &nbsp;&nbsp;&nbsp;&nbsp;"
		+"<button type='button' class='btn btn-warning tp' id='historyexam'><span class='fa fa-clock-o icon'></span><span class='caption'>历史题库</span></button> &nbsp;&nbsp;&nbsp;&nbsp;"
		+"<button type='button' class='btn btn-primary tp'  id='allcheck' onclick='checkall()'><span class='fa fa-check-square-o icon'></span><span class='caption'>全选</span></button> &nbsp;&nbsp;&nbsp;&nbsp;"
		+"<a class='btn btn-danger td' onclick='deleteall()'><span class='fa fa-trash-o icon'></span><span class='caption'>删除</span></a>";
	document.getElementById("add").innerHTML = button;
	$('#msg tbody').on( 'mouseenter', 'td', function () {
	var colIdx = table.cell(this).index().column;
	$( table.cells().nodes() ).removeClass( 'highlight' );
	$( table.column( colIdx ).nodes() ).addClass( 'highlight' );
	} );
	$("#all").click(function(){
	$('[name=all]:checkbox').prop('checked',this.checked);//checked为true时为默认显示的状态
	});
	$("#historyexam").click(function(){
		window.location.href="../../admin-view/workbench/HistoricalTestLibrary.html";
	})

});   
function checkall(){
	var all = $('[name=all]:checkbox');
	for(var i=0;i<all.length;i++){
		all[i].checked=true;
	}
}
function deleteall(){
	var all = $('[name=all]:checkbox');
	var str = "";
	for(var i=1;i<all.length;i++){
		if(all[i].checked)
			str = str+"&"+all[i].value;
	}
	var text="确定要删除所选账号吗?";
	document.getElementById('show_msg').innerHTML=text;
	$('.popup_de').addClass('bbox');
	$('.popup_de .btn-danger').one('click',function(){
		if(str!="")
			doDelete(str);
		else{
			alert("请至少选择一个账号");
			$('.popup_de').removeClass('bbox');
		}		
	})
}
function millisecondsToDateTime(ms){
	return new Date(ms).toLocaleString();
};
function deletemsg(obj){
	var tds = $(obj).parent().parent().parent().find('td');
	var center = tds.eq(1).find('center');
	var rid = center.eq(0).text();
	var text="确定要删除该账号吗?";
	document.getElementById('show_msg').innerHTML=text;
	$('.popup_de').addClass('bbox');
	$('.popup_de .btn-danger').one('click',function(){
		doDelete(rid);
	})
}
function doDelete(data){
	$.ajax({                            
		type:'post',        
        url:'../../userManage/deleteUserByBranch/'+data, 
        dataType:'json',
        success: function(result) {  
			alert(result.msg);
			location.reload();	
        },
        error :function(){
        	alert("系统出错，删除失败！");
        }
   });
}

//题库上传
function addlibary(){
	$("#myModal").modal('show');
 };
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
$(function() {
	
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
		    
		    //检测上传文件的大小        
		   /* var isIE = /msie/i.test(navigator.userAgent) && !window.opera;  
		    var fileSize = 0;           
		    if (isIE && !target.files){       
		        var filePath = target.value;       
		        var fileSystem = new ActiveXObject("Scripting.FileSystemObject");          
		        var file = fileSystem.GetFile (filePath);       
		        fileSize = file.Size;      
		    } else {      
		        fileSize = target.files[0].size;       
		    }     

		    var size = fileSize / 1024*1024;   

		    if(size>(1024*200)){    
		    document.all.submit_upload.disabled=true;
		        alert("文件大小不能超过200KB");   
		    }else{
		    document.all.submit_upload.disabled=false;
		    }    */
		}     
	
});
 