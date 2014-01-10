<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="author" content="GanJianping"/>
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
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.form.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.masonry.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/i18n/i18n_${lang}.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.iframe.transport.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.fileupload.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.masonry.min.${jsSuffix}'/>"></script>
  
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxcore.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxinput.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxradiobutton.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxbuttons.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxdropdownbutton.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxnavigationbar.${jsSuffix}'/>"></script>
  
  <script type="text/javascript" src="<c:url value='/resources/js/jp.${jsSuffix}'/>"></script>
  
  <style>
  	.ui-button-text {padding: .1em 1em;font-size:16px;}
  	#dycontainer {margin-bottom: 20px; border-radius: 5px; clear: both; 
  		-webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;}
  	.box {margin: 5px; line-height: 1.4em; float: left; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;}
  </style>
</head>
<body onload="scaleWidth();" class="jpw2">
<%@ include file="/pages/common/vtwo/inc/header.jsp" %>
<div id="body">
  <div class="content" style="text-align:center;padding:20px;font-size:20px;min-height:500px;">
    <div id="searchTag" class="item">
      <input id="searchTagTxt" type="text" value="${tag}" title="<fmt:message key='search.placeholder.tag'/>" placeholder="<fmt:message key='search.placeholder.tag'/>" style="width:80%;height:28px;"/>
      <button onclick="javascript:searchFilesByTag();" title="<fmt:message key='button.search'/>" class="searchBtn" style="height:35px;margin-top:-10px;min-width:35px;">&nbsp;</button>
      <div>
        <div id="dycontainerProperty">
  		  <input type="checkbox" id="tag" /><label for="tag"><fmt:message key='common.field.tag'/></label>
  		  <c:if test="${user!=null}">
  		    <div style="float:right">
		      <div style="float:left">
		      <button id="addFile" onclick="javascript:addFile();"><fmt:message key="button.add"/></button>
			  </div>
			  <div style="float:left">
  		      <input type="checkbox" id="showEditFile" onclick="javascript:displayEdit();"/><label for="showEditFile"><fmt:message key="button.edit"/></label>
  		      </div>
  		      <div style="float:left;margin-left:10px;">
		      <label class="upload_btn fileinput-button">
		        <span style="font-weight:bold;font-size:15p;"><fmt:message key="button.upload"/></span>
		        <form method="post"><input type="hidden" name="folder" value="file"/><input type="hidden" name="save" value="CmFile"/>
		        <input id="uploadFile" type="file" name="file" data-url="<c:url value='/uploadFileToRackspaceCloudFiles'/>" multiple/></form>
		      </label>
		      </div>
  		    </div>
  		    <div class="clearfix"></div>
		  </c:if>
        </div>
        <div id="tags" style="display:none;">
          <div style="text-align:left;background-color:#34c38f;-webkit-border-radius:3px;border-radius:3px;padding:5px;color:white;margin-left:10px;"><fmt:message key="common.field.pop.tag"/></div>
          <c:if test="${user!=null}">
            <input type="checkbox" id="specialTag" onclick="javascript:toManageTags();" style="margin-left:10px;"/><label for="specialTag"><fmt:message key="button.edit"/></label>
          </c:if>
          <div style="text-align:left;padding:10px;margin-left:10px;">
            <c:forEach var="bmParam" items="${tagBmParams}">
              <input type="checkbox" id="${bmParam.paramId}" name="categories" value="${bmParam.paramName}"/><label for="${bmParam.paramId}">${bmParam.paramName}</label>
            </c:forEach>
		  </div>
          <div style="text-align:left;background-color:#34c38f;-webkit-border-radius:3px;border-radius:3px;padding:5px;color:white;margin-left:10px;"><fmt:message key="common.field.categories"/></div>
          <c:if test="${user!=null}">
              <input type="checkbox" id="editCategories" onclick="javascript:toManageCategories();"/><label for="editCategories"><fmt:message key="button.edit"/></label>
          </c:if>
          <div style="text-align:left;margin-left:-20px;">${fileCategoryCheckboxHtml}</div>
        </div>
	    <div id="dycontainer" class="clearfix masonry" style="width:99%">
		    <c:forEach var="map" items="${fileMaps}" varStatus="status">
		        <div title="${map.tag}" class="box" style="background-color:white;display:block;">
		          
		          <a target="_blank"
		           <c:choose>
	 				  <c:when test="${fn:startsWith(map.url, 'http') || fn:startsWith(map.url, '/')}">
						href="<c:url value='${map.url}'/>"
					  </c:when>
	 				  <c:otherwise>
	 				    <c:if test="${map.url!=''}">
					  	    href="<c:url value='${FileUrl}/${map.url}'/>"
				        </c:if>
					  </c:otherwise>
				    </c:choose>
		            style="color:#333; text-decoration:none; font-size: 18px; font-family:"MuseoSlabRegular",serif;line-height: 1.27;">
			        <!-- fileId,fileName,title,author,imageUrl,tag,updateDate -->
			        <div style="padding:10px;">
			          ${map.title}
			        </div>
			      </a>
			      
			   	  <c:if test="${user!=null}">
			   	    <div class="editFileBtns" style="float:right;display:none;">
			   	  	  <button class="gradient-green-btn" onclick="javascript:editFile('${map.fileId}');"><fmt:message key="button.edit"/></button>
			   	  	  <button class="gradient-green-btn" onclick="javascript:confirmDeleteFile('${map.fileId}','${map.fileName}','${map.title}');">
			   	  	    <fmt:message key="button.delete"/></button>
			   	  	</div>
			   	  </c:if>
			   	</div>  		
		    </c:forEach>
	  	</div>
	  	<c:if test="${totalPages>1}">
	  	<c:forEach var="i" begin="1" end="${totalPages}" step="1">
		  <button onclick="javascript:toPage(${i});" class="gradient-green-btn" style="padding:5px 15px;">${i}<c:if test="${pageNo==i}">-${pageSize}</c:if></button>
		</c:forEach>
		</c:if>
	</div>  	
  </div>
