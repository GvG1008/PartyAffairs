//$(function(){
//  $("#choose").click(
//      function(){
//          // $(".div-checkbox").css("display","block");
//           $(".div-checkbox").slideToggle(100);
//
//      }
// );
//  // $(".js-in-checkbox").toggle(
//  //     function(){
//  //     $(this).parent.append("<i class=\"fa fa-check\"></i>");
//  //     $(this).css("background-color","#ff7f50");
//  //    },function(){
//  //     $(this).
//  //     $(this).css("background-color","#ff7f50");
//  //    }
//
//  // );
//  
//  //全选
//  $("#allcheck").click(
//      function(){ 
//          $("input[type='checkbox']").each(function(){ 
//              if($(this).attr("checked")) 
//              { 
//              $(this).removeAttr("checked"); 
//              } 
//              else 
//              { 
//              $(this).attr("checked","true"); 
//              } 
//              })
//       } 
//  );
//
//  //删除
//  var temp ="";
//  $("#delete").click(function(){
//      $("input[name='checkbox']:checkbox:checked").each(function(){ 
//          temp+=$(this).parent().next().children("a.a_card_title");
//          }) 
//      document.write(temp); 
//      if(temp==""){
//          alert("请选择需要删除的资料！！！");
//      }    
//  });
//
//
//  $("article").click(function(){ 
//      alert( "点击了---"+$(this).children("header").attr("title"));
//  })
//
//  $("span[class='span-em']").click(function(){ 
//        $("span[class='span-em']").each(function(){ 
//          if($(this).is('.active')) 
//          { 
//             $(this).removeClass("active"); 
//          } 
//        })
//          if(!$(this).is('.active')){
//              $(this).addClass('active');
//          }
//  }) 
// 
//});


var checkedLabelID = new Array();
/**
 * 2018-12-17
 * eachen
 */
var classification = new Vue({
	el:"#fenlei_labels",
	data:{
		labels:[]
	},
	methods:{
		change:function(e){
			changeLabelState(e);
		},
		refresh:function(){
			location.reload();
			
		}
	},
	created:function(){
		getLabels();
	}
})

/**
 * 获取所有标签
 */
function getLabels(){
	var that = this;
	$.ajax({
		type:"POST",
		url:"../../study/get_labels.do",
		async:true,
		success:function(res){
			if(res.status == 0)
				console.log(res.data)
				classification.labels = res.data;
		}
	});
}

/**
 * 2018-12-18
 * eachen
 */
var limit = 6;
var contents = new Vue({
	el:"#content_box",
	data:{
		contents:[],
		page:[],
		totalpage:[]
	},
	methods:{
		pageTo:function(currentNum){
			getDocuments(currentNum);
		}
	},
	created:function(){
		getDocuments(1);
	}
})

/**
 * 获取全部文档学习的内容
 */
function getDocuments(currentNum){
	
	if(checkedLabelID.length == 0)
		url = "../../study/get_study_documents.do";
	else if(checkedLabelID.length > 0)
		{
			url = "../../study/get_study_documents_by_label_id.do?label_id="+checkedLabelID
		}
	var that = this;
	$.ajax({
		type:"post",
		data:{
			page:currentNum,
			pageNum:limit
		},
		url:url,
		async:true,
		success:function(res){
			if(res.status == 0)
			{
				console.log(res)
				contents.contents = res.data.list;
				contents.page = res.data.page;
				contents.totalpage = res.data.totalPage;
			}
		}
	});
}

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
	getDocuments(1);
}

$(function(){
    $("#choose").click(
        function(){
            // $(".div-checkbox").css("display","block");
             $(".div-checkbox").slideToggle(100);

        }
   );
   
       //全选
    $("#allcheck").click(
        function(){ 
            $("input[type='checkbox']").each(function(){ 
                if($(this).prop('checked')){
                	//console.log(1)
                	$(this).removeProp('checked')
                }
                else{
                	//console.log(2)
                	$(this).prop('checked','true')
                }
         })
            }
    );
    
    //删除
    var documentId = new Array();
    var checks = document.getElementsByName("checks");
    $("#delete").click(function(){
    	for(var i = 0; i < checks.length; i++){
    		if(checks[i].checked)
    		//console.log(checks[i].value);
    		documentId.push(checks[i].value);
    	}
    	console.log(documentId)
    	$.ajax({
    		type:"post",
    		data:{
    			documentId:documentId
    		},
    		traditional:true,
    		url:"../../study/delete_study_document_by_documentId",
    		async:true,
    		success:function(res){
    			if(res.status == 0){
    				alert(res.msg)
    				location.reload();
    			}
    		},
    		error:function(){
    			alert("删除出错")
    		}
    	});
    })
   
 })


