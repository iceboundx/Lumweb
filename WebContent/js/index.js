window.onload = function news() {
	setTimeout(function(){   
		$(".jumbotron").fadeIn("slow");
		$(".jumbotron").slideDown("slow"); }
		, 700);
};
function get_data()
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
        xmlHttp.open("GET", "index.conf", false);
        xmlHttp.send(null);
        var res=JSON.parse(xmlHttp.responseText);
        return res;
    }
    else
    {
        alert("Your browser does not support XMLHTTP.");
        return null;
    }
}

function put_data()
{
    var islog=GetQueryString("islog");
    if(islog==1)toastr.success("Login success! Welcome!");
    else if(islog==2)toastr.success("Log out success! Good Bye!");
	var res=get_data();
    $("#home-news").text(res.home_news);
    changeBar();
}