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
	<h1><fmt:message key="cmEvaluateItem.edit.title"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
	<form id="cmEvaluateItemForm" action="<c:url value='/updateCmEvaluateItem'/>" method="post" data-ajax="false">
	  <input type="hidden" name="evaluateItemId" value="${cmEvaluateItem.evaluateItemId}"/>
	  <input type="hidden" name="menuId" value="${menuId}"/>
	  <!--evaluateItemId,evaluateTableId,itemName,itemPosition,itemValueType,itemValueTypeId,itemOptions,itemOrder,itemWidth,createDateTime,modifyTimestamp,dataState,-->
	  <div class="lable"><span class="red">*</span><fmt:message key="cmEvaluateItem.fieldName1"/> : </div>
      <input id="fieldName1" name="fieldName1" class="validate[required]" value="${cmEvaluateItem.fieldName1}" class="validate[required]"/>
	  <div style="text-align:right">
		<a href="#" data-role="button" data-inline="true" data-rel="back" data-icon="jp-back" data-mini="false">
			<fmt:message key="button.back"/></a>
		<a href="javascript:save();" data-role="button" data-icon="jp-save" data-mini="false" 
			rel="external" data-inline="true" data-theme="$ {jqmTheme}"> <fmt:message key="button.save"/></a>
	  </div>
	</form>
  </div>
</div>
</body>
</html>
<script type="text/javascript">
<!--
$(document).ready(function(){
  $("#cmEvaluateItemForm").validationEngine();
});

function save() {
  if (!$('#cmEvaluateItemForm').validationEngine('validate')) {
	return false;
  }
  $("#cmEvaluateItemForm").submit();
  showLoading(I18N.msg_saving);
}
//-->
</script>
