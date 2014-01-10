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
	<div class="ui-bar ui-bar-c">
	  <div class="jqm-list-left_title"><fmt:message key="cmCategory.list.title"/>
	  <a href="javascript:toPage('<c:url value='/cmCategoryJqmAdd?menuId=${menuId}'/>')" data-role="button" data-iconpos="notext" 
	  	data-icon="add" data-rel="dialog" rel="external" data-inline="true" style="float:right;"><fmt:message key="button.add"/></a>
	  </div>
	</div>
	<div class="withcheckbox">
		${categoryHtml}  
	</div>
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
function confirmDelete(categoryId) {
  confirm(I18N.msg_del_confirm, function () {
	  window.location.href="<c:url value='/deleteCmCategory/'/>" + categoryId + "?menuId=${menuId}";
  });
}

function edit(categoryId) {
  window.location.href = "<c:url value='/cmCategoryJqmEdit/'/>" + categoryId + "?menuId=${menuId}";
  showLoading();
}

function add(categoryPid, categoryCd) {
  var url = "<c:url value='/cmCategoryJqmAdd?categoryPid='/>" + categoryPid + "&categoryCd=" + categoryCd +
		    "&menuId=${menuId}";
  window.location.href=url;
  showLoading();
}
//-->
</script>
