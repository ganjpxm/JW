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
	<h1><fmt:message key="cmCategory.edit.title"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
	<form id="cmCategoryForm" action="<c:url value='/updateCmCategory'/>" method="post" data-ajax="false">
	  <input type="hidden" name="categoryId" value="${cmCategory.categoryId}"/>
	  <input type="hidden" name="menuId" value="${menuId}"/>
	  <!--categoryId,categoryCd,categoryPid,categoryName,endFlag,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	  <div class="lable"><span class="red">*</span><fmt:message key="cmCategory.categoryCd"/> : </div>
      <input id="categoryCd" name="categoryCd" class="validate[required]" value="${cmCategory.categoryCd}"/>
      <div class="lable"><span class="red">*</span><fmt:message key="cmCategory.categoryName"/> : </div>
      <input id="categoryName" name="categoryName" class="validate[required]" value="${cmCategory.categoryName}"/>
      <div class="lable"><span class="red">*</span><fmt:message key="common.field.displayNo"/> : </div>
      <input id="displayNo" name="displayNo" class="validate[required]" value="${cmCategory.displayNo}"/>
      <span class="red">*</span><fmt:message key="common.field.end.flag"/>:</label>
	  <select name="endFlag" id="endFlag" data-role="slider">
		<option value="0" <c:if test="${endFlag=='0'}"> selected </c:if>><fmt:message key="common.value.no"/></option>
		<option value="1" <c:if test="${endFlag=='1'}"> selected </c:if>><fmt:message key="common.value.yes"/></option>
	  </select>
      
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
  $("#cmCategoryForm").validationEngine();
});

function save() {
  if (!$('#cmCategoryForm').validationEngine('validate')) {
	return false;
  }
  $("#cmCategoryForm").submit();
  showLoading(I18N.msg_saving);
}
//-->
</script>
