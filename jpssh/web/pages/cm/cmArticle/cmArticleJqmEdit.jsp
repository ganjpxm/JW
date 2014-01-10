<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/thirdparty/SCEditor/minified/themes/default.min.css'/>"/>
  <%@ include file="/pages/common/inc/headjqm.jsp" %>
  
  <script src="<c:url value='/resources/js/jquery/plugin/jquery.iframe.transport.${jsSuffix}'/>"></script>
  <script src="<c:url value='/resources/js/jquery/plugin/jquery.fileupload.${jsSuffix}'/>"></script>
  <!--  
  <script src="<c:url value='/resources/js/tiny_mce/jquery.tinymce.${jsSuffix}'/>"></script>
  -->
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/SCEditor/minified/jquery.sceditor.bbcode.min.${jsSuffix}'/>"></script>
  <style type="text/css">
  	.ui-dialog-contain {margin:5% auto 15px; width:100%; }
  </style>
</head>
<body>
<div  style="margin: 2px 20px;">
  <div data-role="header" data-theme="d">
	<h1><fmt:message key="cmArticle.edit.title"/></h1>
  </div>
   <div style="text-align:right">
	<a href="javascript:addPhoto('${cmArticle.articleId}');" data-role="button" data-inline="true" data-icon="jp-add" data-mini="false">
		<fmt:message key="cmArticle.add.photo"/>
	</a>
  </div>
  <div id="pageContent" data-role="content" data-theme="c" style="margin-bottom: 50px;">
	<form id="cmArticleForm" action="<c:url value='/updateCmArticle'/>" method="post" data-ajax="false">
	  <input type="hidden" name="articleId" value="${cmArticle.articleId}"/>
	  <input type="hidden" name="menuId" value="${menuId}"/>
	  <input type="hidden" name="categoryId" value="${categoryId}"/>
	  <input type="hidden" id="newCategoryIds" name="newCategoryIds"/>
	  <input type="hidden" id="roleIds" name="roleIds"/>
	  <input type="hidden" name="folder" value="article"/>
	  <input type="hidden" name="from" value="${from}"/>
	  <input type="hidden" name="edit" value="${edit}"/>
	  <input type="hidden" name="pageNo" value="${pageNo}"/>
	  <input type="hidden" name="pageSize" value="${pageSize}"/>
	  <input type="hidden" name="searchTag" value="${tag}"/>
	  <!--articleId,articleCd,title,content,author,imageUrl,originUrl,displayNo,lang,roleIds,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
      <div class="lable"><span class="red">*</span><fmt:message key="common.field.title"/> : (${cmArticle.articleId})</div>
      <input id="title" name="title" class="validate[required]" value="${cmArticle.title}"/>
      <div class="lable"><fmt:message key="common.field.tag"/> : </div>
      <input id="tag" name="tag" value="${cmArticle.tag}"/>
      <div class="lable"><span class="red">*</span><fmt:message key="cmArticle.summary"/> : </div>
      <textarea name="summary" cols="40" rows="20" style="height:100px;">${cmArticle.summary}</textarea>
      <div class="lable"><span class="red">*</span><fmt:message key="common.field.content"/> : </div>
      <textarea id="sceditor" name="content" cols="40" rows="20" style="height:300px;" class="tinymce validate[required]">${cmArticle.content}</textarea>

      <div class="lable" style="margin-bottom:30px;">
      <div class="lable"><fmt:message key="common.field.imageUrl"/> :  
      <div style="float:right;"> 
      <label class="upload_btn fileinput-button mt5" style="margin-left:35px;">
         <span style="font-weight:bold;font-size:15px;" class="ui-icon-jp-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="button.add"/></span>
         <input id="fileupload" type="file" name="file" data-url="<c:url value='/uploadImageToRackspaceCloudFiles'/>" multiple>
      </label>
      </div>
      <input id="imageUrl" name="imageUrl" value="${cmArticle.imageUrl}"/>
      <c:choose>
		<c:when test="${fn:startsWith(cmArticle.imageUrl, 'http') || fn:startsWith(cmArticle.imageUrl, '/')}">
			<a href="<c:url value='${cmArticle.imageUrl}'/>">
		  	  <img id="image" src="<c:url value='${cmArticle.imageUrl}'/>" height="70"/>
	        </a>
		</c:when>
		<c:otherwise>
		  <c:if test="${cmPhoto.url!=''}">
            <a href="<c:url value='${ImageUrl}/${cmArticle.imageUrl}'/>">
		  	  <img id="image" src="<c:url value='${ImageUrl}/${cmArticle.imageUrl}'/>" height="70"/>
	        </a>
	      </c:if>
		</c:otherwise>
	  </c:choose>
      <a href="javascript:confirmDeleteFile();" data-role="button" 
		    data-icon="jp-delete" data-mini="true" data-inline="true" style="float:center"><fmt:message key="button.delete"/></a>
     <div>https://6ac83497b9ded244ddac-9bead101e19416bd4303c48efb571ff3.ssl.cf1.rackcdn.com</div>
      <div class="lable"><fmt:message key="common.field.originUrl"/> : </div>
      <input id="originUrl" name="originUrl" value="${cmArticle.originUrl}"/>
      <div class="lable"><fmt:message key="common.field.author"/> : </div>
      <input id="author" name="author" value="${cmArticle.author}"/>
      <div class="lable"><fmt:message key="common.field.displayNo"/> : </div>
      <input id="displayNo" name="displayNo" value="${cmArticle.displayNo}"/>
       
      <jp:jqmCheck name="roleId" checkType="checkbox" exprData="${roleMaps}"
        checkedIds="${cmArticle.roleIds}" lableValue="amRole.roleName"/>
        
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
  $("#cmArticleForm").validationEngine();
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
  /**
  $('textarea.tinymce').tinymce({
	// Location of TinyMCE script
	script_url : "<c:url value='/resources/js/tiny_mce/tiny_mce.${jsSuffix}'/>",
	// General options
	theme : "advanced",
	plugins : "autolink,lists,style,table,advhr,advlink,emotions,inlinepopups,insertdatetime,preview,searchreplace,contextmenu,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,advlist",
	// Theme options
	theme_advanced_buttons1 : "mybutton,bold,italic,underline,strikethrough,forecolor,backcolor,sub,sup,charmap,|,hr,removeformat,|,tablecontrols,|,undo,redo",
	theme_advanced_buttons2 : "formatselect,fontselect,fontsizeselect,|,justifyleft,justifycenter,justifyright,justifyfull,|,bullist,numlist,|,outdent,indent,|,insertdate,inserttime,|,fullscreen,code,preview",
	theme_advanced_buttons3 : "",
	theme_advanced_toolbar_location : "top",
	theme_advanced_toolbar_align : "left",
	theme_advanced_statusbar_location : "bottom",
	theme_advanced_resizing : true,
	relative_urls : false,
	setup : function(ed) {
        ed.addButton('jpw', {
            title : 'jpw',
            image : 'img/example.gif',
            onclick : function() {
                ed.focus();
                ed.selection.setContent('Hello world!');
            }
        });
    }
  });
  */
  
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
  if (!$('#cmArticleForm').validationEngine('validate')) {
	return false;
  }
  $("#roleIds").val(jp.getCheckValues("roleId"));
  $("#cmArticleForm").submit();
  showLoading(I18N.msg_saving);
}

$("input[name='categoryIds']").change(function() {
  $("#newCategoryIds").val(jp.getCheckValues("categoryIds"));
});
	
function confirmDeleteFile() {
  var imageUrl = $("#imageUrl").val();
  if (jp.isEmpty(imageUrl)) {
  	showAlert(I18N.msg_no_image_del);  
  } else {
	confirm(I18N.msg_del_confirm, function () {
		$("#imageUrl").val("");
    	$("#image").attr({src: "<c:url value='/resources/style/default/image/jp/photo/blank.png'/>"});
		/**
		$.getJSON("<c:url value='/deleteFile?fileUrl='/>" + imageUrl, function(data) {
			if (data.result == "success") {
		    	$("#imageUrl").val("");
		    	$("#image").attr({src: "<c:url value='/resources/style/default/image/jp/photo/blank.png'/>"});
		    } else {
		    	showAlert(data.result.result);
		    }
		});
		*/
	});
  }
}

function addPhoto(articleId) {
	window.open("<c:url value='/cmPhotoJqmAdd?edit=&from=/vtwo/photo&pageNo=1&pageSize=30&articleId='/>" + articleId, "_blank");	
}
//-->
</script>
