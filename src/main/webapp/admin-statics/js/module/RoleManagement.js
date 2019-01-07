new Vue({
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
				url: "../../role/list",
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
		},
		deletemsg:function(obj){
			deletemsg(obj);
		},
		checkPermission:function(roleId){
			checkPermission(roleId);
		},
		updatePermission:function(roleId){
			updatePermission(roleId);
		}
	},
	created: function () {
		this.loadNewMessages();
	}
});
$(function(){
    //弹出框取消按钮事件
	$('.popup_de .btn_cancel').click(function(){
		$('.popup_de').removeClass('bbox');
	});
	//弹出框关闭按钮事件
	$('.popup_de .popup_close').click(function(){
		$('.popup_de').removeClass('bbox');
	})
})

$(document).ready(function() {
	var table = $('#msg').DataTable({
        responsive: true,
        "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
        "bAutoWidth" : true, //是否自适应宽度  
        "aLengthMenu" : [5, 10, 20], //更改显示记录数选项 
        "iDisplayLength" : 5, //默认显示的记录数  
        "aoColumnDefs": [ { "bSortable": false, "aTargets": [ 0,3 ] }],
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
        	$(this).find('input[type=checkbox]').removeProp('checked');
		}
    });   
	var button = "<button onclick='addInfo()' class='btn btn-success ts'><span class='glyphicon glyphicon-plus icon'></span><span class='caption'>新增角色</span></button> &nbsp;&nbsp;&nbsp;&nbsp;"
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

});   
function checkall(){
	var all = $('[name=all]:checkbox');
	for(var i=0;i<all.length;i++){
		all[i].checked=!all[i].checked;
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
	
	var text="确定要删除该账号吗?";
	document.getElementById('show_msg').innerHTML=text;
	$('.popup_de').addClass('bbox');
	$('.popup_de .btn-danger').one('click',function(){
		doDelete(obj);
	})
}
function doDelete(data){
	$.ajax({                            
		type:'post',        
        url:'../../role/delete?roleId='+data, 
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

function addInfo(){
	var text="<input type='text' placeholder='请输入角色名称' name='roleName' id='roleName' />";
	document.getElementById('show_msg').innerHTML=text;
	$('.popup_de').addClass('bbox');
	$('.popup_de .btn-danger').one('click',function(){
		var name = $.trim($("#roleName").val());
		if(name == ""){
			alert("角色名称不能为空");
			return;
		}
		$.ajax({
			type:"post",
			data:{
				roleName:name
			},
			url:"../../role/insert",
			async:true,
			success:function(res){
				if(res.status == 0){
					alert(res.msg)
					location.reload();
				}
				
			}
		});
	})
}

var modal = new Vue({
	el:"#myModal",
	data:{
		content:[]
	},
	methods:{
		
	},
	created:function(){
		
	}
})

var modalTwo = new Vue({
	el:"#myModalTwo",
	data:{
		contentTwo:[],
		roleId:null
	},
	methods:{
		ToSubmitCheckBox:function(roleId){
			ToSubmitCheckBox(roleId);
		}
	},
	created:function(){
		
	}
})

/**
 * 查看权限
 */
function checkPermission(roleId){
	$.ajax({
		type:"get",
		url:"../../role/permission/"+roleId,
		async:true,
		success:function(res){
			console.log(res);
			modal.content = res.data;
		}
	});
}

function updatePermission(roleId){
	$.ajax({
		type:"get",
		url:"../../permission/list",
		async:true,
		success:function(res){
			//console.log(res)
			modalTwo.contentTwo = res.data;
			modalTwo.roleId = roleId;
		}
	});
	
}

function ToSubmitCheckBox(roleId){
	var all = $('[name=permissionId]:checkbox');
	var permissionId = new Array();
	for(var i=1;i<all.length;i++){
		if(all[i].checked){
			var x = parseInt(all[i].value);
			permissionId.push(x);
		}
	}
	console.log(permissionId)
	$.ajax({
		type:"post",
		data:{
			roleId:roleId,
			permissionId:permissionId
		},
		traditional:true,
		url:"../../role/update_role_permission",
		async:true,
		success:function(res){
			//console.log(res)
			location.reload();
		}
	});
}
function selectall(){
	var all = $('[name=permissionId]:checkbox');
	for(var i=0;i<all.length;i++){
		all[i].checked=!all[i].checked;
	}
}
