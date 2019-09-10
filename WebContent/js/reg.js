// JavaScript Document
function is_exist(id)
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
        xmlHttp.open("GET", "user.do/exist?uid="+id, false);
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
function check(){
	if ($("#passworda").val()!=$("#passwordb").val()){
		toastr.warning('The two input passwords must be consistent!');
		return false;
	}
	var id=$("#uid").val();
	if(is_exist(id))
	{
		toastr.warning('Your phone number has been registered!');
		return false;
	}
	var nowDate=new Date();
    var birthday=new Date($("#birthday").val());
	if(birthday.getTime()>nowDate.getTime())
	{
		toastr.warning("Your birthday can't be in the future!");
		return false;
    }
	return true;
}
function reg(){
	if(!check())
	{
		return false;
	}
	var user={
		uid:$("#uid").val(),
		password:hex_md5(encodeURIComponent($("#passworda").val())),
		nickname:$("#nickname").val(),
		address:$("#address").val(),	
		birthday:$("#birthday").val()	
	};
	startLoad();
	$.ajax({
		type: "POST",
		url: "user.do/reg",
        async:false,
		dataType: "json",
		data:JSON.stringify(user),
		success: function(result){
				if(result.result=="yes")
				{
					toastr.success('Registration successful! Return to the home page after 2 seconds');
					endLoad();
					jump("index.html",2300);
					return false;
				}
				else
				{
					toastr.error('Registration failed for unknown reasons...');
					endLoad();
					return false;
				}
			},
		error: function(){
			toastr.error('server error!');
			return false;
		}
		});
		return false;
}
function regtest(form,evt)
{
	evt.preventDefault();
	reg();
	return false;
}
function putdata()
{
	changeBar();
	if(is_login())
	{
		$("#but").attr('disabled','true');
		toastr.error("You've already signed up.. Return to the home page after 2 seconds");
		jump("index.html",2300);
	}
}