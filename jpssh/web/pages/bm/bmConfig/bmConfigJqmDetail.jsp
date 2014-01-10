<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <%@ include file="/pages/common/inc/headjqm.jsp" %>
</head>
<body>
<div data-role="dialog">
  <div id="header" data-role="header" data-theme="d">
	<h1><fmt:message key="bmConfig.detail.title"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
	  <!--configId,configCd,configName,configValue,description,lang,createDateTime,modifyTimestamp,dataState,-->
	  <div class="jqm-form-lable"><fmt:message key="bmConfig.configCd"/> : <span class="underline">${bmConfig.configCd}</span></div>
      <div class="jqm-form-lable"><fmt:message key="bmConfig.configName"/> : <span class="underline">${bmConfig.configName}</span></div>
      <div class="jqm-form-lable"><fmt:message key="bmConfig.configValue"/> : <span class="underline">${bmConfig.configValue}</span></div>
      <div class="jqm-form-lable"><fmt:message key="common.field.description"/> : </div>
      <div class="jqm-form-lable"><span class="underline">${bmConfig.description}</span></div>
	  <div style="text-align:right">
		<a href="#" data-role="button" data-inline="true" data-rel="back"><fmt:message key="button.back"/></a>
		<a href="javascript:toPage('<c:url value='/bmConfigJqmEdit/${bmConfig.configId}'/>')" data-role="button" data-inline="true" 
			rel="external" data-theme="${jqmTheme}"><fmt:message key="button.edit"/></a>
	  </div>
  </div>
</div>
</body>
</html>
<script type="text/javascript">
<!--

//-->
</script>
