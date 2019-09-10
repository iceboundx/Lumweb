var menu=['<div class="dropdown">',
'			  <a class="btn btn-default dropdown-toggle" type="button" style="background-color: transparent;" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">',
'				<font color="white" >PERSONAL</font>',
'				<span class="caret"></span>',
'			  </a>',
'			  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" style="	background-color: rgba(0,0,0,0.5);" id="menuUser">',
'				<li><a href="usercenter.html">User Center</a></li>',
'				<li><a "javascript:void(0);" onclick="log_out()">Log Out</a></li>',
'			  </ul>',
'			</div>'].join("");
function GetQueryString(name) { 
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
    var r = window.location.search.substr(1).match(reg); //获取url中"?"符后的字符串并正则匹配
    var context = ""; 
    if (r != null) 
    context = r[2]; 
    reg = null; 
    r = null; 
    return context == null || context == "" || context == "undefined" ? "" : context; 
    }
function is_login()
{
    xmlHttp=null;
    if(window.XMLHttpRequest)
    {// code for IE7, Firefox, Opera, etc.
        xmlHttp=new XMLHttpRequest();
    }
    else if(window.ActiveXObject)
    {// code for IE6, IE5
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    if(xmlHttp!=null)
    {
        xmlHttp.onreadystatechange=xmlcallback;
        xmlHttp.open("GET", "user.do/islogin", false);
        xmlHttp.send(null);
        var res=JSON.parse(xmlHttp.responseText);
        if(res.result=="yes")return true;
        else return false;
    }
    else
    {
        alert("Your browser does not support XMLHTTP.");
        return false;
    }
}
function xmlcallback()
{
    var xmlHttpRequest=this;
    if(xmlHttpRequest.status==0)return;
    if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status != 200)
    {
        toastr.error("server error");
    }
}
function sleep(delay) {
    var start = (new Date()).getTime();
    while ((new Date()).getTime() - start < delay) {
      continue;
    }
  }
function jump(href, timeout) {
    setTimeout(function(){
        window.location.href=href;
    },timeout);
}
function log_out() {
	$.ajax({
		type: "POST",
		url: "user.do/exit",
		dataType: "json",
		success: function(result){
            if(result.result=="yes")
            window.location.href="index.html?islog=2";
            else toastr.warning("already log out!");
        },
        error: function(){
            toastr.error("server error");
        }
        }); 
}
function changeBar()
{
    if(is_login())
    {
        $.ajax({
            type: "GET",
            url: "user.do/",
            dataType: "json",
            success: function(result){
                $("#userCenter").html(menu);
             },
             error: function(){
                toastr.error("server error");
             }
         });
    }
}
function startLoad() {
    $("#loading").show("fast");
}
function endLoad() {
    $("#loading").hide("fast");
}