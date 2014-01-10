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
	<h1><fmt:message key="cmVocabulary.edit.title"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
	<form id="cmVocabularyForm" action="<c:url value='/updateCmVocabulary'/>" method="post" data-ajax="false">
	  <input type="hidden" name="vocabularyId" value="${cmVocabulary.vocabularyId}"/>
	  <input type="hidden" name="menuId" value="${menuId}"/>
	  <input type="hidden" name="articleId" value="${articleId}"/>
	  <input type="hidden" name="categoryId" value="${categoryId}"/>
	  <input type="hidden" name="from" value="${from}"/>
	  <input type="hidden" id="newCategoryIds" name="newCategoryIds"/>
	  <input type="hidden" id="roleIds" name="roleIds"/>
	  <input type="hidden" name="folder" value="vocabulary"/>
	  <!--vocabularyId,firstName,fistPhonogram,fistPronounceUrl,secondName,secondPhonogram,secondPronounceUrl,imageUrl,tag,levelId,levelName,displayNo,description,lang,roleIds,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	  <div class="lable"><span class="red">*</span><fmt:message key="cmVocabulary.firstName"/> : </div>
      <input id="firstName" name="firstName" class="validate[required]" value="${cmVocabulary.firstName}"/>
      <div class="lable"><fmt:message key="cmVocabulary.firstPhonogram"/> : </div>
      <input id="firstPhonogram" name="firstPhonogram" value="${cmVocabulary.firstPhonogram}"/>
      <div class="lable"><fmt:message key="cmVocabulary.firstPronounceUrl"/> : </div>
      <input id="firstPronounceUrl" name="firstPronounceUrl" value="${cmVocabulary.firstPronounceUrl}"/>
      <div class="lable"><fmt:message key="cmVocabulary.firstMean"/> : </div>
      <input id="firstMean" name="firstMean" value="${cmVocabulary.firstMean}"/>
      <div class="lable"><fmt:message key="cmVocabulary.firstDescription"/> : </div>
      <textarea id="firstDescription" name="firstDescription" style="height:100px">${cmVocabulary.firstDescription}</textarea>
      <div class="lable"><fmt:message key="cmVocabulary.secondName"/> : </div>
      <input id="secondName" name="secondName" value="${cmVocabulary.secondName}"/>
      <div class="lable"><fmt:message key="cmVocabulary.secondPhonogram"/> : </div>
      <input id="secondPhonogram" name="secondPhonogram" value="${cmVocabulary.secondPhonogram}"/>
      <div class="lable"><fmt:message key="cmVocabulary.secondPronounceUrl"/> : </div>
      <input id="secondPronounceUrl" name="secondPronounceUrl" value="${cmVocabulary.secondPronounceUrl}"/>
      <div class="lable"><fmt:message key="cmVocabulary.secondMean"/> : </div>
      <input id="secondMean" name="secondMean" value="${cmVocabulary.secondMean}"/>
      <div class="lable"><fmt:message key="cmVocabulary.secondDescription"/> : </div>
      <textarea id="secondDescription" name="secondDescription" style="height:100px">${cmVocabulary.secondDescription}</textarea>
      <div class="lable"><fmt:message key="cmVocabulary.levelName"/> : </div>
      <jp:jqmCheck name="levelId" checkType="radio" paramTypeCd="01" lang="${lang}" checkedIds="${cmVocabulary.levelId}" 
      	hiddenName="levelName"/>
      <div class="lable"><fmt:message key="common.field.tag"/> : </div>
      <input id="tag" name="tag" value="${cmVocabulary.tag}"/>
      <div class="lable"><fmt:message key="common.field.displayNo"/> : </div>
      <input id="displayNo" name="displayNo" value="${cmVocabulary.displayNo}"/>
		      
      <div class="lable" style="margin-bottom:30px;">
      <div class="lable"><fmt:message key="common.field.imageUrl"/> :  
      <div style="float:right;"> 
      <label class="upload_btn fileinput-button mt5" style="margin-left:35px;">
         <span style="font-weight:bold;font-size:15px;" class="ui-icon-jp-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="button.add"/></span>
         <input id="fileupload" type="file" name="file" data-url="<c:url value='/uploadFile'/>" multiple>
      </label>
      </div>
      <input id="imageUrl" name="imageUrl" value="${cmVocabulary.imageUrl}"/>
      <img id="image" src="<c:url value='${cmVocabulary.imageUrl}'/>"  height="70"/>
      <a href="javascript:confirmDeleteFile();" data-role="button" 
		    data-icon="jp-delete" data-mini="true" data-inline="true" style="float:center"><fmt:message key="button.delete"/></a>
      
      <jp:jqmCheck name="roleId" checkType="checkbox" exprData="${roleMaps}"
        checkedIds="${cmVocabulary.roleIds}" lableValue="amRole.roleName"/>
      <jp:jqmCheck name="categoryIds" checkType="checkbox" exprData="${categoryMaps}"
        checkedIds="${categoryIds}" lableValue="cmCategory.categoryName"/>  
        
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
  $("#cmVocabularyForm").validationEngine();
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
  if (!$('#cmVocabularyForm').validationEngine('validate')) {
	return false;
  }
  $("#roleIds").val(jp.getCheckValues("roleId"));
  $("#levelName").val(jp.getCheckIds("levelId"));
  $("#cmVocabularyForm").submit();
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
