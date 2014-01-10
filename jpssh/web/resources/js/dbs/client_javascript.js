var ONCE = false;
var timestamp = true;
var printopt = false;
var DA = (document.all) ? 1 : 0;
window.onerror = handle_error;

function handle_error() {
	if (printopt) {
		msg = "\nNothing was printed. \n\nTo cancel this print job, ";
		msg = msg + "click on the printer icon in the toolbar above.";
		alert(msg);
	}

	printopt = false;
	return true;
}

var bV = parseInt(navigator.appVersion);
var NS4 = (document.layers) ? true : false;
var IE4 = ((document.all) && (bV >= 4)) ? true : false;
var ver4 = (NS4 || IE4) ? true : false;

function doingprint() {
	printopt = true;
	// if (IE4 && ver4)
	// { printvb(); }
	// else
	// { window.print();}
	window.print();
	printopt = false;

}

function MM_swapImgRestore() {
	if (document.MM_swapImgData != null) {
		for ( var i = 0; i < (document.MM_swapImgData.length - 1); i += 2) {
			document.MM_swapImgData[i].src = document.MM_swapImgData[i + 1];
		}
	}
}

function MM_preloadImages() {
	if (document.images) {
		var imgFiles = MM_preloadImages.arguments;
		if (document.preloadArray == null)
			document.preloadArray = new Array();

		var i = document.preloadArray.length;
		// with (document)
		for ( var j = 0; j < imgFiles.length; j++)
			if (imgFiles[j].charAt(0) != "#") {
				document.preloadArray[i] = new Image;
				document.preloadArray[i++].src = imgFiles[j];
			}
	}
}

function MM_swapImage() {
	var i;
	var j = 0;
	var objStr;
	var obj;
	var swapArray = new Array;
	var oldArray = document.MM_swapImgData;
	for (i = 0; i < (MM_swapImage.arguments.length - 2); i += 3) {
		objStr = MM_swapImage.arguments[(navigator.appName == 'Netscape') ? i
				: i + 1];
		if ((objStr.indexOf('document.layers[') == 0 && document.layers == null)
				|| (objStr.indexOf('document.all[') == 0 && document.all == null))
			objStr = 'document'
					+ objStr.substring(objStr.lastIndexOf('.'), objStr.length);
		obj = eval(objStr);
		if (obj != null) {
			swapArray[j++] = obj;
			swapArray[j++] = (oldArray == null || oldArray[j - 1] != obj) ? obj.src
					: oldArray[j];
			obj.src = MM_swapImage.arguments[i + 2];
		}
	}
	document.MM_swapImgData = swapArray; // used for restore
}

function GetTip(szURL) {
	window
			.open(
					szURL,
					"Tip",
					"toolbar=0,width=610,height=350,directories=0,status=0,scrollbars=1,resizable=1,menubar=0,location=0")
}

function openUrl(szURL) {
	window
			.open(
					szURL,
					"ExternalSite",
					"toolbar=1,width=610,height=350,directories=0,status=1,scrollbars=1,resizable=1,menubar=1,location=1")
}

function CloseWin() {
	window.close()
}

function DoCancel(szURL, bWarning) {
	if (!bCheckOnce(true))
		return;

	if (bWarning) {
		alert("This transaction has been cancelled at your request");
	}

	document.location.href = szURL;
}

function showVerisignCert() {
	var url = "/help/ib/security_information.htm";
	sealWin = window
			.open(
					url,
					"Security",
					'toolbar=0,location=0,directories=0,status=1,menubar=1,scrollbars=1,resizable=1,width=500,height=450');
}

function bCheckOnce(Set) {
	var ret;
	if (ONCE) {
		alert("You can only submit this request once!");
		ret = false;
	} else
		ret = true;

	if (Set)
		ONCE = true;

	return ret;
}

function SetOnce() {
	ONCE = true;
	return;
}

function ParseUniqueURL(szURL) {
	var mydate = new Date();
	var tmpURL = szURL + "&RandomId=" + mydate.getTime();
	// alert(tmpURL)
	return tmpURL;

}

