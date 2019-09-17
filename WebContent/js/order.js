var od1=['  <div style="margin-top: 20px; margin-bottom: 40px;">',
'		  <div >',
'			  <div class="col-md-3 cc" >',
'				  <span id="ordtime" style="line-height: 30px"><strong>'].join("");
var od2=['</strong></span> ',
'			  </div>',
'			  <div class="col-md-6 cc" style="line-height: 30px">',
'			  <span id="ordid"><strong>ID:'].join("");
var od3=['</strong></span>',
'			  </div>',
'			  <div class="col-md-3 cc" style="line-height: 30px">',
'			  <span id="ordid"><strong>total:￡'].join("");
var od4=['</strong></span>',
'			  </div>',
'			  <div class="col-md-10 cc" >',
'				  <span style="line-height: 30px">Address:'].join("");
var od5=['</span>',
'			  </div>',
'			  <div class="col-md-2 cc" >',
'				  <span style="line-height: 30px">'].join("");
var od6=['</span>',
'			  </div>',
'			</div>'].join("");
var it1=['<div  style="margin-left: 0px; padding-left: 0px;border-left:2px solid rgba(245,245,245,1.00);border-bottom:2px solid rgba(245,245,245,1.00);',
'					   border-right:2px solid rgba(245,245,245,1.00);overflow:auto;"> ',
'			  <div class="col-md-4" style="padding-left: 10px; line-height: 120px;"  >',
'				  <font size="3">'].join("");
var it2=['</font>',
'			  </div>',
'			   <div class="col-md-1" align="center" style="padding-left: 0px; line-height: 120px;" >',
'				  <font size="3">￡'].join("");
var it3=['</font>',
'			  </div>',
'			   <div class="col-md-1" align="center" style="padding-left: 0px; line-height: 120px;" >',
'				  <font size="3">×'].join("");
var it4=['</font>',
'			  </div>',
'			   <div class="col-md-4"  align="center" style="padding-left: 0px; line-height: 120px;" >',
'				  <font size="3">total:￡'].join("");
var it5=['</font>',
'			  </div>',
'			  <div class="col-md-2" align="center" style="padding-left: 0px; line-height: 120px;" >',
'				  <button style="background: rgba(245,245,245,1.00);" type="button" class="btn btn-default navbar-btn">Pay</button>',
'			  </div>',
'		  </div>'].join("");
var itx=['</font>',
'			  </div>',
'		  </div>'].join("");
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
        var str=(od1+productlist[i].orderTime+od2+productlist[i].oid+
        od3+parseFloat(productlist[i].price).toFixed(2)+
        od4+productlist[i].address+
        od5+productlist[i].state+od6);
        var x=productlist[i].orderitems;
        for(j=0;j<x.length;j++){
            str+=(it1+x[j].name+
                it2+x[j].price+
                it3+x[j].quantity+
                it4+(parseFloat(x[j].price)*parseInt(x[j].quantity)).toFixed(2)+
                (j==0?it5:itx));
        }
        $('#ord').append(str);
    }
    endLoad();
}
function getlist()
{
    $.ajax({
        type: "GET",
        url: "order.do/list",
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
        url: "order.do/num",
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
    startLoad();
	if(!is_login())
	{
		toastr.warning("You have to login first! Will go to the login page in 2s...");
        jump("login.html",2200);
        return;
    }
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
    var page=GetQueryString("page");
    if(page=="")page=1;
    nowpage=page;
    getpagenum();
}