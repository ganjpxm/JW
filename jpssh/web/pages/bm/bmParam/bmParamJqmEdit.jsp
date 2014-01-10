<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <title><fmt:message key="bmParam.edit.title"/></title>
  <%@ include file="/pages/common/inc/headjqm.jsp" %>
</head>
<body>
<div style="margin: 2px 20px;">
  <div data-role="header" data-theme="d">
	<h1><fmt:message key="bmParam.edit.title"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
	<form id="bmParamForm" action="<c:url value='/updateBmParam'/>" method="post" data-ajax="false">
	  <input type="hidden" name="paramId" value="${bmParam.paramId}"/>
	  <input type="hidden" name="menuId" value="${menuId}"/>
	  <!--paramId,paramCd,paramName,paramTypeCd,paramTypeName,displayNo,lang,createDateTime,modifyTimestamp,dataState,-->
	  <div class="lable"><span class="red">*</span><fmt:message key="bmParam.paramCd"/> : </div>
      <input id="paramCd" name="paramCd" class="validate[required]" value="${bmParam.paramCd}"/>
      <div class="lable"><span class="red">*</span><fmt:message key="bmParam.paramName"/> : </div>
      <input id="paramName" name="paramName" class="validate[required]" value="${bmParam.paramName}"/>
      <div class="lable"><span class="red">*</span><fmt:message key="bmParam.paramTypeCd"/> : </div>
      <input id="paramTypeCd" name="paramTypeCd" class="validate[required]" value="${bmParam.paramTypeCd}"/>
      <div class="lable"><span class="red">*</span><fmt:message key="bmParam.paramTypeName"/> : </div>
      <input id="paramTypeName" name="paramTypeName" class="validate[required]" value="${bmParam.paramTypeName}"/>
      <div class="lable"><span class="red">*</span><fmt:message key="common.field.displayNo"/> : </div>
      <input id="displayNo" name="displayNo" class="validate[required]" value="${bmParam.displayNo}"/>
      
	  <div style="text-align:right">
		<a href="#" data-role="button" data-inline="true" data-rel="back" data-icon="jp-back" data-mini="false">
			<fmt:message key="button.back"/></a>
		<a href="javascript:save();" data-role="button" data-icon="jp-save" data-mini="false" 
			rel="external" data-inline="true" data-theme="${jqmTheme}"> <fmt:message key="button.save"/></a>
	  </div>
	</form>
  </div>
</div>
</body>
</html>
<script type="text/javascript">
<!--
$(document).ready(function(){
  $("#bmParamForm").validationEngine();
});

function save() {
  if (!$('#bmParamForm').validationEngine('validate')) {
	return false;
  }
  $("#bmParamForm").submit();
  showLoading(I18N.msg_saving);
}
//-->
</script>
