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
	<div class="ui-bar ui-bar-c">
	  <div class="jqm-list-title"><fmt:message key="bmConfig.list.title"/></div>
	</div>
	<div class="ui-bar ui-bar-c">
	  <form id="bmConfigForm" method="post">
	    <input type="hidden" name="menuId" value="${menuId}"/>
	    <input type="checkbox" id="checkHead" onclick="jp.checkAll(this,'configId')" style="margin-top:8px;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
	      <a href="javascript:toPage('<c:url value='/bmConfigJqmAdd?menuId=${menuId}'/>')" data-role="button" data-icon="jp-add" data-mini="true" 
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
	  <!--configId,configCd,configName,configValue,description,lang,createDateTime,modifyTimestamp,dataState,-->
	<div class="withcheckbox">
	  <ul data-role="listview" data-filter="true" data-theme="d" data-split-icon="gear" data-split-theme="d">
		<c:forEach var="model" items="${bmConfigs}">
		  <li><input type="checkbox" name="configId" value="${model.configId}"/>
		    <a href="javascript:popupDetailDialog('${model.configId}','${model.configCd}','${model.configName}','${model.configValue}','${model.description}')">
		      <h3>${model.configName}</h3><p>${model.configValue}</p></a>
			  <a href="javascript:popupDialog('${model.configId}','${model.configName}');" 
			 	data-rel="dialog" data-transition="flit"><fmt:message key="button.edit"/>
			</a>
		  </li>
		</c:forEach>
	  </ul>
	  </div>
  </div>
  <div class="jpw-nav-vertical-menu">
    ${leftMenuHtml}
  	<!--  
    <div data-role="collapsible" data-collapsed="true" data-theme="${jqmTheme}" data-content-theme="d">
	    <h3><fmt:message key="common.theme"/></h3>
	    <ul data-role="listview" data-theme="c" data-dividertheme="d">
	      <li data-role="list-divider"></li>
	      <li><a href="javascript:changeJqmTheme('a')"><fmt:message key="common.theme.black"/></a></li>
		  <li><a href="javascript:changeJqmTheme('b')"><fmt:message key="common.theme.blue"/></a></li>
		  <li><a href="javascript:changeJqmTheme('c')"><fmt:message key="common.theme.slive"/></a></li>
		  <li><a href="javascript:changeJqmTheme('d')"><fmt:message key="common.theme.gray"/></a></li>
		  <li><a href="javascript:changeJqmTheme('e')"><fmt:message key="common.theme.yellow"/></a></li>
		  <li><a href="javascript:changeJqmTheme('f')"><fmt:message key="common.theme.green"/></a></li>
		</ul>
	</div>
	-->
  </div>
</div>
<%@ include file="/pages/common/inc/footerjqm.jsp" %>
</body>
</html>
<script type="text/javascript">
<!--
function popupDialog(configId,configName) {
  $('<div>').simpledialog2({
    mode: 'button',
    headerText: I18N.choose,
    headerClose: true,
    buttonPrompt: configName,
    buttons : {
      '<fmt:message key="button.edit"/>': {
        click: function () { 
        	window.location.href="<c:url value='/bmConfigJqmEdit/'/>" + configId + "?menuId=${menuId}";
        	showLoading();
        },
        icon: "jp-edit" , theme: "c"
      },
      '<fmt:message key="button.delete"/>': {
        click: function () { confirmDelete(configId);},
        icon: "jp-delete", theme: "c"
      },
      '<fmt:message key="button.cancel"/>' : {
  	    click: function () {}, icon: "jp-cancel", theme: "c"
  	  }
    }
  });
}

function popupDetailDialog(configId, configCd, configName, configValue, description) {
  $("<div>").simpledialog2({
    mode:'blank',
    width:'300px',
    blankContent: 
    	"<div class='tc m10'><h3>" + "<fmt:message key='bmConfig.detail.title'/>" + "</h3></div>" + 
    	"<div class='p10'><fmt:message key='bmConfig.configCd'/> : <span class='underline'>" + configCd + "</span></div>" +
    	"<div class='p10'><fmt:message key='bmConfig.configName'/> : <span class='underline'>" + configName + "</span></div>" +
    	"<div class='p10'><fmt:message key='bmConfig.configValue'/> : <span class='underline'>" + configValue + "</span></div>" +
    	"<div class='p10'><fmt:message key='common.field.description'/> : <span class='underline'>" + description + "</span></div>" +
    	"<a rel='close' data-role='button' data-mini='true' data-inline='true' href='#' class='fr'" + 
    	   "data-theme='${jqmTheme}'><fmt:message key='button.close'/></a>" +
    	"<a rel='external' data-role='button' data-mini='true' data-inline='true' data-theme='${jqmTheme}'" + 
    	   "href='javascript:confirmDelete(&quot;" + configId + "&quot;);' class='fr'><fmt:message key='button.delete'/></a>"+   
    	"<a rel='external' data-role='button' data-mini='true' data-inline='true' data-theme='${jqmTheme}'" + 
    	   "href='<c:url value='/bmConfigJqmEdit/'/>" + configId + "' class='fr'><fmt:message key='button.edit'/></a>"
  });
};

function confirmDelete(configId) {
  confirm(I18N.msg_del_confirm, function () {
	  window.location.href="<c:url value='/deleteBmConfig/'/>" + configId + "?menuId=${menuId}";
  });
}

function batchDelete() {
  var checkValues = jp.getCheckValues("configId");
  if (jp.isEmpty(checkValues)) {
	showAlert(I18N.msg_no_sel);
  } else {
	  confirm(I18N.msg_del_confirm, function () {
		  var formEl = document.getElementById("bmConfigForm");
		  if ($("#configIds").val()) {
			$("#configIds").val(checkValues);
		  } else {
			formEl.appendChild(jp.createHidden("configIds", checkValues));
		  }
		  formEl.action = "<c:url value='/deleteBmConfigs'/>";
		  formEl.submit();
		  showLoading();
	  });
  }
}

//-->
</script>
