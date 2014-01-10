/**
 * <input name="localebutton" type="button" id="localebutton"  value="English" onclick="onButtonSubmit('/tw/retail/logon/locale?id=1');"/>
   <input name="localebutton" type="button" id="localebutton"  value="中文" onclick="onButtonSubmit('/tw/retail/logon/locale?id=7');"/>
 
 * <form name="DBSLogonForm" method="post" action="/tw/retail/logon/nomfa" onsubmit="doOnSubmit()" autocomplete="off">
     <input type="hidden" name="random" value="824578FFDEEDA173">
     <input type="text" name="j_username" maxlength="32" size="12" value="" onkeyup="this.value=this.value.toUpperCase();" class="textfield_220">
     <input type="password" name="j_password" maxlength="30" size="12" value="" class="textfield_220">
     <input name="Submit"  type="submit" id="formbutton"  value="Submit" class="redbutton redsize5" /></td>
     <input name="reset" type="reset" id="formbutton"  value="Clear" class="button size7"  style='margin:0px;padding:0px;top:0px'/></td>
   </form>
   <input type="hidden" name="individualUser" value="true"/>
 * 
 */
 
var submited=0;
var publickey="a77af6e54b8e7169ad79d65b98a7b60e69f92462b3830492b5b19edb917ddac9ad4b1e241a22c7eda89e2507f34a83e11913477afd73b9eccbe36d81b9862abe1bd97e806b9ea0bfeed127016ce1594a1c7617263502ba45565dc0bd6e0a68e308c1606e03e6578086aac8066f9788303266ddb26c313a55c11b0b29433dcc6b";
if(publickey.length==0){
	submited=1;
}
function doOnSubmit(){
	if(submited>0){
		$("form").submit(function(){return false;});
		return;
	}
	encryptPassword();
	submited=1;
}
function onButtonSubmit(action){
	document.forms[0].action=action;
	document.forms[0].submit();
}

function encryptPassword(){
	var publickey="a77af6e54b8e7169ad79d65b98a7b60e69f92462b3830492b5b19edb917ddac9ad4b1e241a22c7eda89e2507f34a83e11913477afd73b9eccbe36d81b9862abe1bd97e806b9ea0bfeed127016ce1594a1c7617263502ba45565dc0bd6e0a68e308c1606e03e6578086aac8066f9788303266ddb26c313a55c11b0b29433dcc6b"; 
	var random="824578FFDEEDA173";
	var encrypted;
	var password=document.forms['DBSLogonForm'].elements["j_password"].value;
	if(password.length==0){
		return;
	}
	var ribLogon = new RIBLogon();
	ribLogon.setKeyValue(publickey);
	encrypted = ribLogon.encyptPwd(password, random);
	$(":password").attr("maxlength","256");
	document.forms['DBSLogonForm'].elements["j_password"].value=encrypted;
}

function submitForm(url) { document.forms[0].action="/tw/retail" + url; document.forms[0].submit(); } 
 