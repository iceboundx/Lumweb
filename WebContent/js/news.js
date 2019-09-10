var pagenum=5;
var nowpage=1;
var nowtype=null;
var list1='<li><a href="';
var list2='" ><span class="time">';
var list3='</span>';
var list4='</a></li>';
function render(articleList)
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
                if(nowtype==null)          
                return "?page="+page;
                else return "?page="+page+"&type="+nowtype;
 
            },
            //通过onPageClicked这个事件可以调用响应js 也可以跳转到页面
            //当单击操作按钮的时候, 会执行该函数
            onPageClicked: function (event,originalEvent,page) {
                currentPage = page
            }
        });
    }
    for(i=0;i<articleList.length;i++){
        $('#artlist').append(
            list1+"article.html?id="+articleList[i].id+
            list2+articleList[i].createTime.substr(0,10)+
            list3+articleList[i].title+
            list4
        );
    }
    endLoad();
}
function putdata(type,page)
{
    nowpage=page;
    var t;
    if(type==null)
    t={
        page:page,
        num:10
    };
    else t={
        type:type,
        page:page,
        num:10
    };
    $.ajax({
        type: "GET",
        url: "article.do/list",
        dataType: "json",
        data:t,
        success: function(result){
            var artlist=result;
            render(result);
         },
         error: function(){
            toastr.error("server error");
         }
     });
}
function getpagenum(type,page)
{
    var t;
    if(type==null)
    t=null;
    else t={
        type:type
    };
    $.ajax({
        type: "GET",
        url: "article.do/num",
        dataType: "json",
        data:t,
        success: function(result){
            pagenum=Math.ceil(result.result/10);
            if(page>pagenum&&pagenum!=0) {
                toastr.warning("no such page! Back to home page in 2s...");
                jump("index.html",2200);
            } 
            else {
                putdata(type,page);
            }
         },
         error: function(){
            toastr.error("server error");
         }
     });
}
function loadpage()
{
    startLoad();
    var type=GetQueryString("type");
    var page=GetQueryString("page");
    if(type=="")type=null;
    if(page=="")page=1;
    nowtype=type;
    if(type!=null&&type!="tech"&&type!="fina")
    {
        toastr.warning("no such type! Back to home page in 2s...");
        jump("index.html",2200);
        return;
    }
    if(type!=null)
    $('#'+type).attr("class","typeactive");
    getpagenum(type,page);
}