function SetRandom(myForm) {
	var mydate = new Date();
	// myForm.RandomId.value = mydate.getTime();
	// alert("RandomId = " + myForm.RandomId.value);
	return true;
}

function OpenPrintWindow(strContentName, strSkinName) {
	// prepare content for print preview
	objPreviewContent = createPreviewContent(strContentName);
	// check if there is content for display
	if (objPreviewContent.hasChildNodes()) {
		// open preint preview window
		openPrintPreview(strContentName, objPreviewContent, strSkinName);
	}
}

function createPreviewContent(strContentName) {
	var bodyObject = document.getElementsByTagName(strContentName);
	if (bodyObject.length <= 0) {
		return false;
	} else {
		var contentObject = getFirstChild(bodyObject[0]);
		newContentObject = contentObject.cloneNode(true);
		var strContentToShow = contentObject.innerHTML;

		var forDisplay = document.createElement('div');
		forDisplay.id = 'forDisplay';
		forDisplay.appendChild(newContentObject);

		forDisplay = selectHTMLElements(forDisplay, 'input', contentObject);
		forDisplay = selectHTMLElements(forDisplay, 'select', contentObject);
		forDisplay = selectHTMLElements(forDisplay, 'textarea', contentObject);

		forDisplay = removeElementByTagName(forDisplay, 'script');
		forDisplay = removeElementByTagNameAndAttribute(forDisplay, 'a',
				'name', 'printer');
		forDisplay = removeElementByTagNameAndAttribute(forDisplay, 'a',
				'name', 'help');
		forDisplay = removeElementByTagNameAndAttribute(forDisplay, 'input',
				'type', 'button');
		forDisplay = removeElementByTagNameAndAttribute(forDisplay, 'input',
				'type', 'reset');

		forDisplay = removeAttributeByElementTagName(forDisplay, 'a', 'href');
		forDisplay = removeAttributeByElementTagName(forDisplay, 'a', 'onclick');
		forDisplay = removeAttributeByElementTagName(forDisplay, 'a', 'onblur');
		forDisplay = replaceElementByTagName(forDisplay, 'a', 'span');

		tableTags = forDisplay.getElementsByTagName('table');
		for (i = 0; i < tableTags.length; i++) {
			if (parseInt(tableTags[i].getAttribute('width')) > parseInt('600')) {
				tableTags[i].setAttribute('width', '100%');
			}
		}

		var previewContent = document.createElement('div');
		previewContent.id = 'previewContentID';

		// add document heading
		var previewHeader = document.createElement('h3');
		var previewHeader_text = document
				.createTextNode('This is a print preview page');
		previewHeader.appendChild(previewHeader_text);

		var preview_para = document.createElement('p');
		var cancelLink = document.createElement('a');
		cancelLink.setAttribute("href", "javascript:window.close()");
		var cancelLink_text = document.createTextNode('Close this window.');
		cancelLink.appendChild(cancelLink_text);
		preview_para.appendChild(cancelLink);

		// Put it all toegether
		previewContent.appendChild(previewHeader);
		previewContent.appendChild(preview_para);
		previewContent.appendChild(forDisplay);

		return previewContent;
	}
}

