function checkAll() {
        var all=document.getElementById('all');//获取到点击全选的那个复选框的id
        var one=document.getElementsByName('checkname[]');//获取到复选框的名称
       
        if(all.checked==true){//因为获得的是数组，所以要循环 为每一个checked赋值
            for(var i=0;i<one.length;i++){
                one[i].checked=true;
            }
 
        }else{
            for(var j=0;j<one.length;j++){
                one[j].checked=false;
            }
        }
}
$(function () {
	 var all=document.getElementById('all');//获取到点击全选的那个复选框的id
     var one=document.getElementsByName('checkname[]');//获取到复选框的名称
    $("#allcheck").click(function(){
        
        if(all.checked==true){
            all.checked=false;
        for(var i=0;i<one.length;i++){
             one[i].checked=false;
             
            }
        }else{
            all.checked=true;
            for(var j=0;j<one.length;j++){
              one[j].checked=true;
               
           }
        }
        
    });
	 
});
$("tbody tr").on('click' , function(){
	var check = $(this).find("input[type='checkbox']");
	if ($(check).is(':checked')) {
		$(check).prop('checked', false);
	} else {
		$(check).prop('checked', true);
	}
})