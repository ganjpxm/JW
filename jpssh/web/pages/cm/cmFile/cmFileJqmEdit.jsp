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
<div data-role="dialog">
  <div id="header" data-role="header" data-theme="d">
	<h1><fmt:message key="cmFile.edit.title"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
    <div style="float:right;">
      <label class="upload_btn fileinput-button mt5" style="margin-left:35px;">
         <span style="font-weight:bold;font-size:15px;" class="ui-icon-jp-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="button.add"/> <fmt:message key="common.field.thumbUrl"/></span>
         <form method="post"><input type="hidden" name="folder" value="file/thumbs"/>
         <input id="fileuploadThumbFile" type="file" name="file" data-url="<c:url value='/uploadFile'/>" multiple></input>
      </label>
      </div>
      
      <div style="float:right;"> 
      <label class="upload_btn fileinput-button mt5" style="margin-left:35px;">
         <span style="font-weight:bold;font-size:15px;" class="ui-icon-jp-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="button.add"/> <fmt:message key="common.field.url"/></span>
         <form method="post"><input type="hidden" name="folder" value="file"/>
         <input id="fileuploadFile" type="file" name="file" data-url="<c:url value='/uploadFile'/>" multiple></input></form>
      </label>
    </div>
	<form id="cmFileForm" action="<c:url value='/updateCmFile'/>" method="post" data-ajax="false">
	  <input type="hidden" name="fileId" value="${cmFile.fileId}"/>
	  <input type="hidden" name="menuId" value="${menuId}"/>
	  <input type="hidden" name="categoryId" value="${categoryId}"/>
	  <input type="hidden" id="newCategoryIds" name="newCategoryIds"/>
	  <input type="hidden" id="roleIds" name="roleIds" value="${cmFile.roleIds}"/>
	  <input type="hidden" name="from" value="${from}"/>
	  <input type="hidden" name="edit" value="${edit}"/>
	  <input type="hidden" name="pageNo" value="${pageNo}"/>
	  <input type="hidden" name="pageSize" value="${pageSize}"/>
	  <input type="hidden" name="searchTag" value="${tag}"/>
	  <!--fileId,fileName,title,url,tag,remark,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	  <div class="lable"><fmt:message key="common.field.title"/> : </div>
      <input id="title" name="title" value="${cmFile.title}"/>
      <div class="lable"><fmt:message key="common.field.tag"/> : </div>
      <input id="tag" name="tag" value="${cmFile.tag}"/>
      
      <div class="lable"><fmt:message key="common.field.thumbUrl"/> : 
      <input id="thumbUrl" name="thumbUrl" value="${cmFile.thumbUrl}"/>
      <img id="thumbImage" src="<c:url value='${cmFile.thumbUrl}'/>"  height="70" />
      <a href="javascript:confirmDeleteThumbFile();" data-role="button" 
		    data-icon="jp-delete" data-mini="true" data-inline="true" style="float:center"><fmt:message key="button.delete"/></a>
		    
	  <div class="lable"><fmt:message key="common.field.url"/> : 
	  
      <input id="fileUrl" name="url" value="${cmFile.url}"/>
      <img id="image" src="<c:url value='${cmFile.url}'/>"  height="70" />
      <a href="javascript:confirmDeleteFile();" data-role="button" 
		    data-icon="jp-delete" data-mini="true" data-inline="true" style="float:center"><fmt:message key="button.delete"/></a>	    
      
      <div class="lable"><fmt:message key="common.field.displayNo"/> : </div>
      <input id="displayNo" name="displayNo" value="${cmFile.displayNo}"/>
      <jp:jqmCheck name="roleId" checkType="checkbox" exprData="${roleMaps}" checkedIds="${cmFile.roleIds}" lableValue="amRole.roleName"/>
      <jp:jqmCheck name="categoryIds" checkType="checkbox" exprData="${categoryMaps}" checkedIds="${categoryIds}" lableValue="cmCategory.categoryName"/>  
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
  $("#cmFileForm").validationEngine();
  $('#fileuploadThumbFile').fileupload({
    dataType: 'json',
    done: function (e, data) {
      if (!jp.isEmpty(data.result.saveUrl)) {
    	  $("#thumbUrl").val(data.result.saveUrl);
      	  $("#thumbImage").attr({src: data.result.showUrl});  
      }
      if (data.result.result != "success") {
    	  showAlert(data.result.result);
      }
    }
  });
  
  $('#fileuploadFile').fileupload({
    dataType: 'json',
    done: function (e, data) {
      if (!jp.isEmpty(data.result.saveUrl)) {
    	  $("#fileUrl").val(data.result.saveUrl);
      	  $("#image").attr({src: data.result.showUrl});  
      }
      if (data.result.result != "success") {
    	  showAlert(data.result.result);
      }
    }
  });
  
});

function save() {
  if (!$('#cmFileForm').validationEngine('validate')) {
	return false;
  }
  $("#roleIds").val(jp.getCheckValues("roleId"));
  $("#cmFileForm").submit();
  showLoading(I18N.msg_saving);
}

$("input[name='categoryIds']").change(function() {
  $("#newCategoryIds").val(jp.getCheckValues("categoryIds"));
});

function confirmDeleteThumbFile() {
  var thumbUrl = $("#thumbUrl").val();
  if (jp.isEmpty(thumbUrl)) {
  	showAlert(I18N.msg_no_image_del);  
  } else {
	confirm(I18N.msg_del_confirm, function () {
		$.getJSON("<c:url value='/deleteFile?fileUrl='/>" + thumbUrl, function(data) {
			if (data.result == "success") {
		    	$("#thumbUrl").val("");
		    	$("#thumbImage").attr({src: "<c:url value='/resources/style/default/image/jp/file/blank.png'/>"});
		    } else {
		    	showAlert(data.result.result);
		    }
		});
	});
  }
}

function confirmDeleteFile() {
  var fileUrl = $("#fileUrl").val();
  if (jp.isEmpty(fileUrl)) {
  	showAlert(I18N.msg_no_image_del);  
  } else {
	confirm(I18N.msg_del_confirm, function () {
		$.getJSON("<c:url value='/deleteFile?fileUrl='/>" + fileUrl, function(data) {
			if (data.result == "success") {
		    	$("#fileUrl").val("");
		    	$("#image").attr({src: "<c:url value='/resources/style/default/image/jp/file/blank.png'/>"});
		    } else {
		    	showAlert(data.result.result);
		    }
		});
	});
  }
}
	
//-->
</script>
