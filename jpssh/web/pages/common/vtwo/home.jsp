<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="author" content="Gan Jianping"/>
  <meta http-equiv=“X-UA-Compatible” content=“IE=8”> 
  <title id='Description'><fmt:message key='project.name'/></title>
  <link rel="shortcut icon" type="image/png" href="<c:url value='/resources/style/${jpTheme}/image/jp/icon/jp_icon.png'/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jpw.jq.${cssSuffix}'/>">
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jquery.ui.${cssSuffix}'/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/thirdparty/jqwidgets/styles/jqx.base.${cssSuffix}'/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/default/thirdparty/bootstrap.css'/>" />
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jpw.${cssSuffix}'/>"/>
  <!--[if IE]><link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/default/ie.${cssSuffix}'/>"><![endif]-->
  
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-1.8.2.min.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/ui/jquery.ui.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.cookie.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.nbspSlider.${jsSuffix}'/>"></script>
  <script type="text/JavaScript" src="<c:url value='/resources/js/jquery/ui/jquery.ui.touch-punch.min.${jsSuffix}'/>"></script>
  <script type="text/JavaScript" src="<c:url value='/resources/js/jquery/ui/jquery.shapeshift.min.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.form.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/i18n/i18n_${lang}.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.iframe.transport.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.fileupload.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.masonry.min.${jsSuffix}'/>"></script>
  
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxcore.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxinput.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxradiobutton.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxbuttons.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxdropdownbutton.${jsSuffix}'/>"></script>
  
  <script type="text/javascript" src="<c:url value='/resources/js/jp.${jsSuffix}'/>"></script>
  <style>
  	.ui-button-text {padding: .1em 1em;font-size:16px;}
  	#parentDycontainer, .childDycontainer {margin-bottom: 20px; border-radius: 5px; clear: both; 
  		-webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;}
  	.parentBox, .childBox {margin: 5px; line-height: 1.4em; float: left; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;}
  </style>
</head>
<body onload="scaleWidth();changeScrollPhoto();" class="jpw2">
<%@ include file="/pages/common/vtwo/inc/header.jsp" %>
<div id="photoSlider" class="nbspslider">
  <ul>
	<c:forEach var="map" items="${photoMaps}" varStatus="rowCounter">
	  <li><a href="javascript:void(0);"><img src="<c:url value='${map.url}'/>" alt="${map.title}" /></a></li>
	</c:forEach>				
  </ul>
