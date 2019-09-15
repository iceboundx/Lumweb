var pro1=['<div class="buy" id="'].join("");
var pro2=['">',
'				<div class="buy_small"><input type="checkbox" name="cc" class="checkCss" onclick="check(this)" style="width:30px;height:30px;"/></div>',
'      			<div class="buy_big">',
'					<span class="picture"><img src="'].join("");
var pro3=['					" height="130px" alt=""/></span>',
'					<span class="title">'].join("");
var pro4=['					</span>',
'				</div>',
'      			<div class="buy_small money"></div>',
'      			<div class="buy_mid count">',
'					<div class="btn-group" role="group" aria-label="...">',
'  						<input type="button" class="reduceCss btn btn-default" onclick="dec(this)" value="➖"/>',
' 						<input name="quanlity" type="text" class="inputCountCss btn btn-default" value="'].join("");
var pro5=['" size="1" disabled/>',
' 					    <input type="button" class="addCss btn btn-default"  onclick="plus(this)" value="➕"/>',
'					</div>',
'				</div>',
'      			<div class="buy_small tMoney" name="moneyprice" >'].join("");
var pro6=['				</div>',
'      			<div class="buy_small"><a href="javascript:void(0);" onclick="remove(this)">×</a></div>',
'			</div>'].join("");
function dec(obj)
{
	var now=$(obj).next().attr('value');
	if(now=='1'){$(obj).prop('disabled', true);return;}
	$(obj).next().attr('value',parseInt(now)-1);
	now=$(obj).next().attr('value');
	if(now=='1')$(obj).prop('disabled', true);
	if(now=='99')$(obj).next().next().prop('disabled', false);
	var fa=$(obj).parent().parent().parent().attr('id');
	var pid=fa.substr(3,fa.length);
	$.ajax({
        type: "POST",
        url: "cart.do/dec",
        dataType: "json",
        data:"pid="+pid,
        success: function(result) {
			if(result.result!="yes") toastr.error("server error");
        },
        error: function(){
            toastr.error("server error");
         }
    });
	getTotalprice();
}
function plus(obj)
{
	var now=$(obj).prev().attr('value');
	$(obj).prev().attr('value',parseInt(now)+1);
	now=$(obj).prev().attr('value');
	if(now=='100')$(obj).prop('disabled', true);
	if(now=='2')$(obj).prev().prev().prop('disabled', false);
	var fa=$(obj).parent().parent().parent().attr('id');
	var pid=fa.substr(3,fa.length);
	$.ajax({
        type: "POST",
        url: "cart.do/add",
        dataType: "json",
        data:"pid="+pid,
        success: function(result) {
			if(result.result!="yes") toastr.error("server error");
        },
        error: function(){
            toastr.error("server error");
         }
    });
	getTotalprice();
}
function remove(obj)
{
	var fa=$(obj).parent().parent().attr('id');
	var pid=fa.substr(3,fa.length);
	startLoad();
	$.ajax({
        type: "POST",
        url: "cart.do/remove",
        dataType: "json",
        data:"pid="+pid,
        success: function(result) {
			if(result.result!="yes") 
			{
				toastr.error("server error");
				endLoad();
			}
			else getcart();
        },
        error: function(){
			toastr.error("server error");
			endLoad();
         }
    });
}
function checkAll(obj)
{
	if($(obj).is(":checked")){
		$("input[name='cc']").prop("checked",true);
	}
	else{
		$("input[name='cc']").prop("checked",false);
	}
	getTotalprice();
}
function is_allcheck()
{
	var flag=true;
	$(".buy").each(function(i){
		if($(this).find("input[name='cc']").prop("checked")!=true)flag=false;
	});
	return flag;
}
function check(obj)
{
	if(is_allcheck())
	{
		$("#checkAll").prop("checked",true);
	}
	else
	{
		$("#checkAll").prop("checked",false);
	}
	getTotalprice();
}
function getTotalprice()
{
	var all=0.0;
	$(".buy").each(function(i){
		if($(this).find("input[name='cc']").prop("checked")==true)
		{
			var nowprice=parseFloat($(this).find("[name='moneyprice']").text());
			var nowquanlity=parseInt($(this).find("[name='quanlity']").val());
			all=all+nowprice*nowquanlity;
		}
	});
	$("#priceTotal").text(all.toFixed(2));
}
function confirm()
{
	var ret = new Array()
	var isok = 0;
	$(".buy").each(function(i){
		if($(this).find("input[name='cc']").prop("checked")==true)
		{
			var nowid=$(this).attr('id');
			ret.push(nowid.substr(3));
			isok=1;
		}
	});
	if(isok==0){
		toastr.warning("You choose no products!");
		return;
	}
	startLoad();
	$.ajax({
        type: "POST",
        url: "cart.do/pay",
		dataType: "json",
		data:JSON.stringify(ret),
        success: function(result){
			loadpage();
			if(result.result=="stock error"){
				toastr.warning("No enough stock for your products!");
			}
			else
			{
				toastr.success("Your order has been saved! Will go to the Order Center in 2s...");
				jump("order.html",2200);		
			}
         },
         error: function(){
            toastr.error("server error");
         }
     });
}
function getcart()
{
	$.ajax({
        type: "GET",
        url: "cart.do/",
        dataType: "json",
        success: function(result){
            if(result.result=="no")
            {
                toastr.warning("No login! return to the login page in 2s...");
                endLoad();
                jump("login.html",2200);
            }
            else{
				render(result);
                endLoad();
            }
         },
         error: function(){
            toastr.error("server error");
            endLoad();
         }
     });
}
function render(cart)
{
	$('#cart').html(" ");
	for(i=0;i<cart.length;i++){
		$('#cart').append(
			pro1+'pro'+cart[i].pid+
			pro2+'images/product/thum'+cart[i].pid+'.jpg'+
			pro3+cart[i].name+
			pro4+cart[i].quantity+
			pro5+cart[i].price+pro6
		);
	}
}
function loadpage()
{
	startLoad();
	if(is_login())
	getcart();
	else
	{
		toastr.warning("You have to login first! Will go to the login page in 2s...");
		jump("login.html",2200);
	}
}