</div>
<div class="clearfix"></div>
<div id="dialog-confirm-delete-file" title="" style="display:none;">
  <p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
  The item will be permanently deleted and cannot be recovered. Are you sure?</p>
</div>
<div id="dialog-file" title="" style="display:none;">
  <div style="text-align:center;">
  </div>
</div>
<form id="downloadForm" action="<c:url value='/vtwo/download'/>" method="post">
  <input id="edit" name="edit" type="hidden" value="${edit}"/>
  <input id="pageNo" name="pageNo" type="hidden" value="${pageNo}"/>
  <input id="pageSize" name="pageSize" type="hidden" value="${pageSize}"/>
  <input id="downloadTag" name="tag" type="hidden" value="${searchTagTxt}"/>
</form>
<%@ include file="/pages/common/vtwo/inc/footer.jsp" %>
</body>
</html>
<script>
<!--
var width;
function scaleWidth() {
  var winWidth = $(window).width();
  var containerWidth = $("#dycontainer").width();
  if (winWidth>1366) {
	  containerWidth=containerWidth-60;
	  width = containerWidth/5;
  }	else if (winWidth>1280) {
	  containerWidth=containerWidth-45;
	  width = containerWidth/4;
  }	else if (winWidth>=1000) {
	  containerWidth = containerWidth-30;
	  width = containerWidth/3;
  }	else if (winWidth>=600) {
	  containerWidth=containerWidth-20;
	  width = containerWidth/2;
  } else {
	  containerWidth=containerWidth-10;
	  width = containerWidth;
  }
  $("#dycontainer div.box").css("width", width);	
  $('#dycontainer').masonry({itemSelector: '.box'});
}
var edit = "${edit}";
function addFile() {
  window.location.href = "<c:url value='/cmFileJqmAdd?edit='/>" + edit + "&from=/vtwo/download&pageNo=1&pageSize=30";
}
function editFile(fileId) {
  window.location.href = "<c:url value='/cmFileJqmEdit/'/>" + fileId + "?edit=" + edit + "&from=/vtwo/download&pageNo=${pageNo}&pageSize=${pageSize}";//&tag= + $("#searchTagTxt").val();
}

