$(function(){
	var setting = {
			check: {
				enable: true,
				/*chkboxType: {"Y":"", "N":""}*/
			},
			view: {
				dblClickExpand: false,
				nameIsHTML: true, //允许name支持html				
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

	     /*id为当前节点id，pid为上一节点id，open表示是否展开*/
		var zNodes =[
			{id:1, pId:0, name:"计算机科学与技术专业党支部",open:true},
			{id:2, pId:0, name:"软件工程专业党支部",open:true},
			{id:3, pId:0, name:"网络工程专业党支部",open:true},
			{id:4, pId:0, name:"物联网专业党支部",open:true},
			{id:5, pId:0, name:"数据科学与大数据技术专业党支部",open:true},
			{id:11, pId:1, name:"正式党员"},
			{id:12, pId:1, name:"预备党员"},
			{id:13, pId:1, name:"发展对象"},
			{id:14, pId:1, name:"积极分子"},
			{id:21, pId:2, name:"正式党员"},
			{id:22, pId:2, name:"预备党员"},
			{id:23, pId:2, name:"发展对象"},
			{id:24, pId:2, name:"积极分子"},
			{id:31, pId:3, name:"正式党员"},
			{id:32, pId:3, name:"预备党员"},
			{id:33, pId:3, name:"发展对象"},
			{id:34, pId:3, name:"积极分子"},
			{id:41, pId:4, name:"正式党员"},
			{id:42, pId:4, name:"预备党员"},
			{id:43, pId:4, name:"发展对象"},
			{id:44, pId:4, name:"积极分子"},
			{id:51, pId:5, name:"正式党员"},
			{id:52, pId:5, name:"预备党员"},
			{id:53, pId:5, name:"发展对象"},
			{id:54, pId:5, name:"积极分子"},
			{id:111, pId:11, name:"陈独秀"},
			{id:121, pId:12, name:"李大钊"},
			{id:131, pId:13, name:"李梅梅"},
			{id:141, pId:14, name:"史迪"},
			{id:112, pId:11, name:"陈独秀"},
			{id:122, pId:12, name:"李大钊"},
			{id:133, pId:13, name:"李梅梅"},
			{id:144, pId:14, name:"史迪"},
			
			
		 ];

		

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			fuzzySearch('treeDemo','#key',null,true); //初始化模糊搜索方法

		});

	
})
        function beforeClick(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		}
		
		function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getCheckedNodes(true),
			v = "";
			var parentId = -1;
			var flag = 0;
			console.log(nodes.length+":"+nodes[0].name+":"+nodes[nodes.length-1].name);
			
			for (var i=0, l=nodes.length; i<l; i++) {
				console.log("nodes[i]："+nodes[i].name+"::::"+i);
				console.log("nodes[i].check_Child_State："+nodes[i].check_Child_State);
				if(nodes[i].level==0){
					flag = 0;
				}
				if(nodes[i].check_Child_State==2){//子节点全被选中
				   
				   console.log("node["+i+"].getParentNode():"+nodes[i].getParentNode());
				   if(nodes[i].level==0){ 
					 v += nodes[i].name + ","; 
					 flag = 1; 
				   }
				
				   if(flag==1){
					   parentId = i; 
				   }else{
					   v += nodes[i].name + ","; 
					   parentId = i; 
				   }
				   
				}else if(nodes[i].check_Child_State==-1){//子节点没被选中
				   if(nodes[parentId]!=nodes[i].getParentNode())
				      v += nodes[i].name + ",";
				}else if(nodes[i].check_Child_State==1){
					  v += nodes[i].name + ",";  
							
				}
			}
			if (v.length > 0 ) v = v.substring(0, v.length-1);
			var cityObj = $("#citySel");
			cityObj.attr("value", v);
		}

		function showMenu() {
			var cityObj = $("#citySel");
			var cityOffset = $("#citySel").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "citySel" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}