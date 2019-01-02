/*
 *  会议列表
 */
new Vue({
	el : "#msg",
	data: {
		datas: []
	},
	methods:{
		loadNewMessages: function() {
	
			var app = this;
			$.ajax({
				type:'get',
				url:'../../meetingList',//TODO 不明接口
				async : false,
				dataType: 'json',
				success: function(result){
					if (result.status == 0) {
						app.datas = result.data;
					}else{
						alert(result.msg);
					}
				}
			});
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
});
  $(document).ready(function() {
	var table = $('#msg').DataTable({
        responsive: true,
        "bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示  
        "bAutoWidth" : true, //是否自适应宽度  
        "aLengthMenu" : [5, 10, 20], //更改显示记录数选项 
        "iDisplayLength" : 5, //默认显示的记录数  
        "aoColumnDefs": [ { "bSortable": false, "aTargets": [ 0,6 ] }],
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
	
	var button = "<button type='button' class='btn btn-success tp' onclick='window.parent.aclick()'>" 
		+"<span class='glyphicon glyphicon-plus icon'></span><span class='caption'>发起会议</span></button> " 
	    +"&nbsp;&nbsp;&nbsp;&nbsp;"
		+"<button type='button' class='btn btn-primary tp'  id='allcheck' onclick='checkall()'>" 
		+"<span class='fa fa-check-square-o icon'></span><span class='caption'>全选</span></button> " 
		+"&nbsp;&nbsp;&nbsp;&nbsp;"
		+"<a class='btn btn-danger td' onclick='deleteall()'><span class='fa fa-trash-o icon'></span>" 
		+"<span class='caption'>取消会议</span></a> &nbsp;&nbsp;&nbsp;&nbsp;";
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
function cancel(){
	$('.popup_de').removeClass('bbox');
}
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
	var text="确定要取消所选会议吗?";
	document.getElementById('show_msg').innerHTML=text;
	$('.popup_de').addClass('bbox');
	$('.popup_de .btn-danger').one('click',function(){
		if(str!="")
			doDelete(str);
		else{
			alert("请至少选择一个会议");
			$('.popup_de').removeClass('bbox');
		}		
	})
}
function millisecondsToDateTime(ms){
	return new Date(ms).toLocaleString();
};
function deletemsg(obj){
	var tds = $(obj).parent().parent().parent().find('td');
	var label = tds.eq(1).find('label');
	var sid = label.eq(0).text();
	var text="确定要取消该会议吗?";
	document.getElementById('show_msg').innerHTML=text;
	$('.popup_de').addClass('bbox');
	$('.popup_de .btn-danger').one('click',function(){
		doDelete(sid);
	})
}
function doDelete(data){
	var url;
	
	$.ajax({   //TODO 接口缺失                        
		type:'post',        
        url:url+data, 
        dataType:'json',
        success: function(result) {  
			alert(result.msg);
			location.reload();	
        },
        error :function(){
        	alert("系统出错，删除失败！");
        	$('.popup_de').removeClass('bbox');
        }
   });
}
function doPass(data){
	var url;
	
	$.ajax({  //TODO 接口缺失                                     
		type:'post',        
        url:url+data,   
        dataType:'json',
        success: function(result) {  
			alert(result.msg);
			location.reload();	
        },
        error :function(){
        	alert("系统出错，审核通过失败！");
        	$('.popup_de').removeClass('bbox');
        }
   });
}