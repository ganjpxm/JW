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
  <div class="jpw-content" style="margin-bottom:30px;">
	<div class="ui-bar ui-bar-d">
	  <div class="jqm-list-left_title"><fmt:message key="bmParam.list.title"/>
	  <a href="javascript:toPage('<c:url value='/bmParamJqmAdd?menuId=${menuId}'/>')" data-role="button" data-icon="add" data-mini="true" 
		     data-rel="dialog" rel="external" data-inline="true" data-iconpos="notext" style="float:right;"><fmt:message key="button.add"/></a>
	  </div>
	</div>
	
	<!--paramId,paramCd,paramName,paramTypeCd,paramTypeName,displayNo,lang,createDateTime,modifyTimestamp,dataState,-->
	<form id="bmParamForm" action="<c:url value='/bmParamJqmAdd'/>" method="post" data-ajax="false">
	   <input type="hidden" name="menuId" value="${menuId}"/>
	   <input type="hidden" id="paramTypeCd" name="paramTypeCd"/>
	   <input type="hidden" id="paramTypeName" name="paramTypeName"/>
	</form>
	<ul data-role="listview" data-filter="true" data-theme="d">
	  <c:forEach var="entry" items="${paramTypeAndBmParamss}" >
		<li>
		  <c:set var="paramTypeCdName" value="${fn:split(entry.key, ',')}" />	
		  <h1><div class="dp90">${paramTypeCdName[1]}(${paramTypeCdName[0]})</div>
		      <div class="dp10">
		      <a href="javascript:add('${paramTypeCdName[0]}','${paramTypeCdName[1]}');" data-role="button" 
		  	     data-icon="add" data-iconpos="notext" style="float:right">add</a></div></h1>
		  <div data-role="controlgroup" data-type="horizontal" >
		    <c:forEach var="bmParam" items="${entry.value}">
		  	  <a id="${bmParam.paramId}" href="javascript:popupDialog('${bmParam.paramId}');" 
		  	     data-role="button">${bmParam.paramName}</a>
			</c:forEach>
		  </div>
		</li>
	  </c:forEach>
	</ul>
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
function popupDialog(paramId,fieldName) {
  $('<div>').simpledialog2({
    mode: 'button',
    headerText: I18N.choose,
    headerClose: true,
    buttonPrompt: fieldName,
    buttons : {
      '<fmt:message key="button.edit"/>': {
        click: function () { 
        	window.location.href="<c:url value='/bmParamJqmEdit/'/>" + paramId + "?menuId=${menuId}";
        	showLoading();
        },
        icon: "jp-edit" , theme: "c"
      },
      '<fmt:message key="button.delete"/>': {
        click: function () { confirmDelete(paramId);},
        icon: "jp-delete", theme: "c"
      },
      '<fmt:message key="button.cancel"/>' : {
  	    click: function () {}, icon: "jp-cancel", theme: "c"
  	  }
    }
  });
}

function popupDetailDialog(paramId, filedName) {
  $("<div>").simpledialog2({
    mode:'blank',
    width:'300px',
    blankContent: 
    	"<div class='tc m10'><h3>" + "<fmt:message key='bmParam.detail.title'/>" + "</h3></div>" + 
    	"<div class='p10'><fmt:message key='bmParam.filedName'/> : <span class='underline'>" + filedName + "</span></div>" +
    	"<a rel='close' data-role='button' data-mini='true' data-inline='true' href='#' class='fr'" + 
    	   "data-theme='${jqmTheme}'><fmt:message key='button.close'/></a>" +
    	"<a rel='external' data-role='button' data-mini='true' data-inline='true' data-theme='${jqmTheme}'" + 
    	   "href='javascript:confirmDelete(&quot;" + paramId + "&quot;);' class='fr'><fmt:message key='button.delete'/></a>"+   
    	"<a rel='external' data-role='button' data-mini='true' data-inline='true' data-theme='${jqmTheme}'" + 
    	   "href='<c:url value='/bmParamJqmEdit/'/>" + paramId + "' class='fr'><fmt:message key='button.edit'/></a>"
  });
};

function confirmDelete(paramId) {
  confirm(I18N.msg_del_confirm, function () {
	  window.location.href="<c:url value='/deleteBmParam/'/>" + paramId + "?menuId=${menuId}";
  });
}

function batchDelete() {
  var checkValues = jp.getCheckValues("paramId");
  if (jp.isEmpty(checkValues)) {
	showAlert(I18N.msg_no_sel);
  } else {
	  confirm(I18N.msg_del_confirm, function () {
		  var formEl = document.getElementById("bmParamForm");
		  if ($("#paramIds").val()) {
			$("#paramIds").val(checkValues);
		  } else {
			formEl.appendChild(jp.createHidden("paramIds", checkValues));
		  }
		  formEl.action = "<c:url value='/deleteBmParams'/>";
		  formEl.submit();
		  showLoading();
	  });
  }
}

function add(paramTypeCd,paramTypeName) {
  $("#paramTypeCd").val(paramTypeCd);
  $("#paramTypeName").val(paramTypeName);
  $("#bmParamForm").submit();
  showLoading();	
}
//-->
</script>
