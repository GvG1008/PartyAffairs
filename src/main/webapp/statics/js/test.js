var maxtime;
var examid = 1;//TODO考试id
var app = new Vue({
	el : '#app',
	data : {
		RadioDatas : [],
		RadioCount : [],
		CheckboxDatas : [],
		CheckboxCount : [],
		LeftTime : [],
		datas : []
	},
	created : function() {
		var self = this;
		$.ajax({
			type : "GET",// 请求方式
			url : "../exampaper/"+examid,// 地址，就是json文件的请求路径
			dataType : "json",// 数据类型可以为 text xml json script jsonp
			success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
				if (result.status == 0) {
					self.RadioDatas = result.data.singleQuestion;
					self.RadioCount = result.data.singleQuantity;
					self.CheckboxDatas = result.data.multipleQuestion;
					self.CheckboxCount = result.data.multipleQuantity;
					self.datas = result.data;
					maxtime = result.data.examPeriod;
					self.CountDownTime();
				} else {
					alert(msg);
				}
			}
		});
	},
	methods : {
		generateFor : function(index, t) {
			return "0_answer_" + index + "_option_" + t;
		},
		generateFor1 : function(index, t) {
			return "1_answer_" + index + "_option_" + t;
		},
		generate : function(id, index) {
			return id + index;
		},
		radiochoice : function(index) {
			doRadioChoice(index);
		},
		checkchoice : function(index) {
			doCheckChoice(index);
		},
		CountDownTime : function() {
			timer = setInterval("CountDown()", 1000);
		}
	}
})

function CountDown() {
	if (maxtime >= 0) {
		minutes = Math.floor(maxtime / 60);
		seconds = Math.floor(maxtime % 60);
		msg = minutes + "分" + seconds + "秒";
		document.all["timer"].innerHTML = msg;
		document.all["timer1"].innerHTML = "距离结束还有" + minutes + "分" + seconds
				+ "秒";
		if (maxtime == 5 * 60)
			alert("还剩5分钟");
		--maxtime;
	} else {
		clearInterval(timer);
		alert("时间到，结束!");
	}
}

function doRadioChoice(index) {
	var examId = "qu_0_" + index;
	var cardLi = $('a[href=#' + examId + ']'); // 根据题目ID找到对应答题卡
	// 设置已答题
	if (!cardLi.hasClass('hasBeenAnswer')) {
		cardLi.addClass('hasBeenAnswer');
	}
}

function doCheckChoice(index) {
	var examId = "qu_1_" + index;
	var cardLi = $('a[href=#' + examId + ']'); // 根据题目ID找到对应答题卡
	// 设置已答题
	if (!cardLi.hasClass('hasBeenAnswer')) {
		cardLi.addClass('hasBeenAnswer');
	}
	var str = "answer2" + index;
	obj = document.getElementsByName(str);
	check_val = [];
	for (k in obj) {
		if (obj[k].checked)
			check_val.push(obj[k].value);
	}
	if (check_val.length == 0) {
		cardLi.removeClass('hasBeenAnswer');
	}
}

function submit1() {
	var jsonObj = new Array();
	var score = 0;
	//单选答案
	for (var t = 0; t < app.RadioCount; t++) {
		var str = "answer1" + t;
		var radio = document.getElementsByName(str);
		var answer = new Array();
		for (var i = 0; i < radio.length; i++) {
			if (radio[i].checked) {
				answer.push(radio[i].value);
				//answer = radio[i].value;  
			}
		}
		app.datas.singleQuestion[t].userAnswer = answer;
		var correctanswer = app.datas.singleQuestion[t].answer;
		if(answer.sort().toString() == correctanswer.sort().toString()){
			app.datas.singleQuestion[t].correct = true;
			score += 1;
		}else{
			app.datas.singleQuestion[t].correct = false;
		}
		var temp = {questionId : app.datas.singleQuestion[t].questionId, userAnswer : answer.join(",")};
		 jsonObj.push(temp);
	}
	//多选答案
	for (var t = 0; t < app.CheckboxCount; t++) {
		var str = "answer2" + t;
		obj = document.getElementsByName(str);
		var answer = new Array();
		for (k in obj) {
			if (obj[k].checked)
				answer.push(obj[k].value);
			//answer+=obj[k].value;
		}
		app.datas.multipleQuestion[t].userAnswer = answer;
		var correctanswer = app.datas.multipleQuestion[t].answer;
		if(answer.sort().toString() == correctanswer.sort().toString()){
			score += 2;
			app.datas.singleQuestion[t].correct = true;
		}else{
			app.datas.singleQuestion[t].correct = false;
		}
		var temp = {questionId : app.datas.multipleQuestion[t].questionId, userAnswer : answer.join(",")};
		jsonObj.push(temp);
	}
	
	/*for(var p in jsonObj){//遍历json数组时，这么写p为索引，0,1	 
		alert(jsonObj[p].questionId + " " + jsonObj[p].userAnswer);
	}*/

	var examPaper = jsonObj;
	$.ajax({		
		type : "post",// 请求方式
		url : "../exampaper/"+examid+"/"+score,// 地址，就是json文件的请求路径
		data : JSON.stringify(examPaper),//将examPaper转换为JSON字符串，后台以List<ExamPaper>接收
		contentType : 'application/json;charset=utf-8',
		dataType : "json",// 数据类型可以为 text xml json script jsonp
		success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
			
		}
	});

}