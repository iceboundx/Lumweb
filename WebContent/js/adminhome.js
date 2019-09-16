function getadmin()
{
	$.ajax({
		type: "GET",
		url: "manager.do/",
		dataType: "json",
		success: function(result){
            toastr.success("welcome! "+result.name);
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
	getadmin();
}