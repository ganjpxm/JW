<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>Lesson Plan Template</title>
	<%@ include file="/pages/common/inc/headjqm.jsp" %>
	<style type="text/css">
	  
  	</style>
</head>
<body>
<head>
<body>
<%@ include file="/pages/common/inc/headerjqm.jsp" %>

<div id="content" class="content">
  <div id="contentTitle" class="ui-bar ui-bar-c contentTitle">
  	<div class="dp90 title">Online Survey Table</div>
  	<div class="dp10 titleBtn">
	<a href="<c:url value='/smEvaluateTableAddDialog/${activeMenuId}/20'/>" data-role="button" data-icon="add" data-iconpos="notext" 
	   data-mini="true" data-rel="dialog" rel="external" style="float:right;"><fmt:message key="button.add"/></a></div>
  </div>
  <div id="listview" class="listview">
    <!--evaluateTableId,evaluateTableCd,tableName,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	<ul data-role="listview" data-filter="true" data-theme="d" data-split-icon="gear" data-split-theme="d">
	  <c:forEach var="map" items="${evaluateTables}">
		 <li><a href="<c:url value='/smEvaluateTableDetailDialog/${activeMenuId}/${map.evaluateTableId}?tableType=${map.tableType}'/>" rel="external">
		 <h3>${map.tableName}</h3>
		 <p>Creator: ${map.operatorName}; Create Date: ${map.createDateTime}</p>
		 </a>
		 <a id="${map.evaluateTableId}Edit" href="javascript:popupDialog('${map.evaluateTableId}Edit','${map.evaluateTableId}','${map.tableType}');" 
		 	data-rel="dialog" data-transition="flit"><fmt:message key="button.edit"/></a></li>
	  </c:forEach>
	</ul>
  </div>
</div>
<%@ include file="/pages/common/inc/footerjqm.jsp" %>
</body>
</html>
<script type="text/javascript">
<!--
$(function() {
  //$("#listview").height($("#listview").height()-40);
});
function popupDialog(id,evaluateTableId,tableType) {
  $("#"+id).simpledialog({
	'mode' : 'bool', 
	'prompt' : 'Choice of Operation',
	'useModal': true,
	'buttons' : {
	  '<fmt:message key="button.edit"/>' : {
		click: function () { window.location.href="<c:url value='/smEvaluateTableEditDialog/${activeMenuId}/'/>" + evaluateTableId; },
		icon: "coss-edit",
		theme: "c"
	  },
	  '<fmt:message key="button.delete"/>' : {
	    click: function () { window.location.href="<c:url value='/deleteSmEvaluateTable/${activeMenuId}/lessonPlan/'/>" + evaluateTableId; },
	    icon: "coss-delete",
	    theme: "c"
	  },
	  '<fmt:message key="button.cancel"/>' : {
	    click: function () {},
	    icon: "coss-cancel",
	    theme: "c"
	  }
	}
  });
}

function popupDetailDialog(id,tableName,itemNamesHtml) {
  $("#"+id).simpledialog({
    'mode' : 'blank',
    'prompt': false,
    'forceInput': false,
    'useModal':true,
    'fullHTML' : 
        "<ul data-role='listview'>" +
  			"<li style='font-size:20px;'>"+ tableName + "</li>" +
  			"<li style='font-size:20px;font-weight:normal;'>" + itemNamesHtml + "</li>" +
  		"</ul>" +
  		"<a rel='close' data-role='button' href='#' id='simpleclose'>Close</a>"
  });
}   
//-->
</script>
