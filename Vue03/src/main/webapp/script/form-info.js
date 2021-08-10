//1.调用检测用户名的函数
$("form").each(function () {
   let form=$(this);
   if(form.find("[pattern]").length>0){
       let status=[];
       form.find("[pattern]").each(function (index){
           status[index]=false;
           let reg=new RegExp($(this).attr("pattern"));
           let msg=$(this).attr("msg");
           $(this).next().html(msg);
           $(this).next().css({color:"red"});
           $(this).focus(function () {
              if($(this).val()==""){
                  $(this).next().html(msg);
                  $(this).next().css({color:"red"});
              }
           });
           $(this).blur(function () {
               if(reg.test($(this).val())){
                   $(this).next().html("格式正确");
                   $(this).next().css({color:"green"});
                     status[index]=true;
               }else{
                   $(this).next().html(msg);
                   $(this).next().css({color:"red"});
                   status[index]=false;
               }
           });
           $(this).blur(function (){
               if(form.find(".town").val()==null||form.find(".town").val()!="-1"){
                   if(status.indexOf(false)==-1){
                       form.find(".submit").prop("disabled",false);
                   }else{
                       form.find(".submit").prop("disabled",true);
                   }
               }
           });
           if(form.find("select").val()!=null&&form.find(".town").val()!=null){
               form.find("select").change(function () {
                   if(form.find(".town").val()!="-1"){
                       if(status.indexOf(false)==-1){
                           form.find(".submit").prop("disabled",false);
                       }else{
                           form.find(".submit").prop("disabled",true);
                       }
                   }else{
                       form.find(".submit").prop("disabled",true);
                   }
               });
           }
       });
   }
});

