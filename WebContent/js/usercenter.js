
function loadpage(){
    $.ajax({
        type: "GET",
        url: "user.do/",
        dataType: "json",
        success: function(result){  
            $('#username').html(result.nickname); 
            $('#uid').html("TEL: "+result.uid);   
         },
         error: function(){
            toastr.error("server error");
         }
     });
}