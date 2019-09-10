function login()
{
  $.ajax({
         type: "POST",
         url: "manager.do/login",
		 dataType: "json",
         data:"uid="+$("#uid").val()+"&password="+hex_md5(encodeURIComponent($("#password").val())),  //传的数据  form表单 里面的数"
         success: function(result){
            if(result.result=="yes")
            window.location.href="home.html";
            else toastr.error('Wrong password or userID!');
         },
		 error: function(){
            toastr.error('Server error!');	
		 }
     });
}
function logtest(form,evt)
{
	evt.preventDefault();
	login();
	return false;
}
function loadpage()
{
	if(is_login())
	{
		window.location.href="home.html";
	}
}