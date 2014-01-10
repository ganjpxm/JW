<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <%@ include file="/pages/common/inc/headjqm.jsp" %>
</head>
<body>
<div style="margin: 2px 20px;">
  <div id="header" data-role="header" data-theme="d">
	<h1><fmt:message key="bmConfig.add.title"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
	<form id="bmConfigForm" action="<c:url value='/saveBmConfig'/>" method="post" data-ajax="false" >
	  <input type="hidden" name="menuId" value="${menuId}"/>
	  <!--configId,configCd,configName,configValue,description,lang,createDateTime,modifyTimestamp,dataState,-->
	  <div class="jqm-form-lable"><fmt:message key="bmConfig.configCd"/> : </div>
      <input id="configCd" name="configCd" class="validate[required]"/>
      <div class="jqm-form-lable"><fmt:message key="bmConfig.configName"/> : </div>
      <input id="configName" name="configName" class="validate[required]"/>
      <div class="jqm-form-lable"><fmt:message key="bmConfig.configValue"/> : </div>
      <input id="configValue" name="configValue" class="validate[required]"/>
      <div class="jqm-form-lable"><fmt:message key="common.field.description"/> : </div>
      <textarea id="description" name="description" rows="10" cols="30"></textarea>
	  <div style="text-align:right">
		<a href="#" data-role="button" data-inline="true" data-rel="back" data-icon="jp-back" data-mini="false">
			<fmt:message key="button.back"/></a>
		<a href="javascript:save();" data-role="button" data-icon="jp-save" data-mini="false" rel="external" 
			data-inline="true" data-theme="${jqmTheme}"><fmt:message key="button.save"/></a>
	  </div>
	</form>
  </div>
</div>
</body>
</html>
<script type="text/javascript">
<!--
$(document).ready(function(){
  $("#bmConfigForm").validationEngine();
});

function save() {
  if (!$('#bmConfigForm').validationEngine('validate')) {
	return false;
  }
  $("#bmConfigForm").submit();
  showLoading(I18N.msg_saving);
}
//-->
</script>
