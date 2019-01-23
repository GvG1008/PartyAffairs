$(document).ready(function(){
	$("#video").mouseenter(function(){
		document.getElementById("video").style.backgroundColor = "#E6E7EC";
		document.getElementById("video").style.color = "red";
		document.getElementById("docu").style.backgroundColor = "#FBFBFB";
		document.getElementById("docu").style.color = "#3365DA";
		$("#docu-record").hide();
		$("#video-record").show();
	})
	
	$("#docu").mouseenter(function(){
		document.getElementById("video").style.backgroundColor = "#FBFBFB";
		document.getElementById("video").style.color = "#3365DA";
		document.getElementById("docu").style.backgroundColor = "#E6E7EC";
		document.getElementById("docu").style.color = "red";
		$("#docu-record").show();
		$("#video-record").hide();
	})
})

var limit = 8;

var a = new Vue({
	el:"#le-record",
	data:{
		videodata:[],
		videopageNow:[],
		videoTotalPage:[],
		docdata:[],
		docpageNow:[],
		docTotalPage:[]
	},
	methods:{
		docupPage:function(){
			getDoc(a.docpageNow-1)
		},
		docnextPage:function(){
			getDoc(a.docpageNow+1)
		},
		ViupPage:function(){
			getVideo(a.videopageNow-1)
		},
		VinextPage:function(){
			getVideo(a.videopageNow+1)
		}
	},
	created:function(){
		getVideo(1);
		getDoc(1);
	}
})

function getVideo(pageNow){
	$.ajax({
			type:"post",
			data:{
				page:pageNow,
				pageNum:limit
			},
			url:"../study/get_studied_videos.do",
			async:true,
			success:function(res){
				if(res.status == 0){
					a.videodata = res.data.list;
					a.videopageNow = res.data.page;
					a.videoTotalPage = res.data.totalPage;
				}
			}
		});
}

function getDoc(pageNow){
	$.ajax({
			type:"post",
			data:{
				page:pageNow,
				pageNum:limit
			},
			url:"../study/get_studied_documents.do",
			async:true,
			success:function(res){
				if(res.status == 0){
					a.docdata = res.data.list;
					a.docpageNow = res.data.page;
					a.docTotalPage = res.data.totalPage;
				}
			}
		});
}

var b = new Vue({
	el:"#rp_record",
	data:{
		reportdata:[],
		repotPageNow:[],
		reportTotalPage:[]
	},
	methods:{
		ReupPage:function(){
			getRpo(b.repotPageNow-1)
		},
		RenextPage:function(){
			getRpo(b.repotPageNow+1)
		}
	},
	created:function(){
		getRpo(1);
	}
})

function getRpo(pageNow){
	$.ajax({
		type:"post",
		url:"../report/myReports/"+pageNow+"/"+limit,
		async:true,
		success:function(res){
			if(res.status == 0){
				b.reportdata = res.data.list;
				b.repotPageNow = res.data.pageNum;
				b.reportTotalPage = res.data.totalPageNum;
			}
		}
	});
}
