var pat=/[a-zA-Z0-9]{6,12}/
function check(){
    var newpass=$("#newpass").val();
	if ($("#newpass").val()!=$("#repeat").val()){
		toastr.warning('The two input passwords must be consistent!');
		return false;
    }
    if (pat.test(newpass)==false){
		toastr.warning('The new password is illegal');
		return false;
    }
	return true;
}
function change() {
    if(!check())return;
    startLoad();
      $.ajax({
            type: "POST",
            url: "user.do/changepass",
            dataType: "json",
            data:"oldpassword="+hex_md5(encodeURIComponent($("#oldpass").val()))+"&newpassword="+hex_md5(encodeURIComponent($("#newpass").val())),  //传的数据  form表单 里面的数"
            success: function(result){
                if(result.result=="yes"){
                    endLoad();
                    toastr.success('Change success!');
                    $("#oldpass").val("");
                    $("#newpass").val("");
                    $("#repeat").val("");
                }
                else {
                    endLoad();
                    toastr.error('Change failure! Maybe the old password is wrong'); 
                    $("#oldpass").val("");
                    $("#newpass").val("");
                    $("#repeat").val("");
                }
            },
            error: function(){
                    toastr.error('server error!');
            }
            });
}
function loadpage(){
    startLoad();
    if(!is_login()){
        toastr.warning("You have to login first! Will go to the login page in 2s...");
        jump("login.html",2200);
        return;
    }
    $.ajax({
        type: "GET",
        url: "user.do/",
        dataType: "json",
        success: function(result){  
            $('#uid').html("TEL: "+result.uid);   
            $('#username').html(result.nickname);              
         },
         error: function(){
            toastr.error("server error");
         }
     });
    endLoad();
}