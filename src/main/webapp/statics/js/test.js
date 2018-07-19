var maxtime;
		var app = new Vue({
		  el: '#app',
		  data: {
		    RadioDatas:[],
			RadioCount:[],
			CheckboxDatas:[],
			CheckboxCount:[],
			LeftTime:[]
		  },
		  created: function () {
			  var self = this;
			  $.ajax({
				  type: "GET",// 请求方式
				  url: "../statics/json/test.json",// 地址，就是json文件的请求路径
				  dataType: "json",// 数据类型可以为 text xml json script jsonp
				  success: function(result){// 返回的参数就是 action里面所有的有get和set方法的参数
					  self.RadioDatas = result.RadioRow;
					  self.RadioCount = result.RadioCount;
					  self.CheckboxDatas = result.CheckboxRow;
					  self.CheckboxCount = result.CheckboxCount;
					  maxtime = result.LeftTime;
					  self.CountDownTime();
				  }
			  });
		 },
		 methods:{		 	
		 	generateFor: function(index,t){
		 		return "0_answer_"+index+"_option_"+t;
		 	},
		 	generateFor1: function(index,t){
		 		return "1_answer_"+index+"_option_"+t;
		 	},
		 	generate: function(id,index){
		 		return id+index;
		 	},
		 	radiochoice: function(index){
		 		doRadioChoice(index);
		 	},
		 	checkchoice: function(index){
		 		doCheckChoice(index);
		 	},
		 	CountDownTime: function(){
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
                    document.all["timer1"].innerHTML = "距离结束还有" + minutes + "分" + seconds + "秒";
                    if (maxtime == 5 * 60)alert("还剩5分钟");
                        --maxtime;
                } else{
                    clearInterval(timer);
                    alert("时间到，结束!");
                }
        }
		
		function doRadioChoice(index){
			var examId = "qu_0_"+index;	 		
			var cardLi = $('a[href=#' + examId + ']'); // 根据题目ID找到对应答题卡
			// 设置已答题
			if(!cardLi.hasClass('hasBeenAnswer')){
				cardLi.addClass('hasBeenAnswer');
			}
		}
		
		function doCheckChoice(index){
			var examId = "qu_1_"+index;	 		
			var cardLi = $('a[href=#' + examId + ']'); // 根据题目ID找到对应答题卡
			// 设置已答题
			if(!cardLi.hasClass('hasBeenAnswer')){
				cardLi.addClass('hasBeenAnswer');
			}
			var str = "answer2"+index;
			obj = document.getElementsByName(str);
			check_val = [];
			for(k in obj){
			    if(obj[k].checked)
			        check_val.push(obj[k].value);
			}
			if(check_val.length==0){
				cardLi.removeClass('hasBeenAnswer');
			}
		}
		
		function submit1(){
			 var jsonObj = { 
			 	answer: [
        			{ id: 0, answer: ""},
    			]
			 };
			//单选答案
			for(var t=0;t<app.RadioCount;t++){
				var str = "answer1"+t;
				var radio = document.getElementsByName(str);
				var strid = "qu0"+t;
				tid = $('#'+strid).text();
				var answer = "";
				for (var i=0; i<radio.length; i++) {  
        			if (radio[i].checked) {  
           				answer = radio[i].value;  
        			}  
    			}
				var temp = {id : tid, answer : answer};
			    jsonObj.answer.push(temp);
			}
			//多选答案
			for(var t=0;t<app.CheckboxCount;t++){
				var str = "answer2"+t;
				obj = document.getElementsByName(str);
				var strid = "qu1"+t;
				tid = $('#'+strid).text();
			    var answer = "";
			    for(k in obj){
			        if(obj[k].checked)
			           answer+=obj[k].value;
			    }
			    var temp = {id : tid, answer : answer};
			    jsonObj.answer.push(temp);
		  	}
			jsonObj.answer.splice(0, 1);
			for(var p in jsonObj.answer){//遍历json数组时，这么写p为索引，0,1
 
  				alert(jsonObj.answer[p].id + " " + jsonObj.answer[p].answer);
 
			}
		}