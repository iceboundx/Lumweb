var now_page=0;
var total_num=0;
var proitem=['<div class="col-md-3">',
'<div class="goods-item up-hover-shadow" onclick="todetail(this);" >',
'		<div class="figure-img">',
'		<img src="images/2.png" width="200" height="200" alt=""> ',
'		</div> ',
'		<h3 class="title">LumDogⅤ</h3> ',
'		<p class="price">￡17.98</p> ',
'		<div class="flags"><span class="glyphicon glyphicon-fire" style="color:Red;font-size:20px;" aria-hidden="true"></span></div>',
'		</div>',
'		</div>'].join("");
var rowleft=['<div class="row" id="'].join("");
var rowright=['"></div>'].join("");
var proitemleft=['<div class="col-md-3">',
'<div class="goods-item up-hover-shadow" onclick="todetail(this);" id="'].join("");
var proitemright=['">		',
'<div class="figure-img">		',
'<img src="images/loading.gif" width="200" height="200" alt=""> </div> 		',
'<h3 class="title">loading...</h3> 		',
'<p class="price">loading...</p>',
'<div class="flags"></div></div></div>'].join("");
var hotitem=['<span class="glyphicon glyphicon-fire" style="color:Red;font-size:20px;" aria-hidden="true"></span>'].join("");
var nohotitem=['<span class="glyphicon glyphicon-leaf" style="color:White;font-size:20px;" aria-hidden="true"></span>'].join("");
function load_more()
{
    $.ajax({
        type: "GET",
        url: "product.do/num",
        dataType: "json",
        success: function(result){
            total_num=result;
            if(now_page*8>=total_num)
            {
                toastr.warning("no more product!");
                endLoad();
            }
            else get_data();
         },
         error: function(){
            toastr.error("server error");
         }
     });
}
function get_data()
{
    $.ajax({
        type: "GET",
        url: "product.do/list",
        dataType: "json",
        data:{
            page:now_page+1,
            num:8
        },
        success: function(result){
            now_page++;
            add_page(result,now_page,result.length);
         },
         error: function(){
            toastr.error("server error");
         }
     });
}
function add_row(num){
    $('#prol').append(rowleft+"row"+num+rowright);
}
function add_item(product,rownum){
    var nowrow='#row'+rownum;
    var nowitem='#pro'+product.pid;
    $(nowrow).append(proitemleft+"pro"+product.pid+proitemright);
    $(nowitem).find('img').attr("src",'images/product/thum'+product.pid+'.jpg');
    $(nowitem).find('.title').text(product.name);
    $(nowitem).find('.price').text('￡'+product.price);
    if(product.state=="hot")$(nowitem).find('.flags').html(hotitem);   
    else $(nowitem).find('.flags').html(nohotitem);    
}
function add_page(product_array,page_num,tot){
    var rn=Math.ceil(tot/4);
    var now_row=(page_num-1)*2;
    var now_item=0;
    for(i=1;i<=rn;i++){
        add_row(now_row);
        for(j=1;now_item<tot&&j<=4;now_item++){
            add_item(product_array[now_item],now_row);
        }
        now_row++;
    }
    endLoad();
}
function todetail(e)
{
    var now=e.id;
    var pid=now.substr(3,now.length);
    var url="prodetail.html?pid="+pid;
    window.open(url, '_blank').location;
}

function loadpage()
{
    startLoad();
    load_more();
}