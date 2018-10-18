/*var data = {    
		        "branchId": null,
		        "categoryId": 1,
		        "userId": null,
		        "singleQuestion": [
		            {
		                "questionContent": "在习近平新时代中国特色社会主义思想指导下，中国共产党领导全国各族人民，统揽（），推动中国特色社会主义进入了新时代。",
		                "choice": [
		                    "A.伟大斗争、伟大工程、伟大事业、伟大梦想 ",
		                    "B.伟大斗争、伟大建设、伟大事业、伟大梦想",
		                    "C.伟大斗争、伟大工程、伟大发展、伟大梦想",
		                    "D.伟大斗争、伟大工程、伟大事业、伟大理想"
		                ],
		                "answer": [
		                    1
		                ]
		            },
		            {
		                "questionContent": "在（）指导下，中国共产党领导全国各族人民，统揽伟大斗争、伟大工程、伟大事业、伟大梦想，推动中国特色社会主义进入了新时代。",
		                "choice": [
		                    "A.毛泽东思想",
		                    "B.马克思列宁主义",
		                    "C.习近平新时代中国特色社会主义思想",
		                    "D.“三个代表”重要思想"
		                ],
		                "answer": [
		                    3
		                ]
		            }
		          ],
		          "multipleQuestion": [
			            {
			                "questionContent": "在习近平新时代中国特色社会主义思想指导下，中国共产党领导全国各族人民，统揽（），推动中国特色社会主义进入了新时代。",
			                "choice": [
			                    "A.伟大斗争、伟大工程、伟大事业、伟大梦想 ",
			                    "B.伟大斗争、伟大建设、伟大事业、伟大梦想",
			                    "C.伟大斗争、伟大工程、伟大发展、伟大梦想",
			                    "D.伟大斗争、伟大工程、伟大事业、伟大理想"
			                ],
			                "answer": [
			                    1
			                ]
			            },
			            {
			                "questionContent": "在（）指导下，中国共产党领导全国各族人民，统揽伟大斗争、伟大工程、伟大事业、伟大梦想，推动中国特色社会主义进入了新时代。",
			                "choice": [
			                    "A.毛泽东思想",
			                    "B.马克思列宁主义",
			                    "C.习近平新时代中国特色社会主义思想",
			                    "D.“三个代表”重要思想"
			                ],
			                "answer": [
			                    1,3
			                ]
			            }
			          ]
		           
};*/

function edittest(test_id) {
    if (test_id != "") {
        console.log(data);
        var titleB = data.categoryId;
        var sq = data.singleQuestion;
        var mq = data.multipleQuestion;
        
        var test_box = '';
        var sqtopic_box ='';
        $.each(sq, function (h, sq) {
            var title = sq.questionContent;
            var options = sq.choice;
            var answer = sq.answer;
            var option_box = '';
                $.each(options, function (j, option) {
                        var op = convert(j);
                        option_box +='<div class = "jxz-option radio" >' 
                        	        +'<label class="opl">'
                        	        +'<input name = "test'+h+''+j+'" type = "radio" value = "'+op+'" > '
                        	        +'<input type = "text" class="form-control optiontext" value="'+option+'"/>'
                        	        /*+'<span class="opclosebtn">x</span>*/+'</label>'
                        	        +'</div >';
                });
             var answer_op = '';
             if(answer==1){
            	 answer_op +='A';
             }else if(answer==2){
            	 answer_op += 'B';
             }else if(answer==3){
            	 answer_op += 'C';
             }else if(answer==4){
            	 answer_op += 'D';
             }
             
             sqtopic_box += '<div class = "edittestCon"  data-type = "sq" data-answer="'+answer_op+'">'
               + '<textarea  class = " form-control jxz-title">'+sq.questionContent +'</textarea>'
               + '<span class="closediv" onclick="closediv(this)">X</span>'+option_box
	  		   + '<div class="topic-answer">正确答案：<input type="text" class="form-control answer_op" value="'+answer_op+'"/>' 
	  		   + '</div></div>';
             
        });
       
        var mqtopic_box ='';
        $.each(mq, function (h, mq) {
            var title = mq.questionContent;
            var options = mq.choice;
            var answer = mq.answer;
            var option_box = '';
                $.each(options, function (j, option) {
                        var op = convert(j);
                        option_box +='<div class = "jxz-option radio" >' 
                        	        +'<label class="opl">'
                        	        +'<input name = "test'+h+''+j+'" type = "radio" value = "'+op+'" /> '
                        	        +'<input type = "text" class="form-control optiontext"  value="'+option+'"/>'
                        	        /*+'<span class="opclosebtn">x</span>*/+'</label>'
                        	        +'</div >';
                });
                var answer_op = '';
                
                $.each(answer, function (i, aw) {
                	  if(aw==1){
                      	 answer_op +=(i + 1) ? 'A':'A ';
                       }else if(aw==2){
                      	 answer_op += (i + 1) ? 'B':'B ';
                       }else if(aw==3){
                      	 answer_op += (i + 1) ? 'C':'C ';
                       }else if(aw==4){
                      	 answer_op += (i + 1) ? 'D':'D ';
                       } 
                      /*answer_op += answer.length == (i + 1) ? aw : aw + " ";*/
                }); 
                
                mqtopic_box += '<div class = "edittestCon"  data-type = "sq" data-answer="'+answer_op+'">'
                + '<textarea  class = " form-control jxz-title">'+mq.questionContent +'</textarea>'
                + '<span class="closediv" onclick="closediv(this)">X</span>'+option_box
 	  		    + '<div class="topic-answer">正确答案：<input type="text" class="form-control answer_op" value="'+answer_op+'"/>' 
		  		   + '</div></div>';
     
          });
          test_box+='<div class="jxz-box"><h4 class="tesTitle">单项选择题</h4 >'+sqtopic_box 
                   +'</div>'
                   +'<div class="jxz-box"><h4 class="tesTitle">多项选择题</h4 >'+mqtopic_box 
                   +'</div>';
          
          var test_html=/*'<div class="page-header"><h3 class="text-center">两学一做</h3></div>'*/
                     '<div class="test-form-box" >'+test_box+'</div>';
            $('#testArea').html(test_html)
            }else{alert("试题获取失败！");}
 } 