function openPrintPreview(strContentName, objPreviewContent, strSkinName) {
	var WindowWidth = parseInt(document.getElementsByTagName(strContentName)[0].offsetWidth) + 50;
	var WindowHeight = parseInt(document.getElementsByTagName(strContentName)[0].offsetHeight) + 130;

	var WindowTop = 0;
	var WindowLeft = 0;
	var IsTallerThanScreen = true;
	var IsWiderThanScreen = true;
	var printLayoutWidth = 630;

	// checks if the table is taller than the screen
	if ((screen.availHeight - WindowHeight) > 0) {
		WindowTop = ((screen.availHeight - WindowHeight) / 2);
		IsTallerThanScreen = false;
	} else {
		WindowHeight = screen.availHeight - 30;
		IsTallerThanScreen = true;
	}
	if (WindowHeight > 700) {
		WindowHeight = 700;
		IsTallerThanScreen = true;
	}
	// checks if the table is wider than the screen
	if ((screen.availWidth - WindowWidth) > 0) {
		WindowLeft = ((screen.availWidth - WindowWidth) / 2);
		IsWiderThanScreen = false;
	} else {
		WindowWidth = screen.availWidth - 30;
		IsWiderThanScreen = true;
	}
	if (WindowWidth > 700) {
		WindowWidth = 700;
		IsWiderThanScreen = true;
	}

	var sFeatures = "";
	if (IsWiderThanScreen || IsTallerThanScreen)
		sFeatures = "location=no,menubar=no,scrollbars=yes,resizable=no,status=no,toolbar=no,width="
				+ WindowWidth
				+ ",height="
				+ WindowHeight
				+ ",top="
				+ WindowTop
				+ ",left=" + WindowLeft;
	else
		sFeatures = "location=no,menubar=no,scrollbars=no,resizable=no,status=no,toolbar=no,width="
				+ WindowWidth
				+ ",height="
				+ WindowHeight
				+ ",top="
				+ WindowTop
				+ ",left=" + WindowLeft;

	var printPreviewWin = window.open("", "_blank", sFeatures);

	printPreviewWin.document.open();
	printPreviewWin.document.write('<html><head><title>DBS iBanking</title>');
	printPreviewWin.document.write('<link rel="stylesheet" href="'
			+ strSkinName + '/style/global_dbs.css" type="text/css"/>');
	printPreviewWin.document
			.write('</head><body onLoad="self.print()"><center>');
	printPreviewWin.document
			.write('<TABLE width="'
					+ 600
					+ '" cellSpacing="0" cellPadding="0" border="0"><TBODY><TR><TD align="center">');
	printPreviewWin.document.write(objPreviewContent.innerHTML);
	printPreviewWin.document.write('</TD></TR></TBODY></TABLE>');
	printPreviewWin.document.write('</center></body></html>');
	printPreviewWin.document.close();
	printPreviewWin.focus();
}

function cancelPrintPreview(doc) {
	// remove the preview content
	var printPreview = doc.getElementById('previewContentID');
	var printPreviewBody = printPreview.parentNode;
	printPreviewBody.removeChild(printPreview);
	printPreview = doc.getElementById('forDisplay');
	printPreviewBody = printPreview.parentNode;
	printPreviewBody.removeChild(printPreview);
	window.close();
}

function getFirstChild(node) {
	firstChild = node.firstChild;
	while (firstChild.nodeType != 1) {
		firstChild = firstChild.nextSibling;
	}
	return firstChild;
}

function removeAttributeByElementTagName(objDocument, strTagName,
		strAttributeName) {
	var elements = objDocument.getElementsByTagName(strTagName);
	for (i = 0; i < elements.length; i++) {
		elements[i].removeAttribute(strAttributeName);
	}
	return objDocument;
}

function removeElementByTagName(objDocument, strTagName) {
	var elements = objDocument.getElementsByTagName(strTagName);
	for (i = 0; i < elements.length; i++) {
		elements[i].parentNode.removeChild(elements[i]);
	}
	return objDocument;
}

