var pagenum=5;
var nowpage=1;
var is_change=0;
var onepagenum=5;
var pro1=[' <div class="row mg" id="'].join("");
var pro2=['">',
'  		<div class="col-md-9 font">',
'						<span class="artid">'].join("");
var pro3=['</span>',
'						</div>',
'  			<div class="col-md-3">',
'				<button type="button" class="btn btn-primary"data-toggle="modal" data-target="#myModalchange" onclick="putchange(this)">change</button>',
'				<button type="button" class="btn btn-primary" onclick="del(this)">delete</button>',
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
    $('#usr').html("");
    for(i=0;i<productlist.length;i++){
        $('#usr').append(
            pro1+'usr'+productlist[i].uid+
            pro2+productlist[i].uid+pro3
        );
    }
}
function getlist()
{
    $.ajax({
        type: "GET",
        url: "manager.do/user/list",
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
        url: "manager.do/user/num",
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
function putchange(obj)
{
   var uid=$(obj).parent().parent().attr("id");
   if(uid==null){
        $('#modal-label').html("Add user");
        $('#modal-id').attr('value',"");
        $('#modal-id').attr("disabled",false);        
        $('#modal-nick').attr('value',"");
        $('#modal-address').attr('value',"");
        $('#modal-birth').attr('value',"");
        $('#modal-password').val("");
        return;
   }
   $.ajax({
    type: "GET",
    url: "manager.do/user/",
    dataType: "json",
    data:{
        uid:uid.substr(3)
    },
    success: function(result){
        $('#modal-id').attr('value',result.uid);
        $('#modal-id').attr("disabled",true);        
        $('#modal-nick').attr('value',result.nickname);
        $('#modal-address').attr('value',result.address);
        $('#modal-birth').attr('value',result.birthday);
     },
     error: function(){
        toastr.error("server error");
     }
 });
}
function confirm()
{
    var ps=hex_md5(encodeURIComponent($('#modal-password').val()));
    if($('#modal-password').val()=="")ps="";
    var usr=
    {
        uid:$('#modal-id').val(),
        nickname:$('#modal-nick').val(),
        address:$('#modal-address').val(),
        password:ps,
        birthday:$('#modal-birth').val(),  
    }
    if(usr.uid==null||usr.nickname==null||usr.address==null||usr.birthday==null){
        toastr.warning(" Illegal input! ");
        return;
    }
    var addorchange;
    if($('#modal-id').attr('disabled')=='disabled')addorchange="update";
    else addorchange="add";

    $.ajax({
        type: "POST",
        url: "manager.do/user/"+addorchange,
        dataType: "json",
        contentType: 'application/json;charset=utf-8',
        data:JSON.stringify(usr),
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
    var uid=$(obj).parent().parent().attr("id");
    $.ajax({
        type: "POST",
        url: "manager.do/user/del",
        dataType: "json",
        data:{
            uid:uid.substr(3)
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