<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="author" content="GanJianping"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <title id='Description'>Magnific Popup<</title>
  
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/thirdparty/MagnificPopup/magnific-popup.css'/>"/>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-1.9.1.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/MagnificPopup/jquery.magnific-popup.${jsSuffix}'/>"></script>
  <style type="text/css">
	.white-popup {
	  position: relative;
	  background: #FFF;
	  padding: 40px;
	  width: auto;
	  max-width: 200px;
	  margin: 20px auto;
	  text-align: center;
	}
  </style>
</head>
<body class="jpw2">
<h1 style="text-align:center"><a href="http://dimsemenov.com/plugins/magnific-popup/">Magnific Popup</a></h1>
<div id="container" class="clearfix masonry" style="width:99%">
  <div class="parent-container">
    <a href="<c:url value='/resources/upload/photo/Taylor-swift-01.jpg'/>"><img alt="" src="<c:url value='/resources/upload/photo/Taylor-swift-01.jpg'/>" style="height:200px;"></a>
    <a href="<c:url value='/resources/upload/photo/Taylor-Swift-02.jpg'/>"><img alt="" src="<c:url value='/resources/upload/photo/Taylor-Swift-02.jpg'/>" style="height:200px;"></a>
    <a href="<c:url value='/resources/upload/photo/Taylor-Swift-03.jpg'/>"><img alt="" src="<c:url value='/resources/upload/photo/Taylor-Swift-03.jpg'/>" style="height:200px;"></a>
  </div>
</div>
</body>
</html>
<script>
<!--

$(function() {
  $('.parent-container').magnificPopup({
    delegate: 'a', // child items selector, by clicking on it popup will open
    type: 'image',
    gallery: {
      enabled: true
    }
  });
});

//-->
</script>
