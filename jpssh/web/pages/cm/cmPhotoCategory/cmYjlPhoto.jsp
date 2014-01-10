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
  <audio controls="controls" height="100" width="50" AUTOSTART=TRUE>
    <source src="<c:url value='/resources/upload/mp3/ShayneWard-NoPromises.mp3'/>" type="audio/mp3" />
	<EMBED SRC="<c:url value='/resources/upload/mp3/ShayneWard-NoPromises.mp3'/>"; width="80" height="45">
  </audio>
  <a href="javascript:autoPlay();" data-role="button" data-mini="false" style="margin-top:-10px;"
			rel="external" data-inline="true" data-theme="${jqmTheme}"><span id="play">Play</span></a>
  <div id="im_wrapper" class="im_wrapper">
	<c:forEach var="map" items="${photoMaps}" varStatus="rowCounter">
	  <c:choose>
	  <c:when test="${rowCounter.count==1}">
	    <div id="first" style="background-position:0px 0px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
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
	  
	  <c:when test="${rowCounter.count==13}">
	    <div style="background-position:0px -250px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==14}">
	    <div style="background-position:-125px -250px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==15}">
	    <div style="background-position:-250px -250px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==16}">
	    <div style="background-position:-375px -250px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==17}">
	    <div style="background-position:-500px -250px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==18}">
	    <div style="background-position:-625px -250px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  
	  <c:when test="${rowCounter.count==19}">
	    <div style="background-position:0px -375px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==20}">
	    <div style="background-position:-125px -375px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==21}">
	    <div style="background-position:-250px -375px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==22}">
	    <div style="background-position:-375px -375px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==23}">
	    <div style="background-position:-500px -375px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:when test="${rowCounter.count==24}">
	    <div style="background-position:-625px -375px;"><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div>
	  </c:when>
	  <c:otherwise>
	    <!-- <div><img src="<c:url value='${map.thumbUrl}'/>" alt="" /></div> -->
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
var play = "stop";
function autoPlay(){
  var right = $("#im_next").css('right');
  if (right=="-50px") {
	$("#first").click();  
  }
  if (play=="stop") {
	$("#play").text("Stop");
	play = "start";
	$('body').everyTime('3s',function(){
		$("#im_next").click();
		right = $("#im_next").css('right')
		if (right=="-50px") {
			$("#play").text("Play");
			play = "stop";
			$('body').stopTime();
		} 
	});
  } else {
	$("#play").text("Play");
	play = "stop";
	$('body').stopTime();
  }
  //$('body').everyTime('3s',func);
}

//$('body').everyTime('3s',test);
//$('body').stopTime ();
//-->
</script>
