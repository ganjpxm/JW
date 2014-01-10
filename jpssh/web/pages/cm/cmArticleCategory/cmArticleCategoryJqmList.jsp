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
	  <div class="jqm-list-title"><fmt:message key="cmArticle.list.title"/></div>
	</div>
	<c:if test="${isArticle=='yes'}">
	<div class="ui-bar ui-bar-c">
	  <form id="cmArticleForm" method="post">
	    <input type="hidden" name="menuId" value="${menuId}"/>
	    <input type="hidden" id="categoryId" name="categoryId" value="${categoryId}"/>
	    <input type="hidden" id="articleIds" name="articleIds"/>
	    <div style="float:right;margin:5px 0;">${categoryBtns}</div> 
	    <div style="float:left;"> 
	      <input type="checkbox" id="checkHead" onclick="jp.checkAll(this,'articleId')" style="margin-top:8px;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
	      <a href="javascript:toPage('<c:url value='/cmArticleJqmAdd?menuId=${menuId}&categoryId=${categoryId}&categoryIds=${categoryIds}'/>')" data-role="button" data-icon="jp-add" data-mini="true" 
		     data-rel="dialog" rel="external" data-inline="true" class="lf"><fmt:message key="button.add"/></a>
		  <a href="javascript:batchDelete();" data-role="button" data-icon="jp-delete" data-mini="true"  rel="external" data-inline="true" class="lf">
	      	<fmt:message key="button.delete"/>
	      </a>
	    </div>
	  </form>
	</div>
	<div class="withcheckbox">
	  <ul data-role="listview" data-filter="true" data-theme="d" data-split-icon="jp-edit" data-split-theme="d">
		<c:forEach var="model" items="${articleMaps}">
		  <li><input type="checkbox" name="articleId" value="${model.articleId}"/>
		    <a href="javascript:popupDetailDialog('${model.articleId}')">
		      <h3>${model.title}</h3><p></p></a>
			  <a href="javascript:edit('${model.articleId}');" data-rel="dialog" data-transition="flit">
			  	<fmt:message key="button.edit"/>
			  </a>
		  </li>
		</c:forEach>
	  </ul>
	</div>
	</c:if>
	<c:if test="${isArticle=='no'}">
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
				<a href="javascript:toPage('<c:url value='/cmArticleCategory?menuId=${menuId}&categoryId=${map.categoryId}'/>')">
				  <img src="<c:url value='/resources/style/default/image/jp/icon/open_folder.png'/>"  height="100" alt="${map.categoryName}"/>
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
				<a href="javascript:toPage('<c:url value='/cmArticleCategory?menuId=${menuId}&categoryId=${map.categoryId}'/>')">
				  <img src="<c:url value='/resources/style/default/image/jp/icon/open_folder.png'/>"  height="100" alt="${map.categoryName}"/>
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
				<a href="javascript:toPage('<c:url value='/cmArticleCategory?menuId=${menuId}&categoryId=${map.categoryId}'/>')">
				  <img src="<c:url value='/resources/style/default/image/jp/icon/open_folder.png'/>"  height="100" alt="${map.categoryName}"/>
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
				<a href="javascript:toPage('<c:url value='/cmArticleCategory?menuId=${menuId}&categoryId=${map.categoryId}'/>')">
				  <img src="<c:url value='/resources/style/default/image/jp/icon/open_folder.png'/>"  height="100" alt="${map.categoryName}"/>
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
function edit(articleId) {
  window.location.href="<c:url value='/cmArticleJqmEdit/'/>" + articleId + "?menuId=${menuId}&categoryId=${categoryId}";
  showLoading();
}

function popupDetailDialog(articleId) {
  window.location.href="<c:url value='/cmArticleJqmDetail/'/>" + articleId + "?menuId=${menuId}&categoryId=${categoryId}";
  showLoading();
};

function confirmDeleteArticle(articleId,articleName) {
  confirm(I18N.msg_del_confirm, function () {
  	window.location.href="<c:url value='/deleteCmArticle/'/>" + articleId + "?menuId=${menuId}" +
  	     "&categoryId=${categoryId}&articleName=" + articleName;
  });
}

function batchDelete() {
  var checkValues = jp.getCheckValues("articleId");
  if (jp.isEmpty(checkValues)) {
	showAlert(I18N.msg_no_sel);
  } else {
	  confirm(I18N.msg_del_confirm, function () {
		  var formEl = document.getElementById("cmArticleForm");
		  $("#articleIds").val(checkValues);
		  formEl.action = "<c:url value='/deleteCmArticles'/>";
		  formEl.submit();
		  showLoading();
	  });
  }
}

$(document).ready(function(){
  
});

//-->
</script>
