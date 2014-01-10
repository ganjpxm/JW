<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<%@ include file="/pages/common/inc/headjqm.jsp" %>
</head>
<body>
<%@ include file="/pages/cm/common/jqmStyle/inc/header.jsp" %>
<div id="body" class="body" style="margin-top:20px;">
  <div id="rightContent" class="fr" style="margin-right:20px;">
    <div data-role="fieldcontain" style="margin:-12px 0 2px -60px;">
    <fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
	   <input type="checkbox" name="firstNameDisplay" id="firstNameDisplay" checked/>
	   <label for="firstNameDisplay"><fmt:message key="cmVocabulary.firstName"/></label>
	   <input type="checkbox" name="secondNameDisplay" id="secondNameDisplay" checked/>
	   <label for="secondNameDisplay"><fmt:message key="cmVocabulary.secondName"/></label>
	   <!--
	   <input type="checkbox" name="firstPhonogramDisplay" id="firstPhonogramDisplay" checked/>
	   <label for="firstPhonogramDisplay"><fmt:message key="cmVocabulary.firstPhonogram"/></label>
	   -->
	   <input type="checkbox" name="simpleDisplay" id="simpleDisplay"/>
	   <label for="simpleDisplay"><fmt:message key="button.collapse"/></label>
	   <input type="checkbox" name="print" id="print"/>
	   <label for="print"><fmt:message key="button.print"/></label>
    </fieldset>
	</div>
	<c:forEach var="map" items="${vocabularyMaps}">
	   <div data-role="collapsible" data-theme="${jqmTheme}" data-content-theme="c" class="collapse" data-collapsed='false' 
	   		data-mini="true" style="width:49%;float:left; margin-left:1%;">
		  <h3><span class="firstName">${map.firstName}</span></h3>
		  <table style="text-align:left;">
		    <tr>
		      <c:if test="${map.imageUrl!=''}">
		      <td width="100px"><img src="<c:url value='${map.imageUrl}'/>" alt="${map.firstName}" width="100px"/></td>
		      <td style="padding-left:20px;"><span class="firstPhonogram">${map.firstPhonogram}</span><br/><span class="secondName">${map.secondName}</span></td>
		      </c:if>
		      <c:if test="${map.imageUrl==''}">
		      	<td colspan="2">${map.firstPhonogram}<br/><span class="secondName">${map.secondName}</span></td>
		      </c:if>
		    </tr>
		    <!--  
		  	<tr>
		  	  <td colspan="2">${map.firstDescription}<br/>${map.secondDescription}</td>
		  	</tr>
		  	-->
		  </table>
		  <a href="#${map.vocabularyId}" data-rel="popup" data-position-to="window" data-role="button" 
		  	  		data-inline="true" data-transition="pop" data-mini="true" class="fr" style="margin-top:-22px"><fmt:message key="button.more"/></a>
		  <c:if test="${isAdmin=='yes' || isManager=='yes'}">
	   	     <a href="<c:url value='/cmVocabularyJqmEdit/${map.vocabularyId}?categoryId=${categoryId}'/>" data-role='button' rel='external' 
	   	     	data-icon='jp-edit' target="_blank" 
	   	     	data-iconpos="notext" data-inline='true' class="fr" style="margin-top:-22px"></a>
	   	  </c:if>
	   	  <a href="http://dict.youdao.com/search?le=eng&q=${map.firstName}&keyfrom=dict.top" data-role='button' rel='external' 
	   	     	data-icon='youdao' target="_blank" 
	   	     	data-iconpos="notext" data-inline='true' class="fr" style="margin-top:-22px"></a>	
	   	  
			<div data-role="popup" id="${map.vocabularyId}" data-overlay-theme="a" data-theme="c" 
		 			style="max-width:400px;" class="ui-corner-all">
		  		<div data-role="content" data-theme="d" class="ui-corner-bottom ui-content">
					<p>1、${map.firstName} ${map.firstPhonogram}</p>
					<p>2、${map.firstMean}</p>
					<p>3、${map.firstDescription}</p>
					<p>1、${map.secondName}</p>
					<p>3、${map.secondDescription}</p>
					<a href="#" data-role="button" data-inline="true" data-rel="back" data-theme="c" data-mini="true" class="fr">
						<fmt:message key="button.cancel"/></a>
		  		</div>
			</div>
			
	   </div>
	</c:forEach>
  </div>
  <div id="leftMenu" style="float:left;margin-top:-10px;">
    ${categoryJqmMenu}    
  </div>
</div>
<%@ include file="/pages/cm/common/jqmStyle/inc/footer.jsp" %>
</body>
</html>
<script type="text/javascript">
<!--
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
	  rightContent.width(bodyWidth-320);
	  //rightContent.height(bodyHeight-35);
  }
  $("#firstNameDisplay").change(function() {
	  if ($(this).is(':checked') == false) {
		  $(".firstName").hide();	  
	  } else {
		  $(".firstName").show();
	  }
  });
  $("#secondNameDisplay").change(function() {
	  if ($(this).is(':checked') == false) {
		  $(".secondName").hide();	  
	  } else {
		  $(".secondName").show();
	  }
  });
  $("#print").change(function() {
	  $(this).attr('checked', false);
	  $(this).trigger("refresh");
	  window.open("<c:url value='/cmVocabularyPdf?categoryId=${categoryId}'/>", "_blank");
  });
  $("#simpleDisplay").change(function() {
	  if ($(this).is(':checked') == false) {
		  $(".collapse").attr("data-collapsed", false).trigger('expand');	  
	  } else {
		  $(".collapse").attr("data-collapsed", true).trigger('collapse');
	  }
  });
});
function popupDetailDialog(vocabularyId) {
  
};

//-->
</script>