var $editFileBtns = $("#dycontainer .editFileBtns");
var $showEditFile = $('#showEditFile');
function displayEdit() {
  //window.location.href = "<c:url value='/vtwo/download?edit=yes'/>";
  if ($showEditFile.is(':checked')) {
	$editFileBtns.show();  
	scaleWidth();
	edit = "yes";
  } else {
	$editFileBtns.hide();
	scaleWidth();
	edit = "no";
  }
}

if (edit=="yes") {
  $editFileBtns.show();
  $showEditFile.attr('checked','checked');
}

function confirmDeleteFile(fileId,fileName,title) {
  $("#dialog-confirm-delete-file").attr("title", I18N.msg_delete_file_title);
  $("#dialog-confirm-delete-file p").html(String.format(I18N.msg_delete_content, title));
  $("#dialog-confirm-delete-file").dialog({
    resizable: false,
    height: 240,
    modal: true,
    buttons: {
      "<fmt:message key='button.delete'/>": function() {
    	  $(this).dialog("close");
    	  window.location.href="<c:url value='/deleteCmFile/'/>" + fileId + "?tag=" + $("#searchTagTxt").val() + "&fileName=" + fileName +
    	  	"&from=/vtwo/download&edit=${edit}&pageNo=${pageNo}&pageSize=${pageSize}";
      },
      "<fmt:message key='button.cancel'/>": function() {
        $(this).dialog("close");
      }
    }
  });
}
function searchFilesByTag() {
  $("#downloadTag").val($("#searchTagTxt").val());
  $("#pageNo").val("1");
  $("#downloadForm").submit(); 
  //window.location.href = "<c:url value='/vtwo/download?edit=${edit}&tag='/>" + $("#searchTagTxt").val() + "&pageNo=1&pageSize=${pageSize}";
}
function toManageTags() {
	window.open("<c:url value='/bmParam?menuId=4028d981396133f20139615d35420185'/>", "_blank");	
}

function toManageCategories() {
	window.open("<c:url value='/cmCategory?menuId=4028d981396d930201396dbeda2101ae'/>", "_blank");	
}

function toPage(pageNo) {
  $("#downloadTag").val($("#searchTagTxt").val());
  $("#pageNo").val(pageNo);
  $("#downloadForm").submit(); 	
  //window.location.href = "<c:url value='/vtwo/download?edit=${edit}&tag='/>" + $("#searchTagTxt").val() + "&pageNo=" + pageNo + "&pageSize=${pageSize}";
}

var viewport_width = $(window).width();
var viewport_height = $(window).height();
var $fileDialog = $("#dialog-file");
function showFile(url,title) {
  $fileDialog.attr("title", title);
  $fileDialog.dialog({
	resizable: false,
	height: viewport_height-40,
	width: viewport_width-40,
	modal: true,
	buttons: {
	  "<fmt:message key='button.close'/>": function() {
	     $(this).dialog("close");
	  }
	}
  });
}
$(document).ready(function () {
  $(window).resize(function() { scaleWidth(); });

  $('#searchTagTxt').focus();
  $("#searchTagTxt").keypress(function(event) { if ( event.keyCode == 13 ) searchFilesByTag(); });
  
  $("#dycontainerProperty" ).buttonset();

  $("#tag").click(function() {
	if ($('#tag').is(':checked')) {
	  $("#tags").show();
	} else {
	  $("#tags").hide();
	}
  });
  
  $("#tags").buttonset();
  
  $("#tags input").click(function() {
    var id = $(this).attr('id');
	if (id.indexOf("expand")!=-1) {
		if ($(this).is(':checked')) {
			$("#tags ." + id).show();
		} else {
			$("#tags ." + id).hide();
		} 
	} else {
		$("#searchTagTxt").val(jp.getCheckValues("categories"));
	}
  });
  
  $("#uploadFile").fileupload({
	dataType: 'json',
	done: function (e, data) {
	  if (data.result.result == "success") {
		window.location.href = "<c:url value='/vtwo/download?edit=${edit}&pageNo=1&pageSize=${pageSize}'/>";
	  } else {
	    alert(data.result.result);
	  }
	}
  });
  
});


//-->
</script>
