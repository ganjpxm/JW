<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <%@ include file="/pages/common/inc/headjqm.jsp" %>
  <style>
  	.ui-dialog-contain {width: 400px; max-width: 600px;}
  </style>
</head>
<body>
<div style="margin: 2px 20px;">
  <div id="header" data-role="header" data-theme="d">
	<h1><fmt:message key="amUser.information"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
	<form id="amUserForm" action="<c:url value='/updateAmLoginUser'/>" method="post" data-ajax="false">
	  <input type="hidden" name="userId" value="${amUser.userId}"/>
	  <input type="hidden" name="from" value="${from}"/>
	  
	  <!--userId,userCd,userName,userAlias,userLevel,userScore,loginTimes,password,mobilePhone,qq,email,birthday,photoUrl,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	  <div class="lable"><span class="red">*</span><fmt:message key="amUser.userCd"/> : </div>
      <input id="userCd" name="userCd" class="validate[required,maxSize[30]]" value="${amUser.userCd}"/>
	  <div class="lable"><span class="red">*</span><fmt:message key="amUser.userName"/> : </div>
      <input id="userName" name="userName" class="validate[required,maxSize[30]]" value="${amUser.userName}"/>
      <div class="lable"><span class="red">*</span><fmt:message key="amUser.email"/> : </div>
      <input id="email" name="email" class="validate[required,maxSize[60]]" value="${amUser.email}"/>
      <div class="lable"><span class="red">*</span><fmt:message key="amUser.mobilePhone"/> : </div>
      <input id="mobilePhone" name="mobilePhone" class="validate[required,maxSize[20]]" value="${amUser.mobilePhone}"/>
      <div class="lable"><span class="red">*</span><fmt:message key="amUser.password"/> : </div>
      <input id="password" name="password" class="validate[required,maxSize[20]]" value="${amUser.password}"/>
        
	  <div style="text-align:right"><!-- data-rel="back" -->
		<a href="<c:url value='${from}?profile=yes'/>" data-ajax="false" data-role="button" data-inline="true"  data-icon="jp-back" data-mini="false">
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
  $("#amUserForm").validationEngine();
});

function save() {
  if (!$('#amUserForm').validationEngine('validate')) {
	return false;
  }
  $("#amUserForm").submit();
  showLoading(I18N.msg_saving);
}
//-->
</script>
