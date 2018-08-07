$(document).ready(function(){
	
	$(function() {
		$(':input').labelauty();
	});
	
	$("#learn-search").click(function(){
		var lable = document.getElementsByName("checkbox");
		var type = document.getElementsByName("radio");
		var claim = document.getElementsByName("radio1");
		var lables = new Array();
		var y=0;
		
		for(var i=0;i<lable.length;i++){
			if(lable[i].checked){
				lables[y++] = lable[i].value;
			}
		}
		for(var i=0;i<type.length;i++){
			if(type[i].checked){
				var gtype = type[i].value;
			}
		}
		for(var i=0;i<claim.length;i++){
			if(claim[i].checked){
				var gclaim = claim[i].value;
			}
		}
		
		alert(gclaim);
		for(var i=0;i<lables.length;i++){
			alert(lables[i]);
		}
		alert(gtype);
		
	})
})
