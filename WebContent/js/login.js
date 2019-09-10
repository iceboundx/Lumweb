function login()
{
  $.ajax({
         type: "POST",
         url: "user.do/login",
		 dataType: "json",
         data:"uid="+$("#uid").val()+"&password="+hex_md5(encodeURIComponent($("#password").val())),  //传的数据  form表单 里面的数"
         success: function(result){
            if(result.result=="yes")
            window.location.href="index.html?islog=1";
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
function putdata()
{
    changeBar();
    if(is_login()){
        $("#btn").attr('disabled','true');
        toastr.error("You've already signed up.. Return to the home page after 2 seconds");
		jump("index.html",2300);
    }
}