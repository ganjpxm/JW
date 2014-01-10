<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="author" content="GanJianping"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <title id='Description'>SCeditor 1.4.2</title>
  <!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
		
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/thirdparty/SCEditor/minified/themes/default.min.css'/>"/>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-1.8.2.min.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/SCEditor/minified/jquery.sceditor.bbcode.min.${jsSuffix}'/>"></script>
</head>
<body class="jpw2">
<div id="body">
  <div class="content">
    <h1 style="text-align:center"><a href="http://www.sceditor.com" target="_blank">SCeditor</a></h1>
    <div>
      <textarea style="width:600px; height:300px" id="demo-bbcode">
      	[center][size=3][b]BBCode SCEditor[/b][/size][/center]Give it a try! :) [color=#ff00]Red text! [/color][color=#3399ff]Blue?[/color]
      	[ul][li]A simple list[/li][li]list item 2[/li][/ul] If you are using IE9+ or any non-IE browser just type [b]:[/b]) and it should be converted into :) as you type.
      </textarea>
    </div>
  </div>
</div>
</body>
</html>
<script>
<!--
$(function() {
	var initEditor = function() {
		$("#demo-bbcode").sceditor({
			plugins: 'bbcode',
			emoticonsRoot: "<c:url value='/resources/thirdparty/SCEditor/'/>",
			style: "<c:url value='/resources/thirdparty/SCEditor/minified/jquery.sceditor.default.min.css'/>"
		});
	};
	initEditor();
});

//-->
</script>
