/**
 * 2018-12-20
 * eachen
 */

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




var limit = 6;
var videoList = new Vue({
	el:"#video_content",
	data:{
		content:[],
		page:[],
		totalpage:[]
	},
	methods:{
		pageTo:function(currentNum){
			getVideoList(currentNum);
		}
	},
	created:function(){
		getVideoList(1)
	}
})

/**
 * 获取视频列表
 */
function getVideoList(currentNum){
	if(checkedLabelID.length == 0)
		url = "../../study/get_study_videos.do";
	else if(checkedLabelID.length > 0)
		{
			url = "../../study/get_study_videos_by_label_id.do?label_id="+checkedLabelID
		}
	
	
	$.ajax({
		type:"post",
		data:{
			page:currentNum,
			pageNum:limit
		},
		url:url,
		async:true,
		success:function(res){
			console.log(res)
			if(res.status == 0){
				videoList.content = res.data.list;
				videoList.page = res.data.page;
				videoList.totalpage = res.data.totalPage;
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
	getVideoList(1);
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
    var checks = document.getElementsByName("checks");
    $("#delete").click(function(){
    	for(var i = 0; i < checks.length; i++){
    		if(checks[i].checked)
    		console.log(checks[i].value);
    	}
    })
   
 })
