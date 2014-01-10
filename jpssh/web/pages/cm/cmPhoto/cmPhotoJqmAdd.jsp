<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/thirdparty/SCEditor/minified/themes/default.min.css'/>"/>
  <%@ include file="/pages/common/inc/headjqm.jsp" %>
  <script src="<c:url value='/resources/js/jquery/plugin/jquery.iframe.transport.${jsSuffix}'/>"></script>
  <script src="<c:url value='/resources/js/jquery/plugin/jquery.fileupload.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/SCEditor/minified/jquery.sceditor.bbcode.min.${jsSuffix}'/>"></script>
  <style type="text/css">
  	.ui-dialog-contain {margin:5% auto 15px; width:100%; }
  </style>
</head>
<body>
<div style="margin: 2px 20px;">
  <div id="header" data-role="header" data-theme="d">
	<h1><fmt:message key="cmPhoto.add.title"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
      <!--
      <div style="float:right;">  
      <label class="upload_btn fileinput-button mt5" style="margin-left:35px;">
         <span style="font-weight:bold;font-size:15px;" class="ui-icon-jp-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="button.add"/> <fmt:message key="cmPhoto.thumbUrl"/></span>
         <form method="post"><input type="hidden" name="folder" value="photo/thumbs"/>
         <input id="fileuploadThumbPhoto" type="file" name="file" data-url="<c:url value='/uploadFile'/>" multiple></input>
      </label>
      </div>
      -->
       <div style="float:right;"> 
	      <label class="upload_btn fileinput-button mt5" style="margin-left:35px;">
	         <span style="font-weight:bold;font-size:15px;" class="ui-icon-jp-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Upload Photo</span>
	         <form method="post"><input type="hidden" name="folder" value="photo"/>
	         <input id="fileuploadPhoto" type="file" name="file" data-url="<c:url value='/uploadImageToRackspaceCloudFiles'/>" multiple></input></form>
	      </label>
      </div>
	  <form id="cmPhotoForm" action="<c:url value='/saveCmPhoto'/>" method="post" data-ajax="false">
	  <input type="hidden" name="menuId" value="${menuId}"/>
	  <input type="hidden" name="categoryId" value="${categoryId}"/>
	  <input type="hidden" id="newCategoryIds" name="newCategoryIds"/>
	  <input type="hidden" id="roleIds" name="roleIds" />
	  <input type="hidden" name="from" value="${from}"/>
	  <input type="hidden" name="edit" value="${edit}"/>
	  <input type="hidden" name="pageNo" value="${pageNo}"/>
	  <input type="hidden" name="pageSize" value="${pageSize}"/>
	  <!--photoId,photoName,title,url,tag,remark,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	  <div class="lable"><fmt:message key="cmPhoto.title"/> : </div>
      <input id="title" name="title" />
      <div class="lable"><fmt:message key="cmPhoto.tag"/> : </div>
      <input id="tag" name="tag" value="${tag}"/>
      <!--  
      <jp:jqmCheck name="tagAssist" checkType="checkbox" data="mergingImageBoxes:mergingImageBoxes;slidingGallery:slidingGallery;"/>
      -->
	  <div class="lable">Photo Url : 
      <input id="photoUrl" name="url" />
   	  <img id="image" src="<c:url value='/resources/style/default/image/jp/photo/blank.png'/>"  height="70" />
      <a href="javascript:confirmDeletePhoto();" data-role="button" 
		    data-icon="jp-delete" data-mini="true" data-inline="true" style="float:center"><fmt:message key="button.delete"/></a>	    
<!--    
      <div class="lable"><fmt:message key="cmPhoto.title"/> URL :  fmt:message key="cmPhoto.thumbUrl"/>
      <input id="thumbUrl" name="thumbUrl" />
-->
<!--  
      <img id="thumbImage" src="<c:url value='${cmPhoto.thumbUrl}'/>"  height="70" />
      <a href="javascript:confirmDeleteThumbPhoto();" data-role="button" 
		    data-icon="jp-delete" data-mini="true" data-inline="true" style="float:center"><fmt:message key="button.delete"/></a>
-->
      <div class="lable"><fmt:message key="common.field.displayNo"/> : </div>
      <input id="displayNo" name="displayNo" />
      <div class="lable"><fmt:message key="common.field.remark"/> : </div>
      <textarea id="remark" name="remark" cols="40" rows="20" style="height:200px;" class="tinymce validate[required]"></textarea>
      <div class="lable"><fmt:message key="cmArticle.articleId"/> : </div>
      <input id="refArticleId" name="refArticleId" value="${articleId}"/>
      <jp:jqmCheck name="roleId" checkType="checkbox" exprData="${roleMaps}" checkedIds="${cmPhoto.roleIds}" lableValue="amRole.roleName"/>
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
  $("#cmPhotoForm").validationEngine();
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
  
  $('#fileuploadPhoto').fileupload({
    dataType: 'json',
    done: function (e, data) {
      if (!jp.isEmpty(data.result.saveUrl)) {
    	  $("#photoUrl").val(data.result.saveUrl);
      	  $("#image").attr({src: data.result.showUrl});  
      }
      if (data.result.result != "success") {
    	  showAlert(data.result.result);
      }
    }
  });
  
  var initEditor = function() {
	$("textarea.tinymce").sceditor({
		plugins: 'xhtml',
		emoticonsRoot: "<c:url value='/resources/thirdparty/SCEditor/'/>",
		style: "<c:url value='/resources/thirdparty/SCEditor/minified/jquery.sceditor.default.min.css'/>"
	});
  };
  initEditor();
});

function save() {
  if (!$('#cmPhotoForm').validationEngine('validate')) {
	return false;
  }
  $("#roleIds").val(jp.getCheckValues("roleId"));
  $("#cmPhotoForm").submit();
  showLoading(I18N.msg_saving);
}

$("input[name='categoryIds']").change(function() {
  $("#newCategoryIds").val(jp.getCheckValues("categoryIds"));
});

function confirmDeleteThumbPhoto() {
  var thumbUrl = $("#thumbUrl").val();
  if (jp.isEmpty(thumbUrl)) {
  	showAlert(I18N.msg_no_image_del);  
  } else {
	confirm(I18N.msg_del_confirm, function () {
		$.getJSON("<c:url value='/deleteFile?fileUrl='/>" + thumbUrl, function(data) {
			if (data.result == "success") {
		    	$("#thumbUrl").val("");
		    	$("#thumbImage").attr({src: "<c:url value='/resources/style/default/image/jp/photo/blank.png'/>"});
		    } else {
		    	showAlert(data.result.result);
		    }
		});
	});
  }
}

function confirmDeletePhoto() {
  var photoUrl = $("#photoUrl").val();
  if (jp.isEmpty(photoUrl)) {
  	showAlert(I18N.msg_no_image_del);  
  } else {
	confirm(I18N.msg_del_confirm, function () {
		$("#photoUrl").val("");
    	$("#image").attr({src: "<c:url value='/resources/style/default/image/jp/photo/blank.png'/>"});
		/**
		$.getJSON("<c:url value='/deleteFile?fileUrl='/>" + photoUrl, function(data) {
			if (data.result == "success") {
		    	$("#photoUrl").val("");
		    	$("#image").attr({src: "<c:url value='/resources/style/default/image/jp/photo/blank.png'/>"});
		    } else {
		    	showAlert(data.result.result);
		    }
		});
		*/
	});
  }
}
	
$("input[name='tagAssist']").change(function() {
  $("#tag").val(jp.getCheckValues("tagAssist"));
});
//-->
</script>
