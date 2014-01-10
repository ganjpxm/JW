<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<%@ include file="/pages/common/inc/headjqm.jsp" %>
	<script src="<c:url value='/resources/js/jquery/plugin/jquery.iframe.transport.${jsSuffix}'/>"></script>
    <script src="<c:url value='/resources/js/jquery/plugin/jquery.fileupload.${jsSuffix}'/>"></script>
</head>
<body>
<%@ include file="/pages/common/inc/headerjqm.jsp" %>
<div id="content" data-role="content">
  <div class="jpw-content">
	<div class="ui-bar ui-bar-c">
	  <div class="jqm-list-title"><fmt:message key="cmPhoto.list.title"/></div>
	</div>
	<c:if test="${isPhoto=='yes'}">
	<div class="ui-bar ui-bar-c">
	  <form id="cmPhotoCategoryForm" method="post">
	    <input type="hidden" name="menuId" value="${menuId}"/>
	    <input type="hidden" name="categoryIds" value="${categoryIds}"/>
	    <!--  
	    <input type="checkbox" id="checkHead" onclick="jp.checkAll(this,'photoCategoryId')" style="margin-top:8px;" />
	    <a href="javascript:batchDelete();" data-role="button" data-icon="jp-delete" data-mini="true"  rel="external" data-inline="true" class="lf">
	      <fmt:message key="button.delete"/>
	    </a>
	    -->
	    <div style="float:left;margin:2px 0;">${categoryBtns}</div> 
	    <div style="float:right;"> 
	    <label class="upload_btn fileinput-button mt5" style="margin-left:35px;">
	      <span style="font-weight:bold;font-size:15px;" class="ui-icon-jp-add">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="button.add"/></span>
          <input id="fileupload" type="file" name="file" data-url="<c:url value='/uploadPhoto'/>" multiple>
	    </label>
	    </div>
	  </form>
	</div>
	<div class="ui-bar">
	  <c:forEach var="map" items="${photoMaps}" varStatus="rowCounter">
	  <div style="float:left;margin:10px;">
		<a href="#${map.photoId}" data-rel="popup" data-position-to="window" data-role="button" data-inline="true" data-transition="pop">
		  <img src="<c:url value='${map.url}'/>"  height="150" alt="${map.photoName}"/>
		</a>
		<div class="mt5">
		  <a href="javascript:edit('${map.photoId}')" data-role="button" data-icon="jp-edit" data-mini="true" data-inline="true"  
		    style="float:center"><fmt:message key="button.edit"/></a>
		  <a href="javascript:confirmDeletePhoto('${map.photoId}','${map.photoName}');" data-role="button" 
		    data-icon="jp-delete" data-mini="true" data-inline="true" style="float:center"><fmt:message key="button.delete"/></a>
		</div>
	  </div>
	  <div data-role="popup" id="${map.photoId}" data-overlay-theme="a" data-theme="c" style="max-width:1200px;" class="ui-corner-all">
			<div data-role="header" data-theme="a" class="ui-corner-top">
				<h1>${map.title}</h1>
			</div>
			<div data-role="content" data-theme="d" class="ui-corner-bottom ui-content">
				<img src="<c:url value='${map.url}'/>" class="bigImage" alt="${map.photoName}"/>
			</div>
	  </div>
	  </c:forEach>
	</div>  
	</c:if>
	<c:if test="${isPhoto=='no'}">
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
				<a href="javascript:toPage('<c:url value='/cmPhotoCategory?menuId=${menuId}&categoryId=${map.categoryId}'/>')">
				  <img src="<c:url value='/resources/style/default/image/jp/photo/folder.png'/>"  height="100" alt="${map.categoryName}"/>
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
				<a href="javascript:toPage('<c:url value='/cmPhotoCategory?menuId=${menuId}&categoryId=${map.categoryId}'/>')">
				  <img src="<c:url value='/resources/style/default/image/jp/photo/folder.png'/>"  height="100" alt="${map.categoryName}"/>
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
				<a href="javascript:toPage('<c:url value='/cmPhotoCategory?menuId=${menuId}&categoryId=${map.categoryId}'/>')">
				  <img src="<c:url value='/resources/style/default/image/jp/photo/folder.png'/>"  height="100" alt="${map.categoryName}"/>
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
				<a href="javascript:toPage('<c:url value='/cmPhotoCategory?menuId=${menuId}&categoryId=${map.categoryId}'/>')">
				  <img src="<c:url value='/resources/style/default/image/jp/photo/folder.png'/>"  height="100" alt="${map.categoryName}"/>
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
function edit(photoId) {
  window.location.href="<c:url value='/cmPhotoJqmEdit/'/>" + photoId + "?menuId=${menuId}&categoryId=${categoryId}";
  showLoading();
}

function popupDetailDialog(photoCategoryId, filedName) {
  
};

function confirmDeletePhoto(photoId,photoName) {
  confirm(I18N.msg_del_confirm, function () {
  	window.location.href="<c:url value='/deleteCmPhoto/'/>" + photoId + "?menuId=${menuId}" +
  	     "&categoryId=${categoryId}&photoName=" + photoName;
  });
}

function batchDelete() {
  var checkValues = jp.getCheckValues("photoCategoryId");
  if (jp.isEmpty(checkValues)) {
	showAlert(I18N.msg_no_sel);
  } else {
	  confirm(I18N.msg_del_confirm, function () {
		  var formEl = document.getElementById("cmPhotoCategoryForm");
		  if ($("#photoCategoryIds").val()) {
			$("#photoCategoryIds").val(checkValues);
		  } else {
			formEl.appendChild(jp.createHidden("photoCategoryIds", checkValues));
		  }
		  formEl.action = "<c:url value='/deleteCmPhotoCategorys'/>";
		  formEl.submit();
		  showLoading();
	  });
  }
}

$(document).ready(function(){
  $('#fileupload').fileupload({
    dataType: 'json',
    done: function (e, data) {
      if (data.result.result == "success") {
    	  location.reload(true);  
      } else {
    	  showAlert(data.result.result);
      }
    }
  });
  $("#bigImage").width($(window).width()*0.9);
});

//-->
</script>
