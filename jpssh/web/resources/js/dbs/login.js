/**
 * <form name="MainForm" method="post">
     <input type="hidden" name="flag" value="0"> -->
     <input type="hidden" name="isCancelClicked" value="0">
     <input type="hidden" name="isSubmitClicked" value="0">
     <input type="text" tabindex="1" maxlength="20" size="32" name="UID" id="UID"></td>
	 <input type="password" onkeyup="keyUp(event)" onkeydown="return onlyNumerics(event)" tabindex="2" maxlength="9" size="32" name="PIN" id="PIN" autocomplete="off">
     <input style="width:94px;" type="button" value="Submit" tabindex="3" onclick="CheckErr(document.forms['MainForm'])"  name="button" >
     <input style="width:94px;" type="button" value="Cancel" tabindex="4" onclick="javascript:main()" >
   </form>      
   <form name="LogonEventForm" action="/IB/Welcome" method="post">
	<input type="hidden" name="statemachineStateName" value="23e95b5b0a5b17ef00adb1d416bf0b08">
	<input type="hidden" name="statemachineEventName" value="LogonEvent">
	<input type="hidden" name="ENCRYPTED_PIN_BLOCK" value="">
	<input type="hidden" name="RAW_PIN_LENGTH" value="">
	<input type="hidden" name="RSA_KEY_INDEX" value="1">
	<input type="hidden" name="RANDOM_NUMBER" value="06163621636642557780">
	<input type="hidden" name="RANDOM_NUMBER_AR" value="C579071EA7CEEA92">
	<input type="hidden" name="USER_LOGON_NAME" value="">
	<input type="hidden" name="FROM_IB" value="TRUE">
	<input type="hidden" name="USER_AGENT" value="Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22">
   </form>
 * @param objForm
 */
var submitOnceErrorMsg="You can only submit this request once!";
var publicKey = "9E1C9EB9BD6E2333F8384194BCF06A0EB418779F436B129ED9EF0027DEEF11BF41F5C6AF94A0066658CA7A7ACC5EAA3B2011ED5283023C8EE272B731F29E9590AAAC2664676578DEB29E29F2D0F130F2E65AF8D2F7A458D97DACC7612430BF4FC2014B23EAD8EFDAD0A83F6D90D94A598EA72DC59DC2E014015854E14CCCDBC5";
var dbslogin = new DBSLogin();

function CheckErr(objForm) {
	if (document.MainForm.isCancelClicked.value == "0" && document.MainForm.isSubmitClicked.value == "0") {
		var userId = document.MainForm.UID.value;
		var bValid = true;
		var minlength = "5";
		var maxlength = "20";

		if (isWithinLengthRange(userId, minlength, maxlength) && !isNumeric(userId) && isAlphaNumeric(userId)) {
			bValid = true;
		} else {
			alert("Sorry, you have entered an invalid User ID. Please try again.");
			bValid = false;
		}

		if (bValid) {
			document.LogonEventForm.elements["USER_LOGON_NAME"].value = userId;
			handleLogin();
		} else {
			document.MainForm.UID.focus();
			return;
		}
	} else {
		alert(submitOnceErrorMsg);
		return;
	}
}

function openWin(szURL) {
	window.open(szURL, "Information", "toolbar=0,width=610,height=350,directories=0,status=0,scrollbars=1,resizable=1,menubar=0,location=0");
}

function handleLogin() {
	var pinNumber = document.MainForm.PIN.value;
	if (!isPinValid(pinNumber)) {
		alert("The PIN length should range from 6 to 9 digits. ");
		document.MainForm.PIN.value = "";
		document.MainForm.PIN.focus();
		return;
	}
	if (!bIsDigitOnly(pinNumber)) {
		alert("The PIN length should range from 6 to 9 digits. ");
		document.MainForm.PIN.value = "";
		document.MainForm.PIN.focus();
		return;
	}
	var randomNumber = 'C579071EA7CEEA92';
	var encydata;
	dbslogin.getPublickey(publicKey);
	encydata = dbslogin.RSA_EncryptPIN(randomNumber, pinNumber);
	document.LogonEventForm.ENCRYPTED_PIN_BLOCK.value = encydata;
	document.LogonEventForm.RAW_PIN_LENGTH.value = document.MainForm.PIN.value.length;
	document.MainForm.isSubmitClicked.value = "1";
	document.LogonEventForm.submit();
}
function doClear() {
	document.MainForm.UID.value = "";
	document.MainForm.PIN.value = "";
	window.focus();
	document.MainForm.UID.focus();
}

function main() {
	if (document.MainForm.isCancelClicked.value == "0" && document.MainForm.isSubmitClicked.value == "0") {
		alert("This transaction has been cancelled at your request. ");
		document.MainForm.isCancelClicked.value = "1";
		window.top.location.href = "http://www.dbs.com/sg/personal/ebanking/";
	} else {
		alert(submitOnceErrorMsg);
		return;
	}
}

function initialize() {
	window.focus();
	doClear();
}

function VeriOpen() {
	window.open("https://seal.verisign.com/splash?form_file=fdf/splash.fdf&dn=internet-banking.dbs.com.sg&lang=en", null,
					"height=450,width=525,status=no,toolbar=no,menubar=no,location=no,scrollbars=no");
}
