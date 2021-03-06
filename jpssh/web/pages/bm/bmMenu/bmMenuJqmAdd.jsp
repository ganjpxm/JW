<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <%@ include file="/pages/common/inc/headjqm.jsp" %>
  <script src="<c:url value='/resources/js/jquery/plugin/jquery.iframe.transport.${jsSuffix}'/>"></script>
  <script src="<c:url value='/resources/js/jquery/plugin/jquery.fileupload.${jsSuffix}'/>"></script>
</head>
<body>
<div style="margin: 2px 20px;">
  <div id="header" data-role="header" data-theme="d">
	<h1><fmt:message key="bmMenu.add.title"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
	<form id="bmMenuForm" action="<c:url value='/saveBmMenu'/>" method="post" data-ajax="false">
	  <input type="hidden" name="activeMenuId" value="${menuId}"/>
	  <input type="hidden" name="menuPid" value="${menuPid}"/>
	  <input type="hidden" name="menuCategoryId" value="${menuCategoryId}"/>
	  <input type="hidden" name="displayLevel" value="${displayLevel}"/>
	  <input type="hidden" name="endFlag" value="0"/>
	  <input type="hidden" name="folder" value="menu"/>
	  <input type="hidden" id="roleIds" name="roleIds"/>
	  <input type="hidden" id="from" name="from" value="${from}"/>
	  
	  <!--menuId,menuPid,menuCd,menuName,imageUrl,url,displayLevel,displayNo,endFlag,menuCategoryId,lang,modifyTimestamp,createDateTime,dataState,-->
	  <div class="lable"><span class="red">*</span><fmt:message key="bmMenu.menuCd"/> : </div>
      <input id="menuCd" name="menuCd" class="validate[required]" value="${menuCd}"/>
      <div class="lable"><span class="red">*</span><fmt:message key="bmMenu.menuName"/> : </div>
      <input id="menuName" name="menuName" class="validate[required]"/>      
      <div class="lable"><fmt:message key="bmMenu.url"/> : </div>
      <input id="url" name="url"/>
      <div class="lable"><span class="red">*</span><fmt:message key="common.field.displayNo"/> : </div>
      <input id="displayNo" name="displayNo" class="validate[required]" value="${displayNo}"/>
      <div class="lable" style="margin-bottom:30px;">
      <div class="lable"><fmt:message key="bmMenu.imageUrl"/> : 
      <div style="float:right;"> 
      <label class="upload_btn fileinput-button mt5" style="margin-left:35px;">
         <span style="font-weight:bold;font-size:15px;" class="ui-icon-jp-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="button.add"/></span>
         <input id="fileupload" type="file" name="file" data-url="<c:url value='/uploadFile'/>" multiple>
      </label>
      </div>
      <input id="imageUrl" name="imageUrl"/>
      <img id="image" src="<c:url value='/resources/style/default/image/jp/photo/blank.png'/>"  height="70"/>
      <a href="javascript:confirmDeleteFile();" data-role="button" 
		    data-icon="jp-delete" data-mini="true" data-inline="true" style="float:center"><fmt:message key="button.delete"/></a>
      <!--
      <div class="lable"><span class="red">*</span><fmt:message key="common.field.displayLevel"/> : </div>
      <input id="displayLevel" name="displayLevel" class="validate[required]"/>
      <span class="red">*</span><fmt:message key="common.field.end.flag"/>:</label>
	  <select name="endFlag" id="endFlag" data-role="slider">
		<option value="0" selected><fmt:message key="common.value.no"/></option>
		<option value="1"><fmt:message key="common.value.yes"/></option>
	  </select> 
	  </div>
      <jp:jqmCheck name="menuCategoryId" checkType="radio" paramTypeCd="00" lang="${lang}" inputClass="validate[required]"
        checkedIds="${menuCategoryId}" hiddenName="menuCategory"/>
      -->
      <div class="lable"><span class="red">*</span><fmt:message key="bmMenu.isAddMenuId"/> : </div>
      <select name="addMenuId" id="addMenuId" data-role="slider">
		<option value="0" <c:if test="${addMenuId=='0'}">selected</c:if>><fmt:message key="common.value.no"/></option>
		<option value="1" <c:if test="${addMenuId!='0'}">selected</c:if>><fmt:message key="common.value.yes"/></option>
	  </select>
	  <jp:jqmCheck name="roleId" checkType="checkbox" exprData="${roleMaps}" lableValue="amRole.roleName"/> 
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
  $("#bmMenuForm").validationEngine();
  $('#fileupload').fileupload({
    dataType: 'json',
    done: function (e, data) {
      if (!jp.isEmpty(data.result.saveUrl)) {
    	  $("#imageUrl").val(data.result.saveUrl);
      	  $("#image").attr({src: data.result.showUrl});  
      }
      if (data.result.result != "success") {
    	  showAlert(data.result.result);
      }
    }
  });
});

function save() {
  if (!$('#bmMenuForm').validationEngine('validate')) {
	return false;
  }
  $("#roleIds").val(jp.getCheckValues("roleId"));
  $("#bmMenuForm").submit();
  showLoading(I18N.msg_saving);
}

function confirmDeleteFile() {
  var imageUrl = $("#imageUrl").val();
  if (jp.isEmpty(imageUrl)) {
  	showAlert(I18N.msg_no_image_del);  
  } else {
	confirm(I18N.msg_del_confirm, function () {
		$.getJSON("<c:url value='/deleteFile?fileUrl='/>" + imageUrl, function(data) {
			if (data.result == "success") {
		    	$("#imageUrl").val("");
		    	$("#image").attr({src: "<c:url value='/resources/style/default/image/jp/photo/blank.png'/>"});
		    } else {
		    	showAlert(data.result.result);
		    }
		});
	});
  }
}
//-->
</script>
