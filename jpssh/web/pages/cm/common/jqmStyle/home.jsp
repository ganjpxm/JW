<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
  <%@ include file="/pages/common/inc/headjqm.jsp" %>
</head>
<body>
<%@ include file="/pages/cm/common/jqmStyle/inc/header.jsp" %>
<div id="body" class="body">
   <c:forEach var="pMenuMapAndMenuMaps" items="${pMenuMapAndMenuMapss}">
      <c:forEach items="${pMenuMapAndMenuMaps}" var="map">
        <div data-role="collapsible" data-theme="${jqmTheme}" data-content-theme="c" data-collapsed='false' data-mini="true" class="frame">
	      <h2>${map.key.menuName}</h2>
	      <div style="text-align:left;margin-left:-13px;margin-right:-25px;">
			 <c:forEach var="menuMap" items="${map.value}">
	   	  	  <a href="${menuMap.url}" target="_blank">
	   	  	  	<img src="<c:url value='${menuMap.imageUrl}'/>" height="50" alt="${menuMap.menuName}" style="margin-right:6.5px;"/>
	   	  	  </a>
	   	     </c:forEach>
	   	     <c:if test="${isAdmin=='yes' || isManager=='yes'}">
	   	     <a href="javascript:add('${map.key.menuId}', '${map.key.menuCategoryId}', '2', '${map.key.menuCd}');" data-role='button'
	   	      rel='external' data-icon='add' data-iconpos="notext" data-mini='true' data-inline='true'></a>
	   	     </c:if>	   	  
	   	  </div>
	   	</div>  		
   	  </c:forEach>
   </c:forEach>
</div>  
<%@ include file="/pages/cm/common/jqmStyle/inc/footer.jsp" %>
</body>
</html>
<script type="text/javascript">
<!--
function add(menuPid, menuCategoryId, displayLevel, menuCd) {
  var url = "<c:url value='/bmMenuJqmAdd?menuCategoryId='/>" + menuCategoryId + "&displayLevel=" + displayLevel +
		    "&menuCd=" + menuCd + "&menuPid=" + menuPid + "&addMenuId=0&from=home";
  window.location.href=url;
  showLoading();
}
//-->
</script>
