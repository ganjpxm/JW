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
  <script type="text/JavaScript" src="<c:url value='/resources/js/jquery/ui/jquery.shapeshift.min.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.form.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.masonry.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/i18n/i18n_${lang}.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.iframe.transport.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.fileupload.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jwplayer.${jsSuffix}'/>"></script>
  
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxcore.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxinput.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxradiobutton.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxbuttons.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxdropdownbutton.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/jqwidgets/jqxnavigationbar.${jsSuffix}'/>"></script>
  
  <script type="text/javascript" src="<c:url value='/resources/js/jp.${jsSuffix}'/>"></script>
  
  <style>
  	.ui-button-text {padding: .1em 1em;font-size:16px;}
  </style>
</head>
<body class="jpw2">
<%@ include file="/pages/common/vtwo/inc/header.jsp" %>
<div id="body">
  <div class="content" style="text-align:center;padding:20px;font-size:20px;min-height:500px;">
    <div id="searchTag" class="item">
      <input id="searchTagTxt" type="text" value="${tag}" title="<fmt:message key='search.placeholder.tag'/>" placeholder="<fmt:message key='search.placeholder.tag'/>" style="width:85%;height:28px;"/>
      <button onclick="javascript:searchVideosByTag();" title="<fmt:message key='button.search'/>" class="searchBtn" style="height:35px;margin-top:-10px;">&nbsp;</button>
      <div>
        <div id="dycontainerProperty">
          <input type="checkbox" id="drag" /><label for="drag"><fmt:message key='common.field.drag'/></label>
  		  <input type="checkbox" id="tag" /><label for="tag"><fmt:message key='common.field.tag'/></label>
  		  <c:if test="${user!=null}">
  		    <div style="float:right">
		      <div style="float:left">
		      <button id="addVideo" onclick="javascript:addVideo();"><fmt:message key="button.add"/></button>
			  </div>
			  <div style="float:left">
  		      <input type="checkbox" id="showEditVideo" onclick="javascript:displayEdit();"/><label for="showEditVideo"><fmt:message key="button.edit"/></label>
  		      </div>
  		      <div style="float:left;margin-left:10px;">
		      <label class="upload_btn fileinput-button">
		        <span style="font-weight:bold;font-size:15p;"><fmt:message key="button.upload"/></span>
		        <form method="post"><input type="hidden" name="folder" value="video"/><input type="hidden" name="save" value="CmVideo"/>
		        <input id="uploadVideo" type="file" name="file" data-url="<c:url value='/uploadFile'/>" multiple/></form>
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
          <div style="text-align:left;margin-left:-20px;">${videoCategoryCheckboxHtml}</div>
        </div>
	    <div id="dycontainer" class="dycontainer">
		    <c:forEach var="map" items="${videoMaps}" varStatus="status">
		        <div class="object" style="background-color:white;display:block;">
		        <!-- 
		          <a href="javascript:showVideo('${map.url}', '${map.title}');" style="color:#333; text-decoration:none; font-size: 18px; font-family:"MuseoSlabRegular",serif;line-height: 1.27;">
			          <c:if test="${map.thumbUrl!=''}">
					    <img src="<c:url value='${map.thumbUrl}'/>" width="100%" style="margin-bottom:0px;"/><br/>
				      </c:if>
				      <div style="padding:10px;">
				          ${map.title}<br/>
				          <div style="font-size:16px;color:#777;text-align:left;" class="summary">${map.summary}</div>
				      </div>
			      </a>
			    -->
			      <div id="${map.videoId}" class='mediaplayer'></div>
			      <c:if test="${map.title!=''}">
					 <div style="padding:10px;font-size:16px;">${map.title}</div>
				  </c:if>
				  <script type="text/javascript">
					  jwplayer('${map.videoId}').setup({
					    'flashplayer': "<c:url value='/resources/upload/video/jwplayer.flash.swf'/>",
					    'id': 'playerID',
					    'width': '100%',
					    'height': '200',
					    'file': '<c:url value='${map.url}'/>',
					    'controlbar': 'bottom'
					  });
				  </script>
			   	  <c:if test="${user!=null}">
			   	    <div class="editVideoBtns" style="float:right;display:none;">
			   	      <button class="gradient-green-btn" onclick="javascript:alert('${map.url}');"><fmt:message key="common.field.url"/></button>
			   	  	  <button class="gradient-green-btn" onclick="javascript:editVideo('${map.videoId}');"><fmt:message key="button.edit"/></button>
			   	  	  <button class="gradient-green-btn" onclick="javascript:confirmDeleteVideo('${map.videoId}','${map.videoName}','${map.title}');">
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
<div id="dialog-confirm-delete-video" title="" style="display:none;">
  <p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
  The item will be permanently deleted and cannot be recovered. Are you sure?</p>
