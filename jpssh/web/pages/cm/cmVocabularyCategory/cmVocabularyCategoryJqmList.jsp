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
  <div class="jpw-content">
	<div class="ui-bar ui-bar-c">
	  <div class="jqm-list-title"><fmt:message key="cmVocabulary.list.title"/></div>
	</div>
	<c:if test="${isVocabulary=='yes'}">
	<div class="ui-bar ui-bar-c">
	  <form id="cmVocabularyForm" method="post">
	    <input type="hidden" name="menuId" value="${menuId}"/>
	    <input type="hidden" name="categoryId" value="${categoryId}"/>
	    <input type="hidden" id="vocabularyIds" name="vocabularyIds"/>
	    <div style="float:right;margin:5px 0;">${categoryBtns}</div> 
	    <div style="float:left;"> 
	      <input type="checkbox" id="checkHead" onclick="jp.checkAll(this,'vocabularyId')" style="margin-top:8px;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
	      <a href="javascript:toPage('<c:url value='/cmVocabularyJqmAdd?menuId=${menuId}&categoryId=${categoryId}&categoryIds=${categoryIds}&isSimpleAdd=yes'/>')" data-role="button" data-icon="jp-add" data-mini="true" 
		     data-rel="dialog" rel="external" data-inline="true" class="lf"><fmt:message key="button.quick.add"/></a>
	      <a href="javascript:toPage('<c:url value='/cmVocabularyJqmAdd?menuId=${menuId}&categoryId=${categoryId}&categoryIds=${categoryIds}'/>')" data-role="button" data-icon="jp-add" data-mini="true" 
		     data-rel="dialog" rel="external" data-inline="true" class="lf"><fmt:message key="button.add"/></a>
		  <a href="javascript:batchDelete();" data-role="button" data-icon="jp-delete" data-mini="true"  rel="external" data-inline="true" class="lf">
	      	<fmt:message key="button.delete"/>
	      </a>
	      <a href="<c:url value='/cmVocabularyPdf?categoryId=${categoryId}'/>" data-role="button" data-icon="jp-export" data-mini="true"  rel="external" 
	     	 data-inline="true" class="lf" target="_blank">
	      	<fmt:message key="button.export"/>
	      </a>
	    </div>
	  </form>
	</div>
	<div class="withcheckbox">
	  <ul data-role="listview" data-filter="true" data-theme="d" data-split-icon="gear" data-split-theme="d">
		<c:forEach var="model" items="${vocabularyMaps}" varStatus="rowCounter">
		  <li><input type="checkbox" name="vocabularyId" value="${model.vocabularyId}"/>
		    <a href="javascript:edit('${model.vocabularyId}')">
		      <h3>${rowCounter.count}. ${model.firstName} ${model.firstPhonogram} (${model.displayNo})</h3><p>${model.firstMean}</p>
			</a>
		  </li>
		</c:forEach>
	  </ul>
	</div>
	</c:if>
	<c:if test="${isVocabulary=='no'}">
	  <c:if test="${categoryIds!=null}">
		  <div class="ui-bar ui-bar-c">
		  	<div style="float:right;margin:2px 0;">${categoryBtns}</div>
		  </div>
	  </c:if>
	  <c:forEach var="map" items="${categoryMaps}" varStatus="rowCounter">
		<c:if test='${rowCounter.count % 4 == 1}'>
		  <div class="ui-grid-c mt10 mb40" style="margin-left:-20px;">
			<div class="ui-block-a tc">
			  <div>
				<a href="javascript:toPage('<c:url value='/cmVocabularyCategory?menuId=${menuId}&categoryId=${map.categoryId}'/>')">
				  <img src="<c:url value='/resources/style/default/image/jp/icon/normal_folder.png'/>"  height="100" alt="${map.categoryName}"/>
				</a>
				<div class="mt5">
				  ${map.categoryName}  	
				</div>
			  </div>
			</div>
		  ${rowCounter.last ? '</div>' : ''}	
		</c:if>
		<c:if test='${rowCounter.count % 4 == 2}'>
			<div class="ui-block-b tc">
			  <div>
				<a href="javascript:toPage('<c:url value='/cmVocabularyCategory?menuId=${menuId}&categoryId=${map.categoryId}'/>')">
				  <img src="<c:url value='/resources/style/default/image/jp/icon/normal_folder.png'/>"  height="100" alt="${map.categoryName}"/>
				</a>
				<div class="mt5">
				  ${map.categoryName}  	
				</div>
			  </div>
			</div>
		  ${rowCounter.last ? '</div>' : ''}	
		</c:if>
		<c:if test='${rowCounter.count % 4 == 3}'>
			<div class="ui-block-c tc">
			  <div>
				<a href="javascript:toPage('<c:url value='/cmVocabularyCategory?menuId=${menuId}&categoryId=${map.categoryId}'/>')">
				  <img src="<c:url value='/resources/style/default/image/jp/icon/normal_folder.png'/>"  height="100" alt="${map.categoryName}"/>
				</a>
				<div class="mt5">
				  ${map.categoryName}  	
				</div>
			  </div>
			</div>
		  ${rowCounter.last ? '</div>' : ''}	
		</c:if>
		<c:if test='${rowCounter.count % 4 == 0}'>
			<div class="ui-block-b tc">
			  <div>
				<a href="javascript:toPage('<c:url value='/cmVocabularyCategory?menuId=${menuId}&categoryId=${map.categoryId}'/>')">
				  <img src="<c:url value='/resources/style/default/image/jp/icon/normal_folder.png'/>"  height="100" alt="${map.categoryName}"/>
				</a>
				<div class="mt5">
				  ${map.categoryName}  	
				</div>
			  </div>
			</div>
		  </div>	
		</c:if>
	  </c:forEach>
	</c:if>
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
function edit(vocabularyId) {
  window.location.href="<c:url value='/cmVocabularyJqmEdit/'/>" + vocabularyId + "?menuId=${menuId}&categoryId=${categoryId}";
  showLoading();
}

function batchDelete() {
  var checkValues = jp.getCheckValues("vocabularyId");
  if (jp.isEmpty(checkValues)) {
	showAlert(I18N.msg_no_sel);
  } else {
	  confirm(I18N.msg_del_confirm, function () {
		  var formEl = document.getElementById("cmVocabularyForm");
		  $("#vocabularyIds").val(checkValues);
		  formEl.action = "<c:url value='/deleteCmVocabularys'/>";
		  formEl.submit();
		  showLoading();
	  });
  }
}

$(document).ready(function(){
  
});

//-->
</script>
