function putdata()
{
    startLoad();
    var id=GetQueryString("id");
    $.ajax({
        type: "GET",
        url: "article.do/detail",
        dataType: "json",
        data:"id="+id,
        success: function(result){
            if(result.result=="no")
            {
                toastr.warning("No such article! return to the home page in 2s...");
                endLoad();
                jump("index.html",2200);
            }
            else{
                $('#title').text(result.title);
                $('#author').text('author:     '+result.author);  
                $('#time').text('time:        '+result.createTime);
                $('#content').html(result.content);
                $('#'+result.type).attr("class","typeactive");      
                endLoad();
            }
         },
         error: function(){
            toastr.error("server error");
            endLoad();
         }
     });
}