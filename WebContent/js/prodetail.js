function loadpage()
{
    startLoad();
    var pid=GetQueryString("pid");
    $.ajax({
        type: "GET",
        url: "product.do/detail",
        dataType: "json",
        data:"pid="+pid,
        success: function(result){
            if(result.result=="noproduct")
            {
                $('#buybtn').attr("disabled",true);
                toastr.warning("No such product! return to the home page in 2s...");
                endLoad();
                jump("index.html",2200);
            }
            else{
                $('#title').text(result.name);
                $('#shortdesc').text(result.shortdesc);
                $('#price').html('<div class="pri">Price:</div>￡'+result.price);
                $('#stock').text('Stock:'+result.stock);
                $('#proimg').attr("src",'images/product/pro'+result.pid+'.jpg');
                $('#bigimg').attr("src",'images/product/detail'+result.pid+'.jpg');
                endLoad();
            }
         },
         error: function(){
            toastr.error("server error");
            endLoad();
         }
     });
}
function addcart()
{
    startLoad();
    if(!is_login())
    {
        toastr.warning("No login! return to the login page in 2s...");
        endLoad();
        jump("login.html",2200);
        return;
    }
    var pid=GetQueryString("pid");
    $.ajax({
        type: "POST",
        url: "cart.do/add",
        dataType: "json",
        data:"pid="+pid,
        success: function(result) {
            if(result.result=="yes")
            {
                toastr.success("Add to cart successfully");
                endLoad();
            }
            else
            {
                toastr.error("Unknown error!");
                endLoad();
            }
        },
        error: function(){
            toastr.error("server error");
            endLoad();
         }
    });
}