function test(test_id,data) {
    if (test_id != "") {
        console.log(data);
        var titleB = data.categoryId;
        var sq = data.singleQuestion;
        var mq = data.multipleQuestion;
        
        var test_box = '';
        var sqtopic_box ='';
        $.each(sq, function (h, sq) {
            var title = sq.questionContent;
            var options = sq.choice;
            var answer = sq.answer;
            var option_box = '';
                $.each(options, function (j, option) {
                        var op = convert(j);
                        option_box +='<div class = "jxz-option radio" >' 
                        	 +'<label >'
                 	         +'<input name = "test'+h+''+j+'" type = "radio" value = "'+op+'" > '+option + '</label>'
                             +'</div >';
                });
             var answer_op = '';
             if(answer==1){
            	 answer_op +='A';
             }else if(answer==2){
            	 answer_op += 'B';
             }else if(answer==3){
            	 answer_op += 'C';
             }else if(answer==4){
            	 answer_op += 'D';
             }
             
             sqtopic_box += '<div class = "testCon"  data-type = "sq" data-answer="'+answer_op+'">'
			  		   + '<h4 class = "jxz-title" >'+sq.questionContent +'</h4>'+option_box
			  		   + '<div class="topic-answer"><p>正确答案：'+answer_op+'</p>' 
			  		   + '</div></div>';
             
        });
       
        var mqtopic_box ='';
        $.each(mq, function (h, mq) {
            var title = mq.questionContent;
            var options = mq.choice;
            var answer = mq.answer;
            var option_box = '';
                $.each(options, function (j, option) {
                        var op = convert(j);
                        option_box +='<div class = "jxz-option radio" >' 
                        	        +'<label >'
                        	        +'<input name = "test'+h+''+j+'" type = "radio" value = "'+op+'" > '+option + '</label>'
                                    +'</div >';
                });
                var answer_op = '';
                
                $.each(answer, function (i, aw) {
                	  if(aw==1){
                      	 answer_op +=(i + 1) ? 'A':'A ';
                       }else if(aw==2){
                      	 answer_op += (i + 1) ? 'B':'B ';
                       }else if(aw==3){
                      	 answer_op += (i + 1) ? 'C':'C ';
                       }else if(aw==4){
                      	 answer_op += (i + 1) ? 'D':'D ';
                       } 
                      /*answer_op += answer.length == (i + 1) ? aw : aw + " ";*/
                }); 
                
                mqtopic_box += '<div class = "testCon"  data-type = "sq" data-answer="'+answer_op+'">'
		  		   + '<h4 class = "jxz-title" >'+mq.questionContent +'</h4>'+option_box
		  		   + '<div class="topic-answer"><p>正确答案：'+answer_op+'</p>' 
		  		   + '</div></div>';
     
          });
          test_box+='<div class="jxz-box"><h4 class="tesTitle">单项选择题</h4 >'+sqtopic_box +'</div>'
                   +'<div class="jxz-box"><h4 class="tesTitle">多项选择题</h4 >'+mqtopic_box +'</div>';
          
          var test_html=/*'<div class="page-header"><h3 class="text-center">两学一做</h3></div>'*/
                     '<div class="test-form-box" title="双击进入编辑" ondblclick="dblclick()" >'+test_box+'</div>';
            $('#testArea').html(test_html)
            }else{alert("试题获取失败！");}
    
	    $("#sumbitExam").click(function(){
	    	console.log($(".testCon").length);
	    	if($(".testCon").length <= 0) {
	    		alert("题库不得为空！！！,请重新上传");
	    		$("#previewModal").modal('hide');
	    	}else{
	    		//To do
	    	}
			
		});
         
 }       
		 
		//双击事件
		function dblclick(){
		   edittest();
		}
		

		function convert(num) {
		    num = num + 1;
		    return num <= 26 ? String.fromCharCode(num + 64) : convert(~~((num - 1) / 26)) +
		        convert(num % 26 || 26);
		}
		
		//删除div
		function closediv(Obj){
			 Obj.parentNode.parentNode.removeChild(Obj.parentNode);
		}
		
	
