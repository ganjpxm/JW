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
	<h1><fmt:message key="amUserDetail.add.title"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
	<form id="amUserDetailForm" action="<c:url value='/saveAmUserDetail'/>" method="post" data-ajax="false">
	  <input type="hidden" name="menuId" value="${menuId}"/>
	  <!--userDetailId,userId,homePhone,countryId,provinceId,cityId,birthPlace,livePlace,annualIncome,jobCategoryPid,jobCategoryId,jobPositionId,companyName,shoolName,educationId,interestIds,passwordTipCustom,passwordTipId,passwordTipAnswer,signature,remark,createDateTime,modifyTimestamp,dataState,-->
	  <div class="lable"><span class="red">*</span><fmt:message key="amUserDetail.fieldName1"/> : </div>
      <input id="fieldName1" name="fieldName1" class="validate[required]"/>
      
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
  $("#amUserDetailForm").validationEngine();
});

function save() {
  if (!$('#amUserDetailForm').validationEngine('validate')) {
	return false;
  }
  $("#amUserDetailForm").submit();
  showLoading(I18N.msg_saving);
}
//-->
</script>
