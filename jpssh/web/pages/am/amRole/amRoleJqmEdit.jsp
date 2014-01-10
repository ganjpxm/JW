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
	<h1><fmt:message key="amRole.edit.title"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
	<form id="amRoleForm" action="<c:url value='/updateAmRole'/>" method="post" data-ajax="false">
	  <input type="hidden" name="roleId" value="${amRole.roleId}"/>
	  <input type="hidden" name="menuId" value="${menuId}"/>
	  <!--roleId,roleCd,roleName,description,createDateTime,modifyTimestamp,dataState,-->
	  <div class="lable"><span class="red">*</span><fmt:message key="amRole.roleCd"/> : </div>
      <input id="roleCd" name="roleCd" class="validate[required]" value="${amRole.roleCd}"/>
      <div class="lable"><span class="red">*</span><fmt:message key="amRole.roleName"/> : </div>
      <input id="roleName" name="roleName" class="validate[required]" value="${amRole.roleName}"/>
      
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
  $("#amRoleForm").validationEngine();
});

function save() {
  if (!$('#amRoleForm').validationEngine('validate')) {
	return false;
  }
  $("#amRoleForm").submit();
  showLoading(I18N.msg_saving);
}
//-->
</script>
