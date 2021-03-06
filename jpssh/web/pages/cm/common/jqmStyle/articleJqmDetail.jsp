<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <%@ include file="/pages/common/inc/headjqm.jsp" %>
  <style type="text/css">
  	.ui-dialog-contain {margin:5% auto 15px; width:800px; max-width:800px;}
  </style>
</head>
<body>
<div data-role="dialog">
  <div id="header" data-role="header" data-theme="d">
	<h1><fmt:message key="cmArticle.detail.title"/></h1>
  </div>
  <div id="content" data-role="content" data-theme="c">
	  <!--articleId,articleCd,title,content,author,imageUrl,originUrl,displayNo,lang,roleIds,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
      <div class="tc" style="margin-top:-20px;"><h2>${cmArticle.title}</h2></div>
      <div class="fl fs1"><fmt:message key="common.field.author"/> : 
        <c:choose>
		  <c:when test="${cmArticle.author=='quote' || cmArticle.author=='引用'}">
			<a href="${cmArticle.originUrl}" target="_blank">${cmArticle.author}</a>
		  </c:when>
		  <c:otherwise>
		    ${cmArticle.author}
		  </c:otherwise>
	    </c:choose>  
      </div>
      <div class="fr fs1"><fmt:message key="common.field.updateTime"/> : <fmt:formatDate value="${cmArticle.createDateTime}" pattern="HH:mm dd/MM/yyyy" /></div>
      <c:if test="${cmArticle.imageUrl!=''}">
	    <img src="<c:url value='${cmArticle.imageUrl}'/>" width="100%"/>
      </c:if>
      <div class="jqm-form-lable" style="margin-top:20px;">${cmArticle.content}</div>
      <div><span style="font-weight:bold;"><fmt:message key="common.field.tag"/></span> : ${cmArticle.tag}</div>
      <!--vocabularyId,firstName,firstPhonogram,fistPronounceUrl,secondName,secondPhonogram,secondPronounceUrl,imageUrl,tag,levelId,levelName-->
      <c:forEach var="map" items="${vocabularyMaps}">
        <a href="#${map.vocabularyArticleId}" data-rel="popup" data-position-to="window" data-role="button" data-inline="true" 
         	data-transition="pop">${map.firstName}</a>
		<div data-role="popup" id="${map.vocabularyArticleId}" data-overlay-theme="a" data-theme="c" 
		 	style="max-width:400px;" class="ui-corner-all">
		  <div data-role="content" data-theme="d" class="ui-corner-bottom ui-content">
			<p>1、${map.firstName} ${map.firstPhonogram}</p>
			<p>2、${map.firstMean}</p>
			<p>3、${map.firstDescription}</p>
			<p>1、${map.secondName}</p>
			<p>3、${map.secondDescription}</p>
			<a href="#" data-role="button" data-inline="true" data-rel="back" data-theme="${jqmTheme}"><fmt:message key="button.cancel"/></a>
		  </div>
		</div>
	  </c:forEach>
      
	  <div style="text-align:right">
		<a href="#" data-role="button" data-rel="back" data-inline="true" data-theme="${jqmTheme}"><fmt:message key="button.back"/></a>
	  </div>
  </div>
</div>
</body>
</html>
<script type="text/javascript">
<!--
function edit(articleId) {
  window.location.href="<c:url value='/cmArticleJqmEdit/'/>" + articleId + "?menuId=${menuId}&categoryId=${categoryId}";
  showLoading();
}

function deleteVocabulary(vocabularyArticleId) {
  window.location.href="<c:url value='/deleteCmVocabularyArticle/'/>" + vocabularyArticleId + 
  	"?menuId=${menuId}&categoryId=${categoryId}&articleId=${cmArticle.articleId}";
  showLoading();
}

//-->
</script>
