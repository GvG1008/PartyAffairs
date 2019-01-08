function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var branchId = getUrlParam("branchID")
new Vue({
		el : "#msg",
	data: {
		newMessages: []
	},
	methods:{
		loadNewMessages: function(id) {
			var app = this;
			var m = {};
			$.ajax({
				type:"get",
				url: "../../partyalbum/"+id,
				async : false,
				dataType: 'json',
				success: function(result){
					if (result.status == 0) {
						console.log(result)
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
		}
	},
	created: function () {
		this.loadNewMessages(branchId);
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
        "aoColumnDefs": [ { "bSortable": false, "aTargets": [ 0,5 ] }],
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
	var button = "<a href='AddPartyPhotos.html' class='btn btn-success ts'><span class='glyphicon glyphicon-plus icon'></span><span class='caption'>创建相册</span></a> &nbsp;&nbsp;&nbsp;&nbsp;"
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
	var text="确定要删除所选相册吗?";
	document.getElementById('show_msg').innerHTML=text;
	$('.popup_de').addClass('bbox');
	$('.popup_de .btn-danger').one('click',function(){
		if(str!="")
			doDelete(str);
		else{
			alert("请至少选择一个相册");
			$('.popup_de').removeClass('bbox');
		}		
	})
}
function millisecondsToDateTime(ms){
	return new Date(ms).toLocaleString();
};
function deletemsg(obj){
	
	var text="确定要删除该相册吗?";
	document.getElementById('show_msg').innerHTML=text;
	$('.popup_de').addClass('bbox');
	$('.popup_de .btn-danger').one('click',function(){
		doDelete(obj);
	})
}
function doDelete(data){
	$.ajax({                            
		type:'delete',        
        url:'../../partyalbum/'+data,
        success: function(result) {
        	console.log(result)
				if(result.status == 0){
					alert("删除成功")
					location.reload();	
				}else if(result.status == 1){
					alert("删除出错,您可能无法删除此相册")
				}
        },
        error :function(){
        	alert("系统出错，删除失败！");
        }
   });
}