function selectHTMLElements(objNewDocument, strTagName, objOldDocument) {

	var elements = objOldDocument.getElementsByTagName(strTagName);
	var newElements = objNewDocument.getElementsByTagName(strTagName);

	for (i = 0; i < elements.length; i++) {

		if ((elements[i].getAttribute('type') == 'checkbox')
				&& elements[i].checked) {
			newElements[i].checked = true;
			newElements[i].defaultChecked = true;
			newElements[i].disabled = true;
		} else if ((elements[i].getAttribute('type') == 'checkbox')
				&& !elements[i].checked) {
			newElements[i].disabled = true;
		} else if ((elements[i].getAttribute('type') == 'radio')
				&& elements[i].checked) {
			newElements[i].defaultChecked = true;
			newElements[i].disabled = true;
		} else if ((elements[i].getAttribute('type') == 'radio')
				&& !elements[i].checked) {
			newElements[i].defaultChecked = false;
			newElements[i].disabled = true;
		} else if (elements[i].selectedIndex && elements[i].selectedIndex != -1) {
			newElements[i].options[elements[i].selectedIndex].selected = true;
			newElements[i].options[elements[i].selectedIndex].defaultSelected = true;
			newElements[i].disabled = true;
		} else if (elements[i].getAttribute('type') == 'text') {
			newElements[i].defaultValue = newElements[i].value;
			newElements[i].readOnly = true;
		} else if (strTagName == 'textarea') {
			newElements[i].defaultValue = elements[i].value;
			newElements[i].readOnly = true;
		} else {
			newElements[i].disabled = true;
		}
	}
	return objNewDocument;
}

function removeElementByTagNameAndAttribute(objDocument, strTagName,
		strAttributeName, strNameAttributeValue) {
	var elements = objDocument.getElementsByTagName(strTagName);
	bMoreToRemove = true;
	deleteCount = 0;
	while (bMoreToRemove) {
		for (i = 0; i < elements.length; i++) {
			if (elements[i].getAttribute(strAttributeName) == (strNameAttributeValue)) {
				removedNode = elements[i].parentNode.removeChild(elements[i]);
				deleteCount++;
			}
		}
		elements = objDocument.getElementsByTagName(strTagName);
		if (deleteCount == 0) {
			bMoreToRemove = false;
		}
		deleteCount = 0;
	}
	return objDocument;
}
function replaceElementByTagName(objDocument, strTagNameOld, strTagNameNew) {
	var newElement = null;
	var elements = objDocument.getElementsByTagName(strTagNameOld);
	bMoreToRemove = true;
	deleteCount = 0;
	while (bMoreToRemove) {
		for (i = 0; i < elements.length; i++) {
			newElement = document.createElement(strTagNameNew);
			newElement.innerHTML = elements[i].innerHTML;
			newElement.className = "MFBodyText";
			removedNode = elements[i].parentNode.replaceChild(newElement,
					elements[i]);
			deleteCount++;
		}
		elements = objDocument.getElementsByTagName(strTagNameOld);
		if (deleteCount == 0) {
			bMoreToRemove = false;
		}
		deleteCount = 0;
	}
	return objDocument;
}
function boldSignValue(signObjID, displayObjID, isAmountValue, isSecondField) {
	var signObj = document.getElementById(signObjID);
	var signObjVal = signObj.innerHTML.replace(/^\s\s*/, '').replace(/\s\s*$/,
			'');
	var newSignOjbVal = "";
	var headerTag = "<b style='font-weight: Bold; font-size: 16px; color:  #FF0000 ; background: #E9E9E9;'>", tailTag = "</b>";
	if (isAmountValue) {
		var splitedStr = signObjVal.split(".");
		signObjVal = splitedStr[0];
		for ( var i = splitedStr.length; i > 1; i--) {
			newSignOjbVal = "." + splitedStr[i - 1] + newSignOjbVal;
		}
	}
	var j = 1;
	var i = 0;
	var tempChar;
	for (i = signObjVal.length; i > 0; i--) {
		tempChar = signObjVal.charAt(i - 1);
		if (isNaN(tempChar)) {
			newSignOjbVal = tempChar + newSignOjbVal;
		} else {
			newSignOjbVal = headerTag + tempChar + tailTag + newSignOjbVal;
			j++;
			if (j > 8) {
				break;
			}
		}
	}
	if (i > 1) {
		newSignOjbVal = signObjVal.substr(0, i - 1) + newSignOjbVal;
	}
	if (!isSecondField) {
		while (j <= 4) {
			j++;
			newSignOjbVal = headerTag + "0" + tailTag + newSignOjbVal;
		}
	}
	document.getElementById(displayObjID).innerHTML = newSignOjbVal;
}