<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<html>
<head>
	<title><fmt:message key="common.system.information"/></title>
	<%@ include file="/pages/common/inc/head.jsp" %>
</head>
<body>
<div class="main">
  <div class="barTitle1">
	<div class="content">system default value</div>
  </div>
  <div class="mt1">
	<p>System Theme : ${webConfig["csstheme"]}</p>
	<p>CSS Suffix : ${webConfig["csssuffix"]}</p>
	<p>Javascript Suffix : ${webConfig["jssuffix"]}</p>
	<p>I18N Language : ${webConfig["language"]}</p>
  </div>
  <div class="barTitle1">
	<div class="content">server information</div><p>
  </div>
  <div class="mt1">
	<p>Server Time : ${serverTime}</p>
	<p>System User Home : ${systemUserName}</p>
	<p>System User Dir : ${systemUserDir}</p>
	<p>Operate System Name : ${osName}</p>
	<p>Operate System Version : ${osVersion}</p>
  </div>
  <div class="barTitle1">
	<div class="content">java information</div><p>
  </div>
  <div class="mt1">
  	<p>Java Version : ${javaVersion}</p>
  	<p>Java Vendor : ${javaVendor}</p>
  	<p>Java Home : ${javaHome}</p>
  	<p>Java Input Output Temporary Directory : ${javaIoTmpDir}</p>
  </div>
</div>	
</body>
</html>
<script type="text/javascript">
<!--

//-->
</script>