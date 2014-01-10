<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<%@ include file="/pages/common/inc/headjqm.jsp" %>
	<script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.iframe.transport.${jsSuffix}'/>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.fileupload.${jsSuffix}'/>"></script>
</head>
<body>
<%@ include file="/pages/common/inc/headerjqm.jsp" %>
<div id="content" data-role="content">
  <div class="jpw-content" style="margin-bottom:30px;">
	<div class="ui-bar ui-bar-c">
	  <div class="jqm-list-title"><fmt:message key="amUser.list.title"/></div>
	</div>
	<div class="ui-bar ui-bar-c">
	  <form id="amUserForm" method="post">
	    <input type="hidden" name="menuId" value="${menuId}"/>
	    <input type="checkbox" id="checkHead" onclick="jp.checkAll(this,'userId')" style="margin-top:8px;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
	      <a href="javascript:toPage('<c:url value='/amUserJqmAdd?menuId=${menuId}'/>')" data-role="button" data-icon="jp-add" data-mini="true" 
		     data-rel="dialog" rel="external" data-inline="true" class="lf"><fmt:message key="button.add"/></a>
		  <a href="javascript:batchDelete();" data-role="button" data-icon="jp-delete" data-mini="true"  rel="external" data-inline="true" class="lf">
	      	<fmt:message key="button.delete"/>
	      </a>
	    <a href="javascript:export();" data-role="button" data-icon="jp-export" data-mini="true"  rel="external" data-inline="true" class="lf">
	      	<fmt:message key="button.export"/>
	    </a>
	 </form>   
	    <div id="photoButton" class="dp100" style="padding:10px;margin-top:-48px;margin-left:305px;float:left;display:inline;">
		  <label class="upload_btn fileinput-button jpUpFile" style="padding:5px;">
		    <span style="font-weight:bold;font-size:15px;">Import...</span>
		        <form method="post"><input id="importUserExcel" type="file" name="file" data-url="<c:url value='/importUserExcel'/>" multiple/></form>
		  </label>
	    </div>
	    
	</div>
	<!--userId,userCd,userName,userAlias,userLevel,userScore,loginTimes,password,mobilePhone,qq,email,birthday,photoUrl,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	<div class="withcheckbox">
	  <ul data-role="listview" data-filter="true" data-theme="d" data-split-icon="gear" data-split-theme="d">
		<c:forEach var="amUser" items="${amUsers}" varStatus="status">
		  <li><input type="checkbox" name="userId" value="${amUser.userId}"/>
		    <a href="javascript:popupDetailDialog('${amUser.userId}','${amUser.userCd}','${amUser.userName}','${amUser.email}','${amUser.password}','${amUser.roleIds}','${amUser.roleNames}')">
		      <h3>${status.count}. ${amUser.userName}</h3><p><fmt:message key="amUser.userCd"/>:${amUser.userCd};
		      <fmt:message key="amUser.email"/>:${amUser.email};
		      <fmt:message key="amRole.roleName"/>:${amUser.roleNames};
		      </p></a>
			  <a href="javascript:popupDialog('${amUser.userId}','${amUser.userName}','${amUser.roleIds}');" 
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
function popupDialog(userId,userName,roleIds) {
  $('<div>').simpledialog2({
    mode: 'button',
    headerText: I18N.choose,
    headerClose: true,
    buttonPrompt: userName,
    buttons : {
      '<fmt:message key="button.edit"/>': {
        click: function () { 
        	window.location.href="<c:url value='/amUserJqmEdit/'/>" + userId + "?menuId=${menuId}&roleIds=" + roleIds;
        	showLoading();
        },
        icon: "jp-edit" , theme: "c"
      },
      '<fmt:message key="button.delete"/>': {
        click: function () { confirmDelete(userId);},
        icon: "jp-delete", theme: "c"
      },
      '<fmt:message key="button.cancel"/>' : {
  	    click: function () {}, icon: "jp-cancel", theme: "c"
  	  }
    }
  });
}

function popupDetailDialog(userId, userCd, userName, email, password, roleIds, roleNames) {
  var editUrl = "<c:url value='/amUserJqmEdit/'/>" + userId + "?menuId=${menuId}&roleIds=" + roleIds;
  $("<div>").simpledialog2({
    mode:'blank',
    width:'300px',
    blankContent: 
    	"<div class='tc m10'><h3>" + "<fmt:message key='amUser.detail.title'/>" + "</h3></div>" +
    	"<div class='p10'><fmt:message key='amUser.userCd'/> : <span class='underline'>" + userCd + "</span></div>" +
    	"<div class='p10'><fmt:message key='amUser.userName'/> : <span class='underline'>" + userName + "</span></div>" +
    	"<div class='p10'><fmt:message key='amUser.email'/> : <span class='underline'>" + email + "</span></div>" +
    	"<div class='p10'><fmt:message key='amUser.password'/> : <span class='underline'>" + password + "</span></div>" +
    	"<div class='p10'><fmt:message key='amRole.roleName'/> : <span class='underline'>" + roleNames + "</span></div>" +
    	"<a rel='close' data-role='button' data-mini='true' data-inline='true' href='#' class='fr'" + 
    	   "data-theme='${jqmTheme}'><fmt:message key='button.close'/></a>" +
    	"<a rel='external' data-role='button' data-mini='true' data-inline='true' data-theme='${jqmTheme}'" + 
    	   "href='javascript:confirmDelete(&quot;" + userId + "&quot;);' class='fr'><fmt:message key='button.delete'/></a>"+   
    	"<a rel='external' data-role='button' data-mini='true' data-inline='true' data-theme='${jqmTheme}'" + 
    	   "href='" + editUrl + "' class='fr'><fmt:message key='button.edit'/></a>"
  });
};

function confirmDelete(userId) {
  confirm(I18N.msg_del_confirm, function () {
  	window.location.href="<c:url value='/deleteAmUser/'/>" + userId + "?menuId=${menuId}";
  });
}

function batchDelete() {
  var checkValues = jp.getCheckValues("userId");
  if (jp.isEmpty(checkValues)) {
	showAlert(I18N.msg_no_sel);
  } else {
	  confirm(I18N.msg_del_confirm, function () {
		  var formEl = document.getElementById("amUserForm");
		  if ($("#userIds").val()) {
			$("#userIds").val(checkValues);
		  } else {
			formEl.appendChild(jp.createHidden("userIds", checkValues));
		  }
		  formEl.action = "<c:url value='/deleteAmUsers'/>";
		  formEl.submit();
		  showLoading();
	  });
  }
}

$(document).ready(function () {
  $("#importUserExcel").fileupload({
	dataType: 'json',
	done: function (e, data) {
	  if (data.result.result == "success") {
		window.location.href=window.location.href; 
	  } else {
	    alert(data.result.result);
	  }
  	}
  });
});   

//-->
</script>
