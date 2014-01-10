<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<%@ include file="/pages/common/inc/headjqm.jsp" %>
	<style type="text/css">
		.ui-checkbox input {left:-5px; width:20px; height:20px; margin-top:10px;}
		.ui-li-heading {margin-left:25px;}
	</style>
</head>
<body>
<div data-role="dialog">
  <div data-role="header" data-theme="d">
	<h1><fmt:message key="cmVocabulary.add.article"/></h1>
  </div>
  <div class="ui-bar ui-bar-c">
    <a href="javascript:addVocabulary()" data-role="button" data-icon="jp-add" data-mini="true" 
      data-rel="dialog" rel="external" data-inline="true" class="lf"><fmt:message key="cmVocabulary.add.below"/></a>
  	<a href="javascript:addNewVocabulary();" data-role="button" data-icon="jp-add" data-mini="true"  rel="external" data-inline="true" class="lf">
      <fmt:message key="cmVocabulary.add.new"/>
    </a>
    <a href="<c:url value='/cmVocabularyJqmAdd?articleId=${articleId}&from=cmVocabularyChooseDialog&isSimpleAdd=yes'/>" data-role="button" data-icon="jp-add" data-mini="true"  
      rel="external" data-inline="true" class="lf">
      <fmt:message key="button.quick.add"/>
    </a>
  </div>
  <div data-role="content" data-theme="c" >
	<ul data-role="listview" data-filter="true" data-theme="d" >
	  <c:forEach var="map" items="${cmVocabularys}">
		 <li><input type="checkbox" name="vocabularyId" value="${map.vocabularyId}"/>
		 <h3>${map.firstName}</h3>
		 </li>
	  </c:forEach>
	</ul>
  </div>
</div>
</body>
</html>
<script type="text/javascript">
<!--
function addVocabulary() {
  var vocabularyIds = jp.getCheckValues("vocabularyId");
  if (jp.isEmpty(vocabularyIds)) {
	showAlert(I18N.msg_no_sel);
  } else {
	$.getJSON("<c:url value='/saveCmVocabularyArticles/${articleId}?vocabularyIds='/>" + vocabularyIds, function(data) {
		showAlert(data.result);
	});
  }
}

function addNewVocabulary() {
  window.location.href="<c:url value='/cmVocabularyJqmAdd?articleId=${articleId}&from=cmVocabularyChooseDialog'/>";
  showLoading();
}
//-->
</script>
