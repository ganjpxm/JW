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
	<h1><fmt:message key="cmAudio.add.title"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
    <div style="float:right;">
      <label class="upload_btn fileinput-button mt5" style="margin-left:35px;">
         <span style="font-weight:bold;font-size:15px;" class="ui-icon-jp-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Add Photo</span>
         <form method="post"><input id="fileuploadThumbPhoto" type="file" name="file" data-url="<c:url value='/uploadImageToRackspaceCloudFiles'/>" multiple></input>
      </label>
    </div>
      
    <div style="float:right;"> 
      <label class="upload_btn fileinput-button mt5" style="margin-left:35px;">
         <span style="font-weight:bold;font-size:15px;" class="ui-icon-jp-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Add Audio</span>
         <form method="post"><input id="fileuploadAudio" type="file" name="file" data-url="<c:url value='/uploadAudioToRackspaceCloudFiles'/>" multiple></input></form>
      </label>
    </div>
	<form id="cmAudioForm" action="<c:url value='/saveCmAudio'/>" method="post" data-ajax="false">
	  <input type="hidden" name="menuId" value="${menuId}"/>
	  <input type="hidden" name="categoryId" value="${categoryId}"/>
	  <input type="hidden" id="newCategoryIds" name="newCategoryIds"/>
	  <input type="hidden" id="roleIds" name="roleIds" />
	  <input type="hidden" name="from" value="${from}"/>
	  <input type="hidden" name="edit" value="${edit}"/>
	  <input type="hidden" name="pageNo" value="${pageNo}"/>
	  <input type="hidden" name="pageSize" value="${pageSize}"/>
	  <!--audioId,audioName,title,url,tag,remark,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	  <div class="lable"><fmt:message key="common.field.title"/> : </div>
      <input id="title" name="title" />
      <div class="lable"><fmt:message key="common.field.tag"/> : </div>
      <input id="tag" name="tag" />
      
      <div class="lable">Photo : 
      <input id="thumbUrl" name="thumbUrl"/>
      <img id="thumbImage" src="<c:url value='/resources/style/default/image/jp/photo/blank.png'/>"  height="70" />
      <a href="javascript:confirmDeleteThumbAudio();" data-role="button" 
		    data-icon="jp-delete" data-mini="true" data-inline="true" style="float:center"><fmt:message key="button.delete"/></a>
		    
	  <div class="lable">Audio <fmt:message key="common.field.url"/> (Audio Name+Audio URL;) : 
      <textarea id="audioUrl" name="url" cols="40" rows="20" style="height:100px;">${cmAudio.url}</textarea>
      
      <div class="lable"><fmt:message key="common.field.displayNo"/> : </div>
      <input id="displayNo" name="displayNo" />
	  <div class="lable"><fmt:message key="common.field.description"/> : 
      <textarea name="description" cols="40" rows="20" style="height:100px;"></textarea>
      <div class="lable"><fmt:message key="common.field.remark"/> : 
      <textarea name="remark" cols="40" rows="20" style="height:100px;"></textarea>
      <div id="cloudUrl"></div>

      <jp:jqmCheck name="roleId" checkType="checkbox" exprData="${roleMaps}" checkedIds="${cmAudio.roleIds}" lableValue="amRole.roleName"/>
      <!--  
      <jp:jqmCheck name="categoryIds" checkType="checkbox" exprData="${categoryMaps}" checkedIds="${categoryIds}" lableValue="cmCategory.categoryName"/>  
      -->
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
  $("#cmAudioForm").validationEngine();
  $('#fileuploadThumbPhoto').fileupload({
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
  
  $('#fileuploadAudio').fileupload({
    dataType: 'json',
    done: function (e, data) {
      if (!jp.isEmpty(data.result.saveUrl)) {
    	  $("#cloudUrl").html(data.result.showUrl);
    	  $("#audioUrl").val($("#audioUrl").val()+data.result.saveUrl);
      }
      if (data.result.result != "success") {
    	  showAlert(data.result.result);
      }
    }
  });
  
});

function save() {
  if (!$('#cmAudioForm').validationEngine('validate')) {
	return false;
  }
  $("#roleIds").val(jp.getCheckValues("roleId"));
  $("#cmAudioForm").submit();
  showLoading(I18N.msg_saving);
}

$("input[name='categoryIds']").change(function() {
  $("#newCategoryIds").val(jp.getCheckValues("categoryIds"));
});

function confirmDeleteThumbAudio() {
  var thumbUrl = $("#thumbUrl").val();
  if (jp.isEmpty(thumbUrl)) {
  	showAlert(I18N.msg_no_image_del);  
  } else {
	confirm(I18N.msg_del_confirm, function () {
		$.getJSON("<c:url value='/deleteFile?fileUrl='/>" + thumbUrl, function(data) {
			if (data.result == "success") {
		    	$("#thumbUrl").val("");
		    	$("#thumbImage").attr({src: "<c:url value='/resources/style/default/image/jp/audio/blank.png'/>"});
		    } else {
		    	showAlert(data.result.result);
		    }
		});
	});
  }
}

function confirmDeleteAudio() {
  var audioUrl = $("#audioUrl").val();
  if (jp.isEmpty(audioUrl)) {
  	showAlert(I18N.msg_no_image_del);  
  } else {
	confirm(I18N.msg_del_confirm, function () {
		$.getJSON("<c:url value='/deleteFile?fileUrl='/>" + audioUrl, function(data) {
			if (data.result == "success") {
		    	$("#audioUrl").val("");
		    	$("#image").attr({src: "<c:url value='/resources/style/default/image/jp/audio/blank.png'/>"});
		    } else {
		    	showAlert(data.result.result);
		    }
		});
	});
  }
}
	
//-->
</script>
