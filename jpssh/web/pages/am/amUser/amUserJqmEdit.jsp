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
	<h1><fmt:message key="amUser.edit.title"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
	<form id="amUserForm" action="<c:url value='/updateAmUser'/>" method="post" data-ajax="false">
	  <input type="hidden" name="userId" value="${amUser.userId}"/>
	  <input type="hidden" name="menuId" value="${menuId}"/>
	  <input type="hidden" id="roleIds" name="roleIds" value="${amUser.roleIds}"/>
	  <!--userId,userCd,userName,userAlias,userLevel,userScore,loginTimes,password,mobilePhone,qq,email,birthday,photoUrl,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	  <div class="lable"><span class="red">*</span><fmt:message key="amUser.userCd"/> : </div>
      <input id="userCd" name="userCd" class="validate[required]" value="${amUser.userCd}"/>
	  <div class="lable"><span class="red">*</span><fmt:message key="amUser.userName"/> : </div>
      <input id="userName" name="userName" class="validate[required]" value="${amUser.userName}"/>
      <div class="lable"><fmt:message key="amUser.mobilePhone"/> : </div>
      <input id="mobilePhone" name="mobilePhone" value="${amUser.mobilePhone}"/>
      <div class="lable"><span class="red">*</span><fmt:message key="amUser.email"/> : </div>
      <input id="email" name="email" class="validate[required]" value="${amUser.email}"/>
      <div class="lable"><span class="red">*</span><fmt:message key="amUser.password"/> : </div>
      <input id="password" name="password" class="validate[required]" value="${amUser.password}"/>
      <jp:jqmCheck name="roleId" checkType="checkbox" exprData="${roleMaps}" inputClass="validate[required]" 
      	checkedIds="${amUser.roleIds}" lableValue="amRole.roleName"/>  
      
      <div class="lable"><fmt:message key="amUser.userAlias"/> : </div>
      <input id="userAlias" name="userAlias" value="${amUser.userAlias}"/>
      Note:departmentLeader
        
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
  $("#amUserForm").validationEngine();
});

function save() {
  if (!$('#amUserForm').validationEngine('validate')) {
	return false;
  }
  $("#roleIds").val(jp.getCheckValues("roleId"));
  $("#amUserForm").submit();
  showLoading(I18N.msg_saving);
}
//-->
</script>
