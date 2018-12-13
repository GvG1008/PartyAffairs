$(function(){
    $("#choose").click(
        function(){
            // $(".div-checkbox").css("display","block");
             $(".div-checkbox").slideToggle(100);

        }
   );
    // $(".js-in-checkbox").toggle(
    //     function(){
    //     $(this).parent.append("<i class=\"fa fa-check\"></i>");
    //     $(this).css("background-color","#ff7f50");
    //    },function(){
    //     $(this).
    //     $(this).css("background-color","#ff7f50");
    //    }

    // );
    
    //全选
    $("#allcheck").click(
        function(){ 
            $("input[type='checkbox']").each(function(){ 
                if($(this).attr("checked")) 
                { 
                $(this).removeAttr("checked"); 
                } 
                else 
                { 
                $(this).attr("checked","true"); 
                } 
                })
         } 
    );

    //删除
    var temp ="";
    $("#delete").click(function(){
        $("input[name='checkbox']:checkbox:checked").each(function(){ 
            temp+=$(this).parent().next().children("a.a_card_title");
            }) 
        document.write(temp); 
        if(temp==""){
            alert("请选择需要删除的资料！！！");
        }    
    });


    $("article").click(function(){ 
        alert( "点击了---"+$(this).children("header").attr("title"));
    })

    $("span[class='span-em']").click(function(){ 
          $("span[class='span-em']").each(function(){ 
            if($(this).is('.active')) 
            { 
               $(this).removeClass("active"); 
            } 
          })
            if(!$(this).is('.active')){
                $(this).addClass('active');
            }
    }) 
   
});