var pagenum=5;
var nowpage=1;
var is_change=0;
var onepagenum=8;
var pro1=[' <div class="row mg" id="'].join("");
var pro2=['">',
'  		<div class="col-md-9 font">',
'						<span class="artid">'].join("");
var pro3=['</span>',
'						</div>',
'  			<div class="col-md-3">',
'				<button type="button" class="btn btn-primary we"data-toggle="modal" data-target="#myModalchange">change</button>',
'				<button type="button" class="btn btn-primary we">delete</button>',
'				</div> </div>'].join("");
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
    $('#artlist').html("");
    for(i=0;i<productlist.length;i++){
        $('#artlist').append(
            pro1+'art'+productlist[i].id+
            pro2+productlist[i].title+pro3
        );
    }
}
function getlist()
{
    $.ajax({
        type: "GET",
        url: "manager.do/article/list",
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
        url: "manager.do/article/num",
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
    if(!is_login())
	{
		window.location.href="login.html";
		return;
	}
    var page=GetQueryString("page");
    if(page=="")page=1;
    nowpage=page;
    getpagenum();
}