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
        xmlHttp.open("GET", "manager.do/islogin", false);
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
function isIntNum(val){
    var regPos = / ^\d+$/; // 非负整数
    var regNeg = /^\-[1-9][0-9]*$/; // 负整数
    if(regPos.test(val) || regNeg.test(val)){
        return true;
    }else{
        return false;
    }
}
function jump(href, timeout) {
    setTimeout(function(){
        window.location.href=href;
    },timeout);
}
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
function logout() {
    $.ajax({
        type: "POST",
        url: "manager.do/exit",
        dataType: "json",
        success: function(result){
            if(result.result=="yes")
            window.location.href="../index.html?islog=2";
            else toastr.warning("already log out!");
        },
        error: function(){
            toastr.error("server error");
        }
        }); 
}
function startLoad() {
    $("#loading").show("fast");
}
function endLoad() {
    $("#loading").hide("fast");
}