</div>
<div id="body">
  <div class="content">
    <div class="scrollNews">
      <marquee scrollAmount=1 width="100%" direction="left" behavior=alternate onmouseover=stop() onmouseout=start()>
		<fmt:message key='porject.welcome'/>
	  </marquee>
	</div>
	<div class="clearfix" style="height:1px;"></div>
	  <div id="parentDycontainer" class="clearfix masonry" style="width:99%">
	    <c:forEach var="pMenuMapAndMenuMaps" items="${pMenuMapAndMenuMapss}" varStatus="status">
	      <c:forEach items="${pMenuMapAndMenuMaps}" var="map">
	        <div class="parentBox" style="background-color:white;display:block;">
		      <div style="background-color:<c:choose><c:when test="${status.count%3==1}">#e67504</c:when><c:when test="${status.count%3==2}">#34c38f</c:when><c:otherwise>#7ccc0c</c:otherwise></c:choose>;
		        height:35px;text-align:center;font-style:bold;font-size:20px;color:white;padding-top:10px;border-top-left-radius: 5px; border-top-right-radius: 5px;">
		        ${map.key.menuName}
		      </div>
		      <div class="childDycontainer" class="clearfix masonry" id="${map.key.menuId}" style="background-color:white;margin:5px;auto;">
				<c:forEach var="menuMap" items="${map.value}">
				  <div class="childBox" style="width:65px;border:0;height:70px;">
		   	  	    <a href="${menuMap.url}" target="_blank" style="text-decoration:none;">
		   	  	      <c:if test="${menuMap.imageUrl!=''}">
		   	  	  	    <img src="<c:url value='${menuMap.imageUrl}'/>" width="100%" alt="${menuMap.menuName}" title="${menuMap.menuName}"/>
		   	  	  	  </c:if>
		   	  	  	  <c:if test="${menuMap.imageUrl==''}">
		   	  	  	    <div style="height:60px;width:60px;padding:5px;color:white;vertical-align:middle;text-align:center;word-break:break-all;word-wrap:break-word;background-image:url('<c:url value='/resources/style/default/image/jp/icon/green_button_icon.png'/>')">
						  ${menuMap.menuName}   	  	  	    
		   	  	  	    </div>
		   	  	  	  </c:if>
		   	  	    </a>
		   	  	  </div>
		   	  	  <c:if test="${edit=='yes' && user!=null}">
		   	  	    <div class="childBox" style="width:65px;border:0;height:70px;">
		   	  	  	  <button class="gradient-green-btn" onclick="javascript:editlink('${menuMap.menuId}');"><fmt:message key="button.edit"/></button>
		   	  	  	  <button class="gradient-green-btn" onclick="javascript:confirmDeleteLink('${menuMap.menuId}','${menuMap.menuName}');" style="margin-top:2px;">
		   	  	  	  	<fmt:message key="button.delete"/></button>
		   	  	    </div>
		   	  	  </c:if>
		   	    </c:forEach>
		   	    <c:if test="${isAdmin=='yes' || isManager=='yes'}">
		  		    <div class="childBox" style="width:65px;border:0;height:70px;">
			   	  	  <a href="javascript:addLink('${map.key.menuId}', '${map.key.menuCategoryId}', '2', '${map.key.nextMenuCd}', '${map.key.nextDisplayNo}');" target="_blank">
			   	  	  	<img src="<c:url value='/resources/style/default/image/jp/icon/add-48-48.png'/>" width="48" height="48" alt="<fmt:message key="button.add"/>" style="padding:10px;"/>
			   	  	  </a>
			   	  	</div>
			   	  	<div class="childBox" style="width:65px;border:0;height:70px;">
			   	  	  <a href="javascript:confirmDeleteLink('${map.key.menuId}','${map.key.menuName}');" target="_blank">
			   	  	  	<img src="<c:url value='/resources/style/default/image/jp/icon/delete-48-48.png'/>" width="48" height="48" alt="<fmt:message key="button.delete"/>" style="padding:10px;"/>
			   	  	  </a>
			   	  	</div>
		   	    </c:if>
		   	  </div>
		   	</div>  		
	   	  </c:forEach>
	    </c:forEach>
	    <c:if test="${user!=null}">
	      <div class="parentBox">
	        <button class="gradient-green-btn" onclick="javascript:addLinkCategory();"><fmt:message key="button.add"/></button>
	        <button class="gradient-green-btn" onclick="javascript:edit();"><fmt:message key="button.edit"/></button>
	        <button class="gradient-green-btn" onclick="javascript:cancelEdit();"><fmt:message key="button.cancel"/></button>
	      </div>
	    </c:if>
  	  </div>
  </div>
</div>
<div class="clearfix"></div>
<div id="dialog-confirm-delete-link" title="" style="display:none;">
  <p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
  The item will be permanently deleted and cannot be recovered. Are you sure?</p>
</div>
<%@ include file="/pages/common/vtwo/inc/footer.jsp" %>
</body>
</html>
<script>
<!--
var width;
function scaleWidth() {
  var winWidth = $(window).width();
  var containerWidth = $("#parentDycontainer").width();
  if (winWidth>1366) {
	  containerWidth=containerWidth-60;
	  width = containerWidth/4;
  }	else if (winWidth>=1024) {
	  containerWidth=containerWidth-42;
	  width = containerWidth/3;
	  if (winWidth>1280) {
		  $('#parentDycontainer .childBox').css("margin", "8px");
	  } else {
		  $('#parentDycontainer .childBox').css("margin", "6px");
	  }
  }	else if (winWidth>=600) {
	  containerWidth=containerWidth-22;
	  width = containerWidth/2;
	  if (winWidth>800) {
	  	$('#parentDycontainer .childBox').css("margin", "4px");
	  } else {
		$('#parentDycontainer .childBox').css("margin", "3px");  
	  }
  } else {
	  containerWidth=containerWidth-10;
	  width = containerWidth;
	  if (winWidth<350) { 
	  	$('#parentDycontainer .childBox').css("margin", "2px");
	  }
  }
  $("#parentDycontainer div.parentBox").css("width", width);	
  $('#parentDycontainer').masonry({itemSelector: '.parentBox'});
  $('#parentDycontainer .childDycontainer').masonry({itemSelector: '.parentBox'});
}

