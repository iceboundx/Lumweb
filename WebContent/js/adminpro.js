var pagenum=5;
var nowpage=1;
var is_change=0;
var pro1=['   <div class="row" id="'].join("");
var pro2=['">',
'			<div class="row-pro">',
'			<div class="col-md-8 font">',
'						<span class="proid">'].join("");
var pro3=['						</span> <span class="proname">'].join("");
var pro4=['						</span>',
'						</div>',
'  			<div class="col-md-4 row-pro">',
'				<button type="button" class="btn btn-primary aaa row-pro" data-toggle="modal" data-target="#myModalchange" onclick="putchange(this)">change</button>',
'				<button type="button" class="btn btn-primary aaa row-pro" onclick="del(this)" >delete</button>',
'				<button type="button" class="btn btn-primary aaa" data-toggle="modal" data-target="#modalupload" onclick="putupload(this)">image</button>',
'				</div> </div></div>'].join("");
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
    $('#prolist').html("");
    for(i=0;i<productlist.length;i++){
        $('#prolist').append(
            pro1+'pro'+productlist[i].pid+
            pro2+'id: '+productlist[i].pid+
            pro3+'name: '+productlist[i].name+
            pro4
        );
    }
    endLoad();
}
function getlist()
{
    $.ajax({
        type: "GET",
        url: "manager.do/product/list",
        dataType: "json",
        data:{
            page:nowpage,
            num:5
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
        url: "manager.do/product/num",
        dataType: "json",
        success: function(result){
            var allpro=result.result;
            pagenum=Math.ceil(allpro/5.0);
            if(nowpage>pagenum)
            {
                if(is_change==1)
                {            
                    jump("product.html?page=1",1000);
                    return;
                }
                toastr.warning("no such page! Back to home page in 2s...");
                jump("home.html",2200);
                return;
            }
            getlist(result);
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
		window.location.href="login.html";
		return;
    }
    var page=GetQueryString("page");
    if(page=="")page=1;
    nowpage=page;
    getpagenum();
}
function putchange(obj)
{
   var pid=$(obj).parent().parent().parent().attr("id");
   if(pid==null){
        $('#modal-label').html("Add Product");
        $('#modal-id').val("");
        $('#modal-id').attr("disabled",false);        
        $('#modal-name').val("");
        $('#modal-desc').val("");
        $('#modal-price').val("");
        $('#modal-stock').val("");     
        $('#modal-state').val("");    
        return;
   }
   $.ajax({
    type: "GET",
    url: "manager.do/product/detail",
    dataType: "json",
    data:{
        pid:pid.substr(3,pid.length)
    },
    success: function(result){
        $('#modal-id').val(result.pid);
        $('#modal-id').attr("disabled",true);        
        $('#modal-name').val(result.name);
        $('#modal-desc').val(result.shortdesc);
        $('#modal-price').val(result.price);
        $('#modal-stock').val(result.stock);     
        $('#modal-state').val(result.state);     
     },
     error: function(){
        toastr.error("server error");
     }
 });
}
function putupload(obj)
{

   var pid=$(obj).parent().parent().parent().attr("id");
   if(pid==null){
       toastr.error("error!!!"); 
        return;
   }
   var id=pid.substr(3);
   setproid(id);
   $("#modal-big").attr('src',"../images/product/pro"+id+".jpg");
   $("#modal-small").attr('src',"../images/product/thum"+id+".jpg");
   $("#modal-detail").attr('src',"../images/product/detail"+id+".jpg");
   $('#modal-btn-a').prop("disabled",true);
   $('#modal-btn-b').prop("disabled",true);
   $('#modal-btn-c').prop("disabled",true);
   var file = $('#fileToUpload')[0];
   if(file.outerHTML){
       file.outerHTML = file.outerHTML;
   }else{
       file.value = '';
   }
   $('#fileName').html("");
   $('#fileSize').html("");
   $('#fileType').html("");
   $('#fileName').html("");
}
function reload(id)
{
   $("#modal-big").attr('src',"../images/product/pro"+id+".jpg");
   $("#modal-small").attr('src',"../images/product/thum"+id+".jpg");
   $("#modal-detail").attr('src',"../images/product/detail"+id+".jpg");
}
function confirm()
{
    var pro=
    {
        pid:$('#modal-id').val(),
        name:$('#modal-name').val(),
        shortdesc:$('#modal-desc').val(),
        price:$('#modal-price').val(),
        state:$('#modal-state').val(),  
        stock:$('#modal-stock').val() 
    }
    if(pro.pid==null||pro.name==null||pro.shortdesc==null||pro.price==null||pro.state==null){
        toastr.warning(" Illegal input! ");
        return;
    }
    var addorchange;
    if($('#modal-id').attr('disabled')=='disabled')addorchange="change";
    else addorchange="add";

    $.ajax({
        type: "POST",
        url: "manager.do/product/"+addorchange,
        dataType: "json",
        contentType: 'application/json;charset=utf-8',
        data:JSON.stringify(pro),
        success:function(result){
            if(result.result=="no")
            {
                toastr.error("Something wrong! Please check again...");
                return;
            }
            $("#myModalchange").modal('hide');
            loadpage();
            toastr.success("save success!");
         },
         error:function(){
            toastr.error("server error");
         }
    });
}
function del(obj)
{
    var pid=$(obj).parent().parent().parent().attr("id");
    $.ajax({
        type: "POST",
        url: "manager.do/product/del",
        dataType: "json",
        data:{
            pid:pid.substr(3,pid.length)
        },
        success:function(result){
            if(result.result=="no")
            {
                toastr.error("Something wrong! Please check again...");
            }
            is_change=1;
            loadpage();
            toastr.success("delete success!");
         },
         error:function(){
            toastr.error("server error");
         }
    });
}