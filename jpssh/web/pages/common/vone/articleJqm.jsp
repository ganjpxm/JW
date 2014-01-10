<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<%@ include file="/pages/common/inc/headjqm.jsp" %>
	<style type="text/css">
		.ui-listview-filter {border-left: 1px solid #b3b3b3; border-right: 1px solid #b3b3b3; border-top: 1px solid #b3b3b3;}
	</style>
</head>
<body>
<%@ include file="/pages/cm/common/jqmStyle/inc/header.jsp" %>
<div id="body" class="body" style="margin-top:20px;">
<!--articleId,articleCd,title,content,author,imageUrl,originUrl,displayNo,lang,roleIds,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
  <div id="rightContent" class="fr" style="margin-right:60px;">
  	  <ul data-role="listview" data-filter="true" data-theme="d" data-split-icon="gear" 
  	  	data-split-theme="d" style="border-left:1px solid #b3b3b3; border-right: 1px solid #b3b3b3;">
		<c:forEach var="map" items="${articleMaps}">
		  <li>
		    <a href="javascript:popupDetailDialog('${map.articleId}')">
		      <h3>${map.title}</h3><p></p></a>
			</a>
		  </li>
		</c:forEach>
	  </ul>
  </div>
  <div id="leftMenu" style="float:left;overflow-y:auto;margin-top:-20px;overflow-x:hidden;">
    ${categoryJqmMenu}    
  </div>
</div>
<%@ include file="/pages/cm/common/jqmStyle/inc/footer.jsp" %>
</body>
</html>
<script type="text/javascript">
<!--
function popupDetailDialog(articleId) {
  window.location.href="<c:url value='/articleJqmDetail/'/>" + articleId;
  showLoading();
};

$(document).ready(function(){
  var header = $("#header");
  var navMenu = $("#nav_menu");
  var leftMenu = $("#leftMenu");
  var rightContent = $("#rightContent");
  var bodyHeight = $(window).height()-header.height()-navMenu.height()-photoSliderHeight;
  var bodyWidth = $("#body").width();
  if ($(window).width()<=650) {
	  leftMenu.width($(window).width()-30);
	  rightContent.width($(window).width()-30);
	  rightContent.css("margin-right",0);
  } else {
	  leftMenu.height(bodyHeight-35);
	  leftMenu.width(250);
	  rightContent.width(bodyWidth-330);
  }
});
//-->
</script>