function displayWebsite(divId) {
  var $div = $('#' +divId);
  if ($div.is(':hidden')) {
	$div.css('display', 'inline');
  } else {
	$div.css('display', 'none');
 }
}

function addLink(menuPid, menuCategoryId, displayLevel, menuCd, displayNo) {
  var url = "<c:url value='/bmMenuJqmAdd?menuCategoryId='/>" + menuCategoryId + "&displayLevel=" + displayLevel + "&displayNo=" + displayNo +
		    "&menuCd=" + menuCd + "&menuPid=" + menuPid + "&addMenuId=0&from=vtwo/home";
  window.location.href=url;
}

function addLinkCategory() {
  var url = "<c:url value='/bmMenuJqmAdd?menuCategoryId=${nextLinkCategoryMap.menuCategoryId}&displayNo=${nextLinkCategoryMap.nextLinkCategoryMenuSize}" +
            "&menuCd=${nextLinkCategoryMap.nextLinkCategoryMenuCd}'/>" + "&displayLevel=1&addMenuId=0&from=vtwo/home";
  window.location.href=url;
}
	
function editlink(menuId) {
  window.location.href = "<c:url value='/bmMenuJqmEdit/'/>" + menuId + "?addMenuId=0&from=vtwo/home";
}

function confirmDeleteLink(menuId,menuName) {
  $("#dialog-confirm-delete-link").attr("title", I18N.msg_delete_link_title);
  $("#dialog-confirm-delete-link p").html(String.format(I18N.msg_delete_content, menuName));
  $("#dialog-confirm-delete-link").dialog({
    resizable: false,
    height: 240,
    modal: true,
    buttons: {
      "<fmt:message key='button.delete'/>": function() {
    	  $(this).dialog("close");
    	  window.location.href="<c:url value='/deleteBmMenu/'/>" + menuId + "?from=vtwo/home";
      },
      "<fmt:message key='button.cancel'/>": function() {
        $(this).dialog("close");
      }
    }
  });
}

function edit() {
  window.location.href = "<c:url value='/vtwo/home?edit=yes'/>";
}

function cancelEdit() {
  window.location.href = "<c:url value='/vtwo/home?edit=no'/>";
}

var changePhoto=0;
function changeScrollPhoto() {
  var photoSliderWidth = $("#body").width();
  var photoSliderHeight = photoSliderWidth * 0.2;
  if (photoSliderHeight<120) {
	photoSliderHeight = 120;
  }
  if (changePhoto==0) {
	  $("#photoSlider").nbspSlider({
		  widths: photoSliderWidth,
		  heights : photoSliderHeight,
		  autoplay : 1,  //0,1
		  delays : 6000, 
		  effect : "vertical",
		  speeds : 800,
		  prevId : 'prevBtns',
		  nextId : 'nextBtns',
		  altOpa : 0.5,
		  altBgColor : '#ccc',
		  altHeight : '40px',
		  altShow : 1,
		  altFontColor : 'black',
		  btnFontColor :'red',
		  btnBorderColor : '#ccc',
		  btnBgColor : 'yellow',
		  btnActBgColor : 'green',
		  starEndNoEff : 0,
		  preNexBtnShow : 1,
		  numBtnSty : "square",
		  numBtnShow : "1",
		  starEndNoEff: 1
	  });
	  changePhoto = 1;
   } else {
	   $("#photoSlider").nbspSlider({widths: photoSliderWidth, heights : photoSliderHeight});
   }
}

$(document).ready(function () {
  $(":input[placeholder]").placeholder();
  
  $(window).resize(function() {
	  scaleWidth();
  });

  $(window).bind('orientationchange', function(){window.location.href = window.location.href;});
});
//-->
</script>
