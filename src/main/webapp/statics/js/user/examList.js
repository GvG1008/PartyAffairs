/**
 * 考试列表
 */
var examing = new Vue({
	el : '#examing',
	data :{
		"examing":[]
	},
	created :function(){
		var self = this;
		$.ajax({
			type:"GET",
			url: "../../examlist/unfinish", 
			dataType: "json",
			success: function(result) { 
				if(result.status == 0)
					self.examing = result.data;
				else
					alert(result.msg);
			}			
		})
	}
})

var examed = new Vue({
	el : '#examed',
	data :{
		"examed":[]
	},
	created :function(){
		var self = this;
		$.ajax({
			type:"GET",
			url: "../../examlist/finish", 
			dataType: "json",
			success: function(result) { 
				if(result.status == 0)
					self.examed = result.data;
				else
					alert(result.msg);
			}			
		})
	}
})