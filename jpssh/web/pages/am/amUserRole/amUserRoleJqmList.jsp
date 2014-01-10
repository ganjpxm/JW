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
	  <div class="jqm-list-title"><fmt:message key="amUserRole.list.title"/></div>
	</div>
	<div class="ui-bar ui-bar-c">
	  <form id="amUserRoleForm" method="post">
	    <input type="hidden" name="menuId" value="${menuId}"/>
	    <input type="checkbox" id="checkHead" onclick="jp.checkAll(this,'userRoleId')" style="margin-top:8px;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
	      <a href="javascript:toPage('<c:url value='/amUserRoleJqmAdd?menuId=${menuId}'/>')" data-role="button" data-icon="jp-add" data-mini="true" 
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
	<!--userRoleId,roleId,userId,createDateTime,modifyTimestamp,dataState,-->
	<div class="withcheckbox">
	  <ul data-role="listview" data-filter="true" data-theme="d" data-split-icon="gear" data-split-theme="d">
		<c:forEach var="model" items="${amUserRoles}">
		  <li><input type="checkbox" name="userRoleId" value="${model.userRoleId}"/>
		    <a href="javascript:popupDetailDialog('${model.userRoleId}','${model.filedName}')">
		      <h3>${model.filedName}</h3><p></p></a>
			  <a href="javascript:popupDialog('${model.userRoleId}','${model.filedName}');" 
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
function popupDialog(userRoleId,fieldName) {
  $('<div>').simpledialog2({
    mode: 'button',
    headerText: I18N.choose,
    headerClose: true,
    buttonPrompt: fieldName,
    buttons : {
      '<fmt:message key="button.edit"/>': {
        click: function () { 
        	window.location.href="<c:url value='/amUserRoleJqmEdit/'/>" + userRoleId + "?menuId=${menuId}";;
        	showLoading();
        },
        icon: "jp-edit" , theme: "c"
      },
      '<fmt:message key="button.delete"/>': {
        click: function () { confirmDelete(userRoleId);},
        icon: "jp-delete", theme: "c"
      },
      '<fmt:message key="button.cancel"/>' : {
  	    click: function () {}, icon: "jp-cancel", theme: "c"
  	  }
    }
  });
}

function popupDetailDialog(userRoleId, filedName) {
  $("<div>").simpledialog2({
    mode:'blank',
    width:'300px',
    blankContent: 
    	"<div class='tc m10'><h3>" + "<fmt:message key='amUserRole.detail.title'/>" + "</h3></div>" + 
    	"<div class='p10'><fmt:message key='amUserRole.filedName'/> : <span class='underline'>" + filedName + "</span></div>" +
    	"<a rel='close' data-role='button' data-mini='true' data-inline='true' href='#' class='fr'" + 
    	   "data-theme='${jqmTheme}'><fmt:message key='button.close'/></a>" +
    	"<a rel='external' data-role='button' data-mini='true' data-inline='true' data-theme='${jqmTheme}'" + 
    	   "href='javascript:confirmDelete(&quot;" + userRoleId + "&quot;);' class='fr'><fmt:message key='button.delete'/></a>"+   
    	"<a rel='external' data-role='button' data-mini='true' data-inline='true' data-theme='${jqmTheme}'" + 
    	   "href='<c:url value='/amUserRoleJqmEdit/'/>" + userRoleId + "' class='fr'><fmt:message key='button.edit'/></a>"
  });
};

function confirmDelete(userRoleId) {
  confirm(I18N.msg_del_confirm, function () {
  	window.location.href="<c:url value='/deleteAmUserRole/'/>" + userRoleId + "?menuId=${menuId}";
  });
}

function batchDelete() {
  var checkValues = jp.getCheckValues("userRoleId");
  if (jp.isEmpty(checkValues)) {
	showAlert(I18N.msg_no_sel);
  } else {
	  confirm(I18N.msg_del_confirm, function () {
		  var formEl = document.getElementById("amUserRoleForm");
		  if ($("#userRoleIds").val()) {
			$("#userRoleIds").val(checkValues);
		  } else {
			formEl.appendChild(jp.createHidden("userRoleIds", checkValues));
		  }
		  formEl.action = "<c:url value='/deleteAmUserRoles'/>";
		  formEl.submit();
		  showLoading();
	  });
  }
}

//-->
</script>
