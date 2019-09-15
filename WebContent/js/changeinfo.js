var patternbirth=/(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)/;
var patternnick=/[a-zA-Z0-9]{3,10}/;

function check()
{
    var nick=""+$('#modal-nick').val();
    var birth=""+$('#modal-birth').val();
    var address=""+$('#modal-address').val();
    if(patternnick.test(nick)==false)return false;
    if(patternbirth.test(birth)==false)return false;
    if(address.length>100)return false;
    return true;
}
function change()
{
    if(!check()){
        toastr.warning('The format of your information is wrong');
        return;
    }
    var usr=
    {
        nickname:$('#modal-nick').val(),
        address:$('#modal-address').val(),
        password:"",
        birthday:$('#modal-birth').val(),  
    }
    if(usr.nickname==null||usr.address==null||usr.birthday==null){
        toastr.warning(" Illegal input! ");
        return;
    }
    var addorchange="update";
    $.ajax({
        type: "POST",
        url: "user.do/"+addorchange,
        dataType: "json",
        contentType: 'application/json;charset=utf-8',
        data:JSON.stringify(usr),
        success:function(result){
            if(result.result=="no")
            {
                toastr.error("Something wrong! Please check again...");
                return;
            }
            else if(result.result=="nouser")
            {
                toastr.error("No such user!!!");
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
function putchange()
{
   $.ajax({
    type: "GET",
    url: "user.do/",
    dataType: "json",
    success: function(result){     
        $('#modal-nick').attr('value',result.nickname);
        $('#modal-address').attr('value',result.address);
        $('#modal-birth').attr('value',result.birthday);
     },
     error: function(){
        toastr.error("server error");
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
            $('#nickname').html("Nick name: "+result.nickname);
            $('#address').html("Address: "+result.address);
            $('#birth').html("Birthday "+result.birthday);
         },
         error: function(){
            toastr.error("server error");
         }
     });
     endLoad();
}