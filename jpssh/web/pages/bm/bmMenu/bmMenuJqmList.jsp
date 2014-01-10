<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<%@ include file="/pages/common/inc/headjqm.jsp" %>
</head>
<body>
<%@ include file="/pages/common/inc/headerjqm.jsp" %>
<div id="content" data-role="content">
  <div class="jpw-content">
	<div class="ui-bar ui-bar-d">
	  <div class="jqm-list-title"><fmt:message key="bmMenu.list.title"/></div>
	</div>
	${menuHtml}
  </div>
  <div class="jpw-nav-vertical-menu">
	${leftMenuHtml}    
  </div>
</div>
<%@ include file="/pages/common/inc/footerjqm.jsp" %>
</body>
</html>
<script type="text/javascript">
<!--
function confirmDelete(menuId) {
  confirm(I18N.msg_del_confirm, function () {
	  window.location.href="<c:url value='/deleteBmMenu/'/>" + menuId + "?menuId=${menuId}";
  });
}

function edit(menuId) {
  window.location.href = "<c:url value='/bmMenuJqmEdit/'/>" + menuId + "?menuId=${menuId}";
  showLoading();
}

function add(menuPid, menuCategoryId, displayLevel, menuCd) {
  var url = "<c:url value='/bmMenuJqmAdd?menuCategoryId='/>" + menuCategoryId + "&displayLevel=" + displayLevel +
		    "&menuCd=" + menuCd + "&menuId=${menuId}";
  if (!jp.isEmpty(menuPid)) {
	  url += "&menuPid=" + menuPid;
  }		    
  window.location.href=url;
  showLoading();
}
//-->
</script>
