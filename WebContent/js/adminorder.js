var pro1=['<div style="margin-top: 20px;">',
'					<div class="row col-md-12">',
'					<div style="background-color:rgba(239,239,239,1.00); height: 30px;">',
'					<span class="col-md-6 ln" id="ordtime"><strong>'].join("");
var pro2=['</strong></span> ',
'					<span class="col-md-6 ln" id="ordid">'].join("");
var pro3=['</span>',
'					</div>',
'					</div>',
'					<div class="row">',
'						<div  class="row col-md-9">',
'						<span class="col-md-6">'].join("");
var pro4=['</span>',
'						<span class="col-md-3">'].join("");
var pro5=['</span>',
'					</div>',
'					<div style="" class="row col-md-3">',
'						<span class="col-md-6" style="margin-top: 0px;">'].join("");
var pro6=['</span>',
'						<a href="#" class="col-md-6">detail</a>',
'					</div>',
'					</div>',
'					</div>'].join("");
var pagenum=5;
var nowpage=1;
var onepagenum=4;
function render(productlist)
{
    if(pagenum>=2){
        $(".pagination").bootstrapPaginator({
            //设置版本号 我们用的bootstrap3
            bootstrapMajorVersion: 3,
            // 显示第几页
            currentPage: nowpage,
            // 总页数
            totalPages: pagenum,
            numberOfPages: 6,															//这个10是什么意思
            //通过pageUrl可以跳转到页面
            //每个按钮对应的url 前面baidu.com可以改成我们自己的article.html
			pageUrl: function(type, page, current){   
                return "?page="+page;
 
            },
            //通过onPageClicked这个事件可以调用响应js 也可以跳转到页面
            //当单击操作按钮的时候, 会执行该函数
            onPageClicked: function (event,originalEvent,page) {
                currentPage = page
            }
        });
    }
    $('#ord').html("");
    for(i=0;i<productlist.length;i++){
        $('#ord').append(
            pro1+productlist[i].orderTime+
            pro2+productlist[i].oid+
            pro3+'userID:'+productlist[i].uid+
            pro4+'total:'+productlist[i].price+
            pro5+productlist[i].state+pro6
        );
    }
}
function getlist()
{
    $.ajax({
        type: "GET",
        url: "manager.do/order/list",
        dataType: "json",
        data:{
            page:nowpage,
            num:onepagenum
        },
        success: function(result){
            if(result.result!=null)
            {
                toastr.error("server error");
                return;
            }
            render(result);
         },
         error: function(){
            toastr.error("server error");
         }
     });
}
function getpagenum()
{
    $.ajax({
        type: "GET",
        url: "manager.do/order/num",
        dataType: "json",
        success: function(result){
            pagenum=Math.ceil(result.result/onepagenum);
            if(nowpage>pagenum&&pagenum!=0) {
                toastr.warning("no such page! Back to home page in 2s...");
                jump("admin/index.html",2200);
            } 
            else {
                getlist();
            }
         },
         error: function(){
            toastr.error("server error");
         }
     });
}
function loadpage()
{
    var page=GetQueryString("page");
    if(page=="")page=1;
    nowpage=page;
    getpagenum();
}