</div>
<div id="dialog-video" title="" style="display:none;">
  <div style="text-align:center;">
  </div>
</div>
<%@ include file="/pages/common/vtwo/inc/footer.jsp" %>
</body>
</html>
<script>
<!--
var edit = "${edit}";
function addVideo() {
  window.location.href = "<c:url value='/cmVideoJqmAdd?edit='/>" + edit + "&from=/vtwo/video&pageNo=1&pageSize=30";
}
function editVideo(videoId) {
  window.location.href = "<c:url value='/cmVideoJqmEdit/'/>" + videoId + "?edit=" + edit + "&from=/vtwo/video&pageNo=${pageNo}&pageSize=${pageSize}&tag=" + $("#searchTagTxt").val();
}

var $editVideoBtns = $("#dycontainer .editVideoBtns");
var $showEditVideo = $('#showEditVideo');
function displayEdit() {
  //window.location.href = "<c:url value='/vtwo/video?edit=yes'/>";
  if ($showEditVideo.is(':checked')) {
	$editVideoBtns.show();  
	shapeshift();
	edit = "yes";
  } else {
	$editVideoBtns.hide();
	shapeshift();
	edit = "no";
  }
}

if (edit=="yes") {
  $editVideoBtns.show();
  $showEditVideo.attr('checked','checked');
}

function confirmDeleteVideo(videoId,videoName,title) {
  $("#dialog-confirm-delete-video").attr("title", I18N.msg_delete_video_title);
  $("#dialog-confirm-delete-video p").html(String.format(I18N.msg_delete_content, title));
  $("#dialog-confirm-delete-video").dialog({
    resizable: false,
    height: 240,
    modal: true,
    buttons: {
      "<fmt:message key='button.delete'/>": function() {
    	  $(this).dialog("close");
    	  window.location.href="<c:url value='/deleteCmVideo/'/>" + videoId + "?tag=" + $("#searchTagTxt").val() + "&videoName=" + videoName +
    	  	"&from=/vtwo/video&edit=${edit}&pageNo=${pageNo}&pageSize=${pageSize}";
      },
      "<fmt:message key='button.cancel'/>": function() {
        $(this).dialog("close");
      }
    }
  });
}
function searchVideosByTag() {
  window.location.href = "<c:url value='/vtwo/video?edit=${edit}&tag='/>" + $("#searchTagTxt").val() + "&pageNo=1&pageSize=${pageSize}";
}
function toManageTags() {
	window.open("<c:url value='/bmParam?menuId=4028d981396133f20139615d35420185'/>", "_blank");	
}

function toManageCategories() {
	window.open("<c:url value='/cmCategory?menuId=4028d981396d930201396dbeda2101ae'/>", "_blank");	
}

var $dycontainer = $("#dycontainer");
var shapeshift = function() {$dycontainer.shapeshift({enableDrag: false, paddingY: 3});}

function toPage(pageNo) {
  window.location.href = "<c:url value='/vtwo/video?edit=${edit}&tag='/>" + $("#searchTagTxt").val() + "&pageNo=" + pageNo + "&pageSize=${pageSize}";
}

var viewport_width = $(window).width();
var viewport_height = $(window).height();
var $videoDialog = $("#dialog-video");
function showVideo(url,title) {
  $videoDialog.attr("title", title);
  $videoDialog.dialog({
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
  shapeshift();
  if (!jp.isIE) {
	  setTimeout(shapeshift,1000);
	  setTimeout(shapeshift,5000); 
  }
  $('#searchTagTxt').focus();
  $("#searchTagTxt").keypress(function(event) { if ( event.keyCode == 13 ) searchVideosByTag(); });
  
  $("#dycontainerProperty" ).buttonset();

  $("#drag").click(function() {
	  if ($('#drag').is(':checked')) {
		  $dycontainer.shapeshift({enableDrag: true});
	  } else {
		  shapeshift();
	  }
  });
  
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
  
  $("#uploadVideo").fileupload({
	dataType: 'json',
	done: function (e, data) {
	  if (data.result.result == "success") {
		window.location.href = "<c:url value='/vtwo/video?edit=${edit}&pageNo=1&pageSize=${pageSize}'/>";
	  } else {
	    alert(data.result.result);
	  }
	}
  });
  
});


//-->
</script>
