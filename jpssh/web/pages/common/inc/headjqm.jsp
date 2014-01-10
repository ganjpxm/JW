<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="Gan Jianping"/>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title id='Description'><fmt:message key='project.name'/></title>

<link rel="shortcut icon" type="image/png" href="<c:url value='/resources/style/default/image/jp/icon/jp_icon.png'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jquery.mobile.${cssSuffix}'/>" type="text/css" media="screen, projection">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jpw.jq.${cssSuffix}'/>" type="text/css" media="screen, projection">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jpw.jqm.${cssSuffix}'/>" type="text/css" media="screen, projection">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jpw.${cssSuffix}'/>" type="text/css" media="screen, projection">
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/i18n/i18n_${lang}.${jsSuffix}'/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/jquery/jquery.${jsSuffix}'/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/jquery/i18n/jquery_${lang}.${jsSuffix}'/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.bgiframe.${jsSuffix}'/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.validationEngine.${jsSuffix}'/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.cookie.${jsSuffix}'/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.nbspSlider.${jsSuffix}'/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.form.${jsSuffix}'/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.timers.${jsSuffix}'/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/jquery/mobile/jquery.mobile.${jsSuffix}'/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/jquery/mobile/jqm.simpledialog2.${jsSuffix}'/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/jquery/mobile/jqm.datebox.${jsSuffix}'/>"></script>
<script type="text/javascript" language="javascript" src="<c:url value='/resources/js/jp.${jsSuffix}'/>"></script>
<!-- 
<link href="../css/touch.v.1.w320.css?v=201207171156"  media="all and (min-width:320px) and (max-width:479px)" rel="stylesheet" />
-->
<!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
<script type="text/javascript">
<!--
$(document).ready(function(){
  $(window).bind('orientationchange',function(e){
	location.reload();
  });
});

function showLoading(messageTitle, elementId) {
  var loading;	
  if (jp.isEmpty(elementId)) {
	loading = new ol.loading({id:'content'});
  }	else {
	loading = new ol.loading({id:elementId});
  }
  loading.show();
  if (jp.isEmpty(messageTitle)) {
	messageTitle = "<fmt:message key='common.loading'/>";
  }
  $.mobile.loadingMessage = messageTitle;
  $.mobile.loadingMessageTextVisible = true;
  $.mobile.showPageLoadingMsg();
  return loading;
}

function hiddenLoading(loading) {
  loading.hide();   
  $.mobile.hidePageLoadingMsg();
}

function showAlert(message, funct) {
  if (funct==null) {
	funct = function () {}
  }
  $('<div>').simpledialog2({
	mode: 'button',
	headerClose: true,
	buttonPrompt: message,
	buttons : {
	  '<fmt:message key="button.close"/>': {
	    click: funct,
		theme: "c"
	  }
	}
  });
}

function confirm(message, funct) {
  if (funct==null) {
	funct = function () {};
  }
  $('<div>').simpledialog2({
	mode: 'button',
	headerClose: true,
	buttonPrompt: message,
	buttons : {
	  '<fmt:message key="button.confirm"/>': {
	    click: funct,
		theme: "c"
	  },
	  '<fmt:message key="button.cancel"/>' : {
  	    click: function () {},
  	    theme: "c", icon: "jp-cancel"
  	  }
	}
  });
}
	
function toPage(url) {
  window.location.href=url;
  showLoading();
}
//-->
</script>