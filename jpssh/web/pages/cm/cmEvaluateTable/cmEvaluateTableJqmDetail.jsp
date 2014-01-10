<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <%@ include file="/pages/common/inc/headjqm.jsp" %>
  <style type="text/css">
  	.ui-dialog-contain {margin:5% auto 15px; width:800px; max-width:800px;}
  </style>
</head>
<body>
<div data-role="dialog">
  <div id="header" data-role="header" data-theme="d">
	<h1>${cmEvaluateTable.tableName}</h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
	  <!--articleId,articleCd,title,content,author,imageUrl,originUrl,displayNo,lang,roleIds,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
      ${evaluateItemHtml}
      
	  <div style="text-align:right">
		<a href="javascript:toPage('<c:url value='/cmEvaluateTable?menuId=${menuId}'/>');" data-role="button" data-inline="true" rel="external"><fmt:message key="button.back"/></a>
		<a href="javascript:edit('${cmEvaluateTable.evaluateTableId}')" data-role="button" data-inline="true" 
			rel="external" data-theme="${jqmTheme}"><fmt:message key="button.edit"/></a>
	  </div>
  </div>
</div>
</body>
</html>
<script type="text/javascript">
<!--
function edit(evaluateTableId) {
  window.location.href="<c:url value='/cmEvaluateTableJqmEdit/'/>" + evaluateTableId + "?menuId=${menuId}";
  showLoading();
}
//-->
</script>
