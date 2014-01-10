<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="author" content="GanJianping"/>
  <meta http-equiv=“X-UA-Compatible” content=“IE=8”> 
  <title id='Description'><fmt:message key='project.name'/></title>
  <link rel="shortcut icon" type="image/png" href="<c:url value='/resources/style/${jpTheme}/image/jp/icon/jp_icon.png'/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jpw.jq.${cssSuffix}'/>">
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jquery.ui.${cssSuffix}'/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/thirdparty/jqwidgets/styles/jqx.base.${cssSuffix}'/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/default/thirdparty/bootstrap.css'/>" />
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jpw.${cssSuffix}'/>"/>
  <!--[if IE]><link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/default/ie.${cssSuffix}'/>"><![endif]-->
  
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-1.8.2.min.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/ui/jquery.ui.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.cookie.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.nbspSlider.${jsSuffix}'/>"></script>
  <script type="text/JavaScript" src="<c:url value='/resources/js/jquery/ui/jquery.ui.touch-punch.min.${jsSuffix}'/>"></script>
  <script type="text/JavaScript" src="<c:url value='/resources/js/jquery/ui/jquery.shapeshift.min.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.form.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/i18n/i18n_${lang}.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.iframe.transport.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.fileupload.${jsSuffix}'/>"></script>
  
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxcore.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxinput.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxradiobutton.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxbuttons.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxdropdownbutton.${jsSuffix}'/>"></script>
  
  <script type="text/javascript" src="<c:url value='/resources/js/jp.${jsSuffix}'/>"></script>
  <style type="text/css">
  </style>
</head>
<body class="jpw2">
<%@ include file="/pages/common/vtwo/inc/header.jsp" %>
<div id="body">
  <div id="demo" class="content">
    <div style="padding:10px;">
      <button type="button" onclick="changeFrameUrl('<c:url value='/vtwo/SCEditor'/>');" class="css3Greenbutton">SCEditor</button>
      <button type="button" onclick="changeFrameUrl('<c:url value='/vtwo/masonry'/>');" class="css3Greenbutton">Masonry</button>
      <button type="button" onclick="changeFrameUrl('<c:url value='/vtwo/magnificPopup'/>');" class="css3Greenbutton">Magnific Popup</button>
      <button type="button" onclick="toNewWindow();" class="css3Greenbutton">New</button>
    </div>
    <div>
   	  <iframe id="demoFrame" src="" width="100%" frameborder="0"></iframe>
    </div>
  </div>
</div>
<div class="clearfix"></div>
<%@ include file="/pages/common/vtwo/inc/footer.jsp" %>
</body>
</html>
<script>
<!--

function changeFrameUrl(url) {
  $("#demoFrame").attr("src", url);
}

function toNewWindow() {
  window.open($("#demoFrame").attr("src"), "_blank");
}

$(document).ready(function () {
  var frameHeight = $(window).height()-120;
  if (frameHeight<300) {
	  frameHeight = $(window).height();
  }
  $("#demoFrame").attr("height", frameHeight);
});
//-->
</script>
