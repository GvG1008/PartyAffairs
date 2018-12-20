function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var examId = getUrlParam('id');
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
						if(item.branchId==1){
							parentId = item.branchId*10+item.roleId;					
							zNodes.push({
				                id:item.userId,  //本身id
				                pId:parentId, //父级id
				                name:item.realName//显示的名称
				            });	
						}else if(item.branchId==2){
							parentId = item.branchId*10+item.roleId;
							zNodes.push({
				                id:item.userId,  //本身id
				                pId:item.parentId, //父级id
				                name:item.realName//显示的名称
				            });	
						}else if(item.branchId==3){
							parentId = item.branchId*10+item.roleId;
							zNodes.push({
				                id:item.userId,  //本身id
				                pId:item.parentId, //父级id
				                name:item.realName//显示的名称
				            });	
						}else if(item.branchId==4){
							parentId = item.branchId*10+item.roleId;
							zNodes.push({
				                id:item.userId,  //本身id
				                pId:item.parentId, //父级id
				                name:item.realName//显示的名称
				            });	
						}else if(item.branchId==5){
							parentId = item.branchId*10+item.roleId;
							zNodes.push({
				                id:item.userId,  //本身id
				                pId:item.parentId, //父级id
				                name:item.realName//显示的名称
				            });	
						}
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
	
	//弹出框取消按钮事件
	$('.popup_de .btn_cancel').click(function(){
		$('.popup_de').removeClass('bbox');
	});
	//弹出框关闭按钮事件
	$('.popup_de .popup_close').click(function(){
		$('.popup_de').removeClass('bbox');
	})

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
		top : cityOffset.top + cityObj.outerHeight() + "px"
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
var ExamContent = new Vue({
	el:"#ExamContent",
	data:{
		select:[],
		examInfo:[]
	},
	created:function(){
		this.loadExamcategory();
		this.getExamInfo();
	},
	methods : {
		loadExamcategory:function(){
			var self = this;
			$.ajax({
				type : "get",
				url : "../../examcategory",
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.status == 0) {
						self.select = result.data;
					} else {
						alert(result.msg);
					}
				}
			})
		},
		getExamInfo:function(){
			var self = this;
			$.ajax({
				type : "get",
				url : "../../examlist/"+examId,
				async : false,
				dataType : 'json',
				success : function(result) {
					if (result.status == 0) {
						self.examInfo = result.data;
					} else {
						alert(result.msg);
					}
				}
			})
		}
	}
})
function release(){
	var examTitle = $("#examTitle").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var examPeriod = $("#examPeriod").val();
	var singleQuantity = $("#singleQuantity").val();
	var singleScore = $("#singleScore").val();
	var multipleQuantity = $("#multipleQuantity").val();
	var multipleScore = $("#multipleScore").val();
	var passScore = $("#passScore").val();
	//获取所选的节点id
	var userID = new Array();
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"), nodes = zTree
	.getCheckedNodes(true), v = "";
	for(var i=0;i<nodes.length;i++){
		if(nodes[i].check_Child_State == -1 && nodes[i].id >=100){//可能存在的bug
			userID.push(nodes[i].id);
		}       
    }
	var tk = $("#tk").val();
	var str = tk.split("&");
	var categoryID = str[0];
	//alert("测试"+singleQuantity+"    "+multipleQuantity+"    "+str[1]+"    "+str[2]);
	if(singleQuantity>parseInt(str[1]) || multipleQuantity>parseInt(str[2]) ){
		//alert("测试"+singleQuantity+"    "+multipleQuantity+"    "+str[1]+"    "+str[2]);
		var text="所选题库数量不满足要求的题数<br/>请重新选择";
		document.getElementById('show_msg').innerHTML=text;
		$('.popup_de').addClass('bbox');
		$('.popup_de .btn-danger').one('click',function(){
			$('.popup_de').removeClass('bbox');
		})
		return;
	}
	var temp = {
		examInfo:{
			examTitle:examTitle,
			startTime:new Date(startTime).getTime(),
			endTime:new Date(endTime).getTime(),
			examPeriod:examPeriod,
			singleQuantity:singleQuantity,
			multipleQuantity:multipleQuantity,
			passScore:passScore
		},
		examInfoReview:{
			singleScore:singleScore,
			multipleScore:multipleScore
		},
		userID:userID,
		categoryID:categoryID
	};
	//console.log(temp);
	//先删除
	$.ajax({                            
		type:'delete',        
        url:'../../examinfo/'+examId, 
        dataType:'json',
        async : false,
        success: function(result) {  
        	
        },
        error :function(){
        	alert("系统出错！");
        }
   });
	//再插入
	$.ajax({		
		type : "post",// 请求方式
		url : "../../examinfo",
		data : JSON.stringify(temp),//将temp转换为JSON字符串
		contentType : 'application/json;charset=utf-8',//json转递必须
		dataType : "json",
		success : function(result) {
			if(result.status==0){
				alert("修改成功");
			}else{
				alert("修改失败,请检查填写数据是否完整");
			}
		}
	});
}