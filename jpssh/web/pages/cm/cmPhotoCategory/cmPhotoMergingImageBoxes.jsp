<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<%@ include file="/pages/common/inc/headjqm.jsp" %>
	<script src="<c:url value='/resources/js/jquery/plugin/jquery.transform.min.${jsSuffix}'/>"></script>
	<script src="<c:url value='/resources/js/jquery/plugin/jquery.mergingImageBoxes.${jsSuffix}'/>"></script>
</head>
<body>
  <!-- 750*500 125*125-->
  <div id="im_wrapper" class="im_wrapper">
	<c:forEach var="map" items="${photoMaps}" varStatus="rowCounter">
	  <c:choose>
	  <c:when test="${rowCounter.count==1}">
	    <div style="background-position:0px 0px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==2}">
	    <div style="background-position:-125px 0px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==3}">
	    <div style="background-position:-250px 0px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==4}">
	    <div style="background-position:-375px 0px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==5}">
	    <div style="background-position:-500px 0px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==6}">
	    <div style="background-position:-625px 0px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  
	  <c:when test="${rowCounter.count==7}">
	    <div style="background-position:0px -125px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==8}">
	    <div style="background-position:-125px -125px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==9}">
	    <div style="background-position:-250px -125px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==10}">
	    <div style="background-position:-375px -125px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==11}">
	    <div style="background-position:-500px -125px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==12}">
	    <div style="background-position:-625px -125px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:otherwise>
	    <div><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:otherwise>
	  </c:choose>
	</c:forEach>  
  </div>
  <div id="im_loading" class="im_loading"></div>
  <div id="im_next" class="im_next"></div>
  <div id="im_prev" class="im_prev"></div>  
</body>
</html>
<script type="text/javascript">
<!--

//-->
</script>
