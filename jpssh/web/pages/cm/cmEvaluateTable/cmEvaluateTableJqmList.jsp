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
	  <div class="jqm-list-title">Survey Table</div>
	</div>
	<div class="ui-bar ui-bar-c">
	  <form id="cmEvaluateTableForm" method="post">
	    <input type="hidden" name="menuId" value="${menuId}"/>
	    <input type="checkbox" id="checkHead" onclick="jp.checkAll(this,'evaluateTableId')" style="margin-top:8px;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
	      <a href="javascript:toPage('<c:url value='/cmEvaluateTableJqmAdd?menuId=${menuId}'/>')" data-role="button" data-icon="jp-add" data-mini="true" 
		     data-rel="dialog" rel="external" data-inline="true" class="lf"><fmt:message key="button.add"/></a>
		  <a href="javascript:batchDelete();" data-role="button" data-icon="jp-delete" data-mini="true"  rel="external" data-inline="true" class="lf">
	      	<fmt:message key="button.delete"/>
	      </a>
	      <a href="javascript:import();" data-role="button" data-icon="jp-import" data-mini="true"  rel="external" data-inline="true" class="lf">
	      	<fmt:message key="button.import"/>
	      </a>
	      <a href="javascript:export();" data-role="button" data-icon="jp-export" data-mini="true"  rel="external" data-inline="true" class="lf">
	      	<fmt:message key="button.export"/>
	      </a>
	  </form>
	</div>
	<!--evaluateTableId,tableCd,tableName,tableType,startDate,endDate,roleIds,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	<div class="withcheckbox">
	  <ul data-role="listview" data-filter="true" data-theme="d" data-split-icon="gear" data-split-theme="d">
		<c:forEach var="model" items="${cmEvaluateTables}">
		  <li><input type="checkbox" name="evaluateTableId" value="${model.evaluateTableId}"/>
		    <a href="javascript:toPage('<c:url value='/cmEvaluateTableJqmDetail/${model.evaluateTableId}?menuId=${menuId}'/>');">
		      <h3>${model.tableName}</h3><p></p></a>
			  <a href="javascript:popupDialog('${model.evaluateTableId}','${model.tableName}');" 
			 	data-rel="dialog" data-transition="flit"><fmt:message key="button.edit"/>
			</a>
		  </li>
		</c:forEach>
	  </ul>
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
function popupDialog(evaluateTableId,fieldName) {
  $('<div>').simpledialog2({
    mode: 'button',
    headerText: I18N.choose,
    headerClose: true,
    buttonPrompt: fieldName,
    buttons : {
      '<fmt:message key="button.edit"/>': {
        click: function () { 
        	window.location.href="<c:url value='/cmEvaluateTableJqmEdit/'/>" + evaluateTableId + "?menuId=${menuId}";;
        	showLoading();
        },
        icon: "jp-edit" , theme: "c"
      },
      '<fmt:message key="button.delete"/>': {
        click: function () { confirmDelete(evaluateTableId);},
        icon: "jp-delete", theme: "c"
      },
      '<fmt:message key="button.cancel"/>' : {
  	    click: function () {}, icon: "jp-cancel", theme: "c"
  	  }
    }
  });
}

function popupDetailDialog(evaluateTableId, tableName) {
  $("<div>").simpledialog2({
    mode:'blank',
    width:'300px',
    blankContent: 
    	"<div class='tc m10'><h3>" + "<fmt:message key='cmEvaluateTable.detail.title'/>" + "</h3></div>" + 
    	"<div class='p10'><fmt:message key='cmEvaluateTable.tableName'/> : <span class='underline'>" + tableName + "</span></div>" +
    	"<a rel='close' data-role='button' data-mini='true' data-inline='true' href='#' class='fr'" + 
    	   "data-theme='${jqmTheme}'><fmt:message key='button.close'/></a>" +
    	"<a rel='external' data-role='button' data-mini='true' data-inline='true' data-theme='${jqmTheme}'" + 
    	   "href='javascript:confirmDelete(&quot;" + evaluateTableId + "&quot;);' class='fr'><fmt:message key='button.delete'/></a>"+   
    	"<a rel='external' data-role='button' data-mini='true' data-inline='true' data-theme='${jqmTheme}'" + 
    	   "href='<c:url value='/cmEvaluateTableJqmEdit/'/>" + evaluateTableId + "' class='fr'><fmt:message key='button.edit'/></a>"
  });
};

function confirmDelete(evaluateTableId) {
  confirm(I18N.msg_del_confirm, function () {
  	window.location.href="<c:url value='/deleteCmEvaluateTable/'/>" + evaluateTableId + "?menuId=${menuId}";
  });
}

function batchDelete() {
  var checkValues = jp.getCheckValues("evaluateTableId");
  if (jp.isEmpty(checkValues)) {
	showAlert(I18N.msg_no_sel);
  } else {
	  confirm(I18N.msg_del_confirm, function () {
		  var formEl = document.getElementById("cmEvaluateTableForm");
		  if ($("#evaluateTableIds").val()) {
			$("#evaluateTableIds").val(checkValues);
		  } else {
			formEl.appendChild(jp.createHidden("evaluateTableIds", checkValues));
		  }
		  formEl.action = "<c:url value='/deleteCmEvaluateTables'/>";
		  formEl.submit();
		  showLoading();
	  });
  }
}

//-->
</script>
