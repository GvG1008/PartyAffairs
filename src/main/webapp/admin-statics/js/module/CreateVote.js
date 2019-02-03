$(document).ready(function() {
	$("#send,#sendsend").click(function() {
		var title = $("#title").val();
		var description = $("#description").val();
		var type1 = $("#type1").val();
		var least = $("#least").val();
		var most = $("#most").val();
		var startTime1 = new Date($("#startTime").val());
		var startTime = Date.parse(startTime1);
		var endTime1 = new Date($("#endTime").val());
		var endTime = Date.parse(endTime1);		

		//获取所选的用户id
		var userID = new Array();
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree
			.getCheckedNodes(true),
			v = "";
		for(var i = 0; i < nodes.length; i++) {
			if(nodes[i].check_Child_State == -1 && nodes[i].id >= 100) { //可能存在的bug
				userID.push(nodes[i].id);
			}
		}

		var tempchoices = $("#choices").val();
		var choices = tempchoices.split(",")
		if(choices.length < most) {
			alert("最多选择不能超过选项数目")
			$("#most").focus();
		}

		var choice_user = {
			choice: choices,
			voteUser: userID
		}

		$.ajax({
			type: "post",
			data: JSON.stringify(choice_user),
			contentType: "application/json",
			url: "../../voteinfo?type=" + type1 + "&title=" + title + "&description=" + description + "&least=" + least + "&most=" + most + "&startTime=" + startTime + "&endTime=" + endTime,
			async: true,
			success: function(res) {
				console.log(res)
				if(res.status == 0) {
					alert("发布成功")
					location.href="VoteManager.html";
				}
			}
		});

	})
})

$(function() {
	var setting = {
		check: {
			enable: true,
			/* chkboxType: {"Y":"", "N":""} */
		},
		view: {
			dblClickExpand: false,
			nameIsHTML: true, // 允许name支持html
			selectedMulti: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onCheck: onCheck
		}
	};

	var zNodes = [{
			id: 1,
			pId: 0,
			name: "软件工程专业党支部",
			open: true
		}, {
			id: 2,
			pId: 0,
			name: "计算机科学与技术专业党支部",
			open: true
		}, {
			id: 3,
			pId: 0,
			name: "网络工程专业党支部",
			open: true
		}, {
			id: 4,
			pId: 0,
			name: "物联网专业党支部",
			open: true
		}, {
			id: 5,
			pId: 0,
			name: "数据科学与大数据技术专业党支部",
			open: true
		}, {
			id: 11,
			pId: 1,
			name: "正式党员"
		}, {
			id: 12,
			pId: 1,
			name: "预备党员"
		}, {
			id: 13,
			pId: 1,
			name: "发展对象"
		}, {
			id: 14,
			pId: 1,
			name: "积极分子"
		}, {
			id: 21,
			pId: 2,
			name: "正式党员"
		}, {
			id: 22,
			pId: 2,
			name: "预备党员"
		}, {
			id: 23,
			pId: 2,
			name: "发展对象"
		}, {
			id: 24,
			pId: 2,
			name: "积极分子"
		}, {
			id: 31,
			pId: 3,
			name: "正式党员"
		}, {
			id: 32,
			pId: 3,
			name: "预备党员"
		}, {
			id: 33,
			pId: 3,
			name: "发展对象"
		}, {
			id: 34,
			pId: 3,
			name: "积极分子"
		}, {
			id: 41,
			pId: 4,
			name: "正式党员"
		}, {
			id: 42,
			pId: 4,
			name: "预备党员"
		}, {
			id: 43,
			pId: 4,
			name: "发展对象"
		}, {
			id: 44,
			pId: 4,
			name: "积极分子"
		}, {
			id: 51,
			pId: 5,
			name: "正式党员"
		}, {
			id: 52,
			pId: 5,
			name: "预备党员"
		}, {
			id: 53,
			pId: 5,
			name: "发展对象"
		}, {
			id: 54,
			pId: 5,
			name: "积极分子"
		},

	];

	$.ajax({
		type: "get",
		url: "../../userManage/userListByBranch",
		async: false,
		dataType: 'json',
		success: function(result) {
			if(result.status == 0) {
				data = result.data;
				//alert("查询成功");
				$.each(data, function(index, item) {
					//alert(item.userId);
					if(item.roleId != 0) {
						parentId = item.branchId * 10 + item.roleId;
						zNodes.push({
							id: item.userId, //本身id
							pId: parentId, //父级id
							name: item.realName //显示的名称
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
	var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree
		.getCheckedNodes(true),
		v = "";
	var parentId = -1;
	var flag = 0;
	/*console.log(nodes.length + ":" + nodes[0].name + ":"+ nodes[nodes.length - 1].name);*/

	for(var i = 0, l = nodes.length; i < l; i++) {
		/*console.log("nodes[i]：" + nodes[i].name + "::::" + i);
		console.log("nodes[i].check_Child_State：" + nodes[i].check_Child_State);*/
		if(nodes[i].level == 0) {
			flag = 0;
		}
		if(nodes[i].check_Child_State == 2) { // 子节点全被选中

			//console.log("node[" + i + "].getParentNode():"+ nodes[i].getParentNode());
			if(nodes[i].level == 0) {
				v += nodes[i].name + ",";
				flag = 1;
			}

			if(flag == 1) {
				parentId = i;
			} else {
				v += nodes[i].name + ",";
				parentId = i;
			}

		} else if(nodes[i].check_Child_State == -1) { // 子节点没被选中
			if(nodes[parentId] != nodes[i].getParentNode())
				v += nodes[i].name + ",";
		} else if(nodes[i].check_Child_State == 1) {
			v += nodes[i].name + ",";

		}
	}
	if(v.length > 0)
		v = v.substring(0, v.length - 1);
	var cityObj = $("#citySel");
	cityObj.attr("value", v);
}

function showMenu() {
	var cityObj = $("#citySel");
	var cityOffset = $("#citySel").offset();
	$("#menuContent").css({
		left: cityOffset.left + "px",
		top: cityOffset.top + cityObj.outerHeight() + "px",
		"z-index": 99999
	}).slideDown("fast");

	$("body").bind("mousedown", onBodyDown);
}

function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}

function onBodyDown(event) {
	if(!(event.target.id == "menuBtn" || event.target.id == "citySel" ||
			event.target.id == "menuContent" || $(event.target).parents(
				"#menuContent").length > 0)) {
		hideMenu();
	}
}