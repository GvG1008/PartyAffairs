var maxtime;
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]); return null; 
}
var examid=getUrlParam('id');//考试id

var app = new Vue({
	el : '#app',
	data : {
		RadioDatas : [],
		RadioCount : [],
		CheckboxDatas : [],
		CheckboxCount : [],
		singleScore : [],
		multipleScore : [],
		LeftTime : [],
		datas : [],
		submit : 0
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
					self.singleScore = result.data.singleScore;
					self.multipleScore = result.data.multipleScore;
					self.datas = result.data;
					maxtime = result.data.examPeriod;
					self.CountDownTime();
				} else {
					alert(result.msg);
				}
			}
		});
	},
	methods : {
		generateFor : function(index, t) {//单选动态相连
			return "0_answer_" + index + "_option_" + t;
		},
		generateFor1 : function(index, t) {//多选动态相连
			return "1_answer_" + index + "_option_" + t;
		},
		generate : function(id, index) {//动态id
			return id + index;
		},
		radiochoice : function(index) {//选择单选
			doRadioChoice(index);
		},
		checkchoice : function(index) {//选择多选
			doCheckChoice(index);
		},
		CountDownTime : function() {//时间倒计时
			timer = setInterval("CountDown()", 1000);
		}
	}
})

function CountDown() {//时间倒计时
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
		alert("时间到，考试结束!");
		updataExam();
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

function submit1() {//提交试卷
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
			//alert("答案正确");
			app.datas.singleQuestion[t].correct = true;
			//alert(app.datas.singleQuestion[t].correct);
			score += app.singleScore;
		}else{
			//alert("答案错误");
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
			score += app.multipleScore;
			app.datas.multipleQuestion[t].correct = true;
		}else{
			app.datas.multipleQuestion[t].correct = false;
		}
		var temp = {questionId : app.datas.multipleQuestion[t].questionId, userAnswer : answer.join(",")};
		jsonObj.push(temp);
	}
	
	/*for(var p in jsonObj){//遍历json数组时，这么写p为索引，0,1	 
		alert(jsonObj[p].questionId + " " + jsonObj[p].userAnswer);
	}*/
	//updataExam();
	var examPaper = jsonObj;
	$.ajax({		
		type : "post",// 请求方式
		url : "../exampaper/"+examid+"/"+score,// 地址，就是json文件的请求路径
		data : JSON.stringify(examPaper),//将examPaper转换为JSON字符串，后台以List<ExamPaper>接收
		contentType : 'application/json;charset=utf-8',
		dataType : "json",// 数据类型可以为 text xml json script jsonp
		success : function(result) {// 返回的参数就是 action里面所有的有get和set方法的参数
			if(result.status==0){
				if(result.data.passScore > score){
					alert("很遗憾！此次考试不及格，再接再厉！\n本次考试成绩为："+score+"\n历史最高分数是："+result.data.topScore);
				}else{
					if(result.data.topScore>=score){
						alert("恭喜你！此次考试及格！\n本次考试成绩为："+score+"\n历史最高分数是："+result.data.topScore+"\n不高于历史最高分数，不计入成绩！");
						updataExam();
					}else{
						alert("恭喜你！此次考试及格！\n本次考试成绩为："+score+"\n历史最高分数是："+result.data.topScore+"\n高于历史最高分数，计入成绩！");
						updataExam();
					}
				}
				clearInterval(timer);
			}else{
				alert(result.msg);
			}
		}
	});
}

//更新考试后的试卷
function updataExam(){
	//更新单选错误列表
	var datas = app.datas.singleQuestion;
	for(var t in datas){
		//alert(t+"      "+datas[t].userAnswer+"    "+datas[t].correct);
		var str = "answer1" + t;
		var radio = document.getElementsByName(str);
		for (var i = 0; i < radio.length; i++) {
			if (radio[i].value  == datas[t].answer) {
				//alert(radio[i].id);
				radio[i].parentNode.style.background = "#50f161";	
			}
		}
		if(datas[t].correct == false){
			var examId = "qu_0_" + t;
			var cardLi = $('a[href=#' + examId + ']'); // 根据题目ID找到对应答题卡
			cardLi.removeClass('hasBeenAnswer');
			cardLi.addClass('errorAnswer');
		}
	}
	//更新多选错误列表
	var datas1 = app.datas.multipleQuestion;
	for(var t in datas1){
		//alert(t+"      "+datas[t].userAnswer+"    "+datas[t].correct);
		var str = "answer2" + t;
		obj = document.getElementsByName(str);
		var temp = datas1[t].answer;
		//alert(temp);
		for(var i=0;i<temp.length;i++)
			for (k in obj) {
				if (obj[k].value  == temp[i]) {
					obj[k].parentNode.style.background = "#50f161";	
				}			
		}
		if(datas1[t].correct == false){
			var examId = "qu_1_" + t;
			var cardLi = $('a[href=#' + examId + ']'); // 根据题目ID找到对应答题卡
			cardLi.removeClass('hasBeenAnswer');
			cardLi.addClass('errorAnswer');
		}
	}
	app.submit = 1;
}
var port = "http://localhost:8080/";
//退出考试
function exit(){
	window.location.href = port+"PartyAffairs/views/home.html";
}