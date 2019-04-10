function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var id=getUrlParam('id');//id
var typename = getUrlParam('name')
var Url = null;
if(typename == 0 || typename == 1){
	Url = "../../voteresult/choice/"+id
}else if(typename == 2){
	Url = "../../voteresult/sortchoice/"+id
}

var app = new Vue({
	el:"#app",
	data:{
		info:[],
		choice:[]
	},
	methods:{
		
	},
	created:function(){
		$.ajax({
			type:"get",
			url:Url,
			async:true,
			success:function(res){
				console.log(res)
				if(res.status == 0){
					app.info = res.data.voteInfo;
					app.choice = res.data.choice;
					if(app.info.type != 2){
						showTheResult(app.choice);
					}
						else if(app.info.type == 2){
							showanotherResult(app.choice);
						}
				}
			}
		});
	}
})

function showTheResult(choice){
	//console.log(choice)
	var myChart = echarts.init(document.getElementById('main'));
	var xdata = new Array();
	var ydata = new Array();
	for(var i = 0; i <choice.length; i++){
		xdata.push(choice[i].choiceContent);
		ydata.push(parseInt(choice[i].count));
	}
	
	option = {
		xAxis: {
			type: 'category',
			data: xdata
		},
		yAxis: {
			type: 'value'
		},
		series: [{
			data: ydata,
			type: 'bar'
		}]
	};
	myChart.setOption(option);
}

function showanotherResult(choice){
	console.log(choice)
	var myChart = echarts.init(document.getElementById('main'));
	var xdata = new Array();
	var ydata = new Array();
	for(var i = 0; i <choice.length; i++){
		xdata.push(choice[i].choiceContent);
		ydata.push(parseInt(choice[i].score));
	}
	
	option = {
		xAxis: {
			type: 'category',
			data: xdata
		},
		yAxis: {
			type: 'value'
		},
		series: [{
			data: ydata,
			type: 'bar'
		}]
	};
	myChart.setOption(option);
}
