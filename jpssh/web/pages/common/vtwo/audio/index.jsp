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
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/thirdparty/MagnificPopup/magnific-popup.css'/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jpw.jq.${cssSuffix}'/>">
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jplayer.blue.monday.${cssSuffix}'/>">
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jquery.ui.${cssSuffix}'/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/thirdparty/jqwidgets/styles/jqx.base.${cssSuffix}'/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/default/thirdparty/bootstrap.css'/>" />
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jpw.${cssSuffix}'/>"/>
  <!--[if IE]><link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/default/ie.${cssSuffix}'/>"><![endif]-->
  
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-1.8.2.min.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/ui/jquery.ui.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.cookie.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.nbspSlider.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.jplayer.min.${jsSuffix}'/>"></script>
  <script type="text/JavaScript" src="<c:url value='/resources/js/jquery/ui/jquery.ui.touch-punch.min.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.form.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.masonry.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/i18n/i18n_${lang}.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.iframe.transport.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.fileupload.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.masonry.min.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/MagnificPopup/jquery.magnific-popup.${jsSuffix}'/>"></script>
  
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
  <div id="jplayerDiv" style="margin:0 auto 10px;display:none;">  
		  <div id="jquery_jplayer_1" class="jp-jplayer"></div>
		  <div id="jp_container_1" class="jp-audio" style="background-color:white;min-width:420px; width:95%; margin:0 auto;border:0px;-webkit-border-radius:3px;border-radius:3px;">
			<div class="jp-type-single">
			  <div class="jp-gui jp-interface" style="width:420px;margin:0 auto;">
				<ul class="jp-controls">
				  <li><a href="javascript:;" class="jp-play" tabindex="1">play</a></li>
				  <li><a href="javascript:;" class="jp-pause" tabindex="1">pause</a></li>
				  <li><a href="javascript:;" class="jp-stop" tabindex="1">stop</a></li>
				  <li><a href="javascript:;" class="jp-mute" tabindex="1" title="mute">mute</a></li>
				  <li><a href="javascript:;" class="jp-unmute" tabindex="1" title="unmute">unmute</a></li>
				  <li><a href="javascript:;" class="jp-volume-max" tabindex="1" title="max volume">max volume</a></li>
			    </ul>
				<div class="jp-progress">
				  <div class="jp-seek-bar">
					<div class="jp-play-bar"></div>
				  </div>
				</div>
				<div class="jp-volume-bar">
				  <div class="jp-volume-bar-value"></div>
				</div>
				<div class="jp-time-holder">
				  <div class="jp-current-time"></div>
				  <div class="jp-duration"></div>
				  <ul class="jp-toggles">
					<li><a href="javascript:;" class="jp-repeat" tabindex="1" title="repeat">repeat</a></li>
					<li><a href="javascript:;" class="jp-repeat-off" tabindex="1" title="repeat off">repeat off</a></li>
				  </ul>
				</div>
			  </div>
			  <div class="jp-title" style="background-color:#34c38f;color:white;">
				<ul>
				  <a href="javascript:displayLyric();" style="color:white;text-decoration:none;"><li id="jp-title">${defaultCmAudio.title}</li></a>
				</ul>
			  </div>
			  <div class="jp-no-solution">
				<span>Update Required</span>
				To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.
			  </div>
			</div>
		  </div>
	</div>
	<div id="lyric" style="display:none;margin-bottom:10px;line-height:30px;font-size:14px;word-wrap: break-word; word-break: normal; text-align:left; "></div>
	<div id="searchTag" class="item">
	   <input id="searchTagTxt" type="text" value="${tag}" title="<fmt:message key='search.placeholder.tag'/>" placeholder="<fmt:message key='search.placeholder.tag'/>" style="width:80%;height:28px;"/>
	   <button onclick="javascript:searchAudiosByTag();" title="<fmt:message key='button.search'/>" class="searchBtn" style="height:35px;margin-top:-10px;min-width:35px;">&nbsp;</button>
	</div>  
    <div id="dycontainerProperty">
          <input type="checkbox" id="lyricCheck" /><label for="lyricCheck"><fmt:message key='common.field.lyric'/></label>
          <input type="checkbox" id="remarkCheck" /><label for="remarkCheck"><fmt:message key='common.field.remark'/></label>
  		  <input type="checkbox" id="tag" /><label for="tag"><fmt:message key='common.field.tag'/></label>
  		  <c:if test="${user!=null}">
  		    <div style="float:right">
		      <div style="float:left;margin-right:5px;">
		      <button id="addAudio" onclick="javascript:addAudio();"><fmt:message key="button.add"/></button>
			  </div>
			  <div style="float:left">
  		      <input type="checkbox" id="showEditAudio" onclick="javascript:displayEdit();"/><label for="showEditAudio"><fmt:message key="button.edit"/></label>
  		      </div>
  		      <!--  
  		      <div style="float:left;margin-left:10px;">
		      <label class="upload_btn fileinput-button">
		        <span style="font-weight:bold;font-size:15p;"><fmt:message key="button.upload"/></span>
		        <form method="post"><input type="hidden" name="folder" value="audio"/><input type="hidden" name="save" value="CmAudio"/>
		        <input id="uploadAudio" type="file" name="file" data-url="<c:url value='/uploadFile'/>" multiple/></form>
		      </label>
		      </div>
		      -->
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
          <div style="text-align:left;margin-left:-20px;">${audioCategoryCheckboxHtml}</div>
    </div>
	<div id="dycontainer" class="clearfix masonry" style="width:99%">
		    <c:forEach var="map" items="${audioMaps}" varStatus="status">
		        <div class="box" style="background-color:white;display:block;">
				  <!--  
				  <audio controls>
 					<source src="horse.ogg" type="audio/ogg">
  					<source src="<c:url value='${map.url}'/>" type="audio/mp3">
  					Your browser does not support the audio element.
  				  </audio>
  				  -->
  				<c:choose>
 				  <c:when test="${fn:startsWith(map.thumbUrl, 'http') || fn:startsWith(map.thumbUrl, '/')}">
					<a href="<c:url value='${map.thumbUrl}'/>">
				  	  <img src="<c:url value='${map.thumbUrl}'/>" width="100%" style="margin-bottom:0px;"/><br/>
			        </a>
				  </c:when>
 				  <c:otherwise>
 				    <c:if test="${map.thumbUrl!=''}">
                      <a href="<c:url value='${ImageUrl}/${map.thumbUrl}'/>">
				  	    <img src="<c:url value='${ImageUrl}/${map.thumbUrl}'/>" width="100%" style="margin-bottom:0px;"/><br/>
			          </a>
			        </c:if>
				  </c:otherwise>
				</c:choose>
			      <div style="padding:10px;font-size:16px;font-weight:bold; color:#333;">
			        ${map.title}<br/>
			        <c:set var="audioNameUrlArrs" value="${fn:split(map.url, ';')}" />
		          	<c:forEach var="audioNameUrlArr" items="${audioNameUrlArrs}">
		          	   <c:set var="audioNameUrl" value="${fn:split(audioNameUrlArr, '+')}" />
		          	   <button title="${audioNameUrl[1]}"
		          	   	style="background-color:white;border:1px;text-decoration:underline;font-size:16px;font-weight:bold; color:#333;" 
			          	onclick="playAudio(&#34;<c:url value="${audioNameUrl[1]}"/>&#34;, &#34;${audioNameUrl[0]}&#34;,&#34;${map.description}&#34;);">${audioNameUrl[0]}</button>
			          	  <button title="${audioNameUrl[1]}" style="border:0;magin-bottom:15px;padding:1px 5px;background-color:white;background-image:url('<c:url value='/resources/style/default/image/jp/icon/download_icon.png'/>')"
			          	    onclick="download(&#34;<c:url value="${audioNameUrl[1]}"/>&#34;);">&nbsp;&nbsp;&nbsp;&nbsp;</button>
			          	<br/>
		          	</c:forEach>
			      </div>
			      <div style="float:right;margin:5px;">
				       <button id="lyricBtn"  style="display:none;" class="gradient-white-btn" onclick="javascript:showDescription(&#34;${map.description}&#34;);"><fmt:message key="common.field.lyric"/></button>
				       <button id="remarkBtn" style="display:none;" class="gradient-white-btn" onclick="javascript:showRemark(&#34;${map.remark}&#34;);"><fmt:message key="common.field.remark"/></button>
			      </div>
			   	  <c:if test="${user!=null}">
			   	    <div class="editAudioBtns" style="float:right;display:none;margin:5px;">
			   	  	  <button class="gradient-green-btn" onclick="javascript:editAudio('${map.audioId}');"><fmt:message key="button.edit"/></button>
			   	  	  <button class="gradient-green-btn" onclick="javascript:confirmDeleteAudio(&#34;${map.audioId}&#34;,&#34;${map.audioName}&#34;,&#34;${map.title}&#34;);">
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
<div class="clearfix"></div>
<div id="dialog-confirm-delete-audio" title="" style="display:none;">
  <p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
  The item will be permanently deleted and cannot be recovered. Are you sure?</p>
</div>
<div id="dialog-audio-description" title="<fmt:message key='common.field.lyric'/>" style="display:none;">
  <div id="dialog-audio-description-content" style="text-align:center;margin-bottom:10px;line-height:30px;word-wrap: break-word; word-break: normal;">
  </div>
</div>
<div id="dialog-audio-remark" title="<fmt:message key='common.field.remark'/>" style="display:none;">
  <div id="dialog-audio-remark-content" style="text-align:center;margin-bottom:10px;line-height:30px;text-align:left;word-wrap: break-word; word-break: normal;">
  </div>
</div>
<form id="audioForm" action="<c:url value='/vtwo/audio'/>" method="post">
  <input id="edit" name="edit" type="hidden" value="${edit}"/>
  <input id="pageNo" name="pageNo" type="hidden" value="${pageNo}"/>
  <input id="pageSize" name="pageSize" type="hidden" value="${pageSize}"/>
  <input id="audioTag" name="tag" type="hidden" value="${searchTagTxt}"/>
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
function addAudio() {
  window.location.href = "<c:url value='/cmAudioJqmAdd?edit='/>" + edit + "&from=/vtwo/audio&pageNo=1&pageSize=30";
}
function editAudio(audioId) {
  window.location.href = "<c:url value='/cmAudioJqmEdit/'/>" + audioId + "?edit=" + edit + "&from=/vtwo/audio&pageNo=${pageNo}&pageSize=${pageSize}";// &tag= + $("#searchTagTxt").val()
}

var $editAudioBtns = $("#dycontainer .editAudioBtns");
var $showEditAudio = $('#showEditAudio');
function displayEdit() {
  //window.location.href = "<c:url value='/vtwo/audio?edit=yes'/>";
  if ($showEditAudio.is(':checked')) {
	$editAudioBtns.show();  
	scaleWidth();
	edit = "yes";
  } else {
	$editAudioBtns.hide();
	scaleWidth();
	edit = "no";
  }
}

var display = "hide";
var $lyric = $("#lyric");
function displayLyric() {
  if (display=="hide") {
	$lyric.show();  
	display = "show";
  } else {
	$lyric.hide();
	display = "hide";
  }
}

function confirmDeleteAudio(audioId,audioName,title) {
  $("#dialog-confirm-delete-audio").attr("title", I18N.msg_delete_audio_title);
  $("#dialog-confirm-delete-audio p").html(String.format(I18N.msg_delete_content, title));
  $("#dialog-confirm-delete-audio").dialog({
    resizable: false,
    height: 240,
    modal: true,
    buttons: {
      "<fmt:message key='button.delete'/>": function() {
    	  $(this).dialog("close");
    	  window.location.href="<c:url value='/deleteCmAudio/'/>" + audioId + "?tag=" + $("#searchTagTxt").val() + "&audioName=" + audioName +
    	  	"&from=/vtwo/audio&edit=${edit}&pageNo=${pageNo}&pageSize=${pageSize}";
      },
      "<fmt:message key='button.cancel'/>": function() {
        $(this).dialog("close");
      }
    }
  });
}
function searchAudiosByTag() {
  $("#audioTag").val($("#searchTagTxt").val());
  $("#pageNo").val("1");
  $("#audioForm").submit(); 
  //window.location.href = "<c:url value='/vtwo/audio?edit=${edit}&tag='/>" + $("#searchTagTxt").val() + "&pageNo=1&pageSize=${pageSize}";
}
function toManageTags() {
	window.open("<c:url value='/bmParam?menuId=4028d981396133f20139615d35420185'/>", "_blank");	
}

function toManageCategories() {
	window.open("<c:url value='/cmCategory?menuId=4028d981396d930201396dbeda2101ae'/>", "_blank");	
}

function toPage(pageNo) {
  $("#audioTag").val($("#searchTagTxt").val());
  $("#pageNo").val(pageNo);
  $("#audioForm").submit(); 
  //window.location.href = "<c:url value='/vtwo/audio?edit=${edit}&tag='/>" + $("#searchTagTxt").val() + "&pageNo=" + pageNo + "&pageSize=${pageSize}";
}

var viewport_width = $(window).width();
var viewport_height = $(window).height();

var $jpTitle = $("#jp-title");
function playAudio(url,title,content) {
  $("#jplayerDiv").show();
  if(url.indexOf("/")!=0 && url.indexOf("h")!=0) {
	  url = "${AudioUrl}/" + url;
  }
  $jpTitle.html(title);
  $lyric.html(content);
  $jqPlayer.jPlayer("setMedia", {mp3:url}).jPlayer("play");
}

function download(url) {
  if(url.indexOf("/")!=0 && url.indexOf("h")!=0) {
	  url = "${AudioUrl}/" + url;
  }
  window.open(url, "_blank");	
}
var $audioDescriptionDialog = $("#dialog-audio-description");
var $audioDescriptionDialogContent = $("#dialog-audio-description-content");
var $audioRemarkDialog = $("#dialog-audio-remark");
var $audioRemarkDialogContent = $("#dialog-audio-remark-content");

function showDescription(content) {
  $audioDescriptionDialogContent.html(content);
  $audioDescriptionDialog.dialog({
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

function showRemark(content) {
  $audioRemarkDialogContent.html(content);
  $audioRemarkDialog.dialog({
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

var $jqPlayer = $("#jquery_jplayer_1");
$(document).ready(function () {
  $('#searchTagTxt').focus();
  $("#searchTagTxt").keypress(function(event) { if ( event.keyCode == 13 ) searchAudiosByTag(); });
  
  $("#dycontainerProperty" ).buttonset();

  $("#tag").click(function() {
	if ($('#tag').is(':checked')) {
	  $("#tags").show();
	} else {
	  $("#tags").hide();
	}
  });
  
  
  $("#lyricCheck").click(function() {
	if ($('#lyricCheck').is(':checked')) {
	  $("#lyricBtn").show();
	  scaleWidth();
	} else {
	  $("#lyricBtn").hide();
	  scaleWidth();
	}
  });
  
  $("#remarkCheck").click(function() {
	if ($('#remarkCheck').is(':checked')) {
	  $("#remarkBtn").show();
	} else {
	  $("#remarkBtn").hide();
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
  
  $("#uploadAudio").fileupload({
	dataType: 'json',
	type: 'post',
	done: function (e, data) {
	  if (data.result.result == "success") {
		window.location.href = "<c:url value='/vtwo/audio?edit=${edit}&pageNo=1&pageSize=${pageSize}'/>";
	  } else {
	    alert(data.result.result);
	  }
	}
  });
  
  $jqPlayer.jPlayer({
	ready: function () {
	  $(this).jPlayer("setMedia", {
		mp3:"<c:url value='${defaultCmAudio.url}'/>"
	  });
	},
	swfPath: "js",
	supplied: "mp3",
	wmode: "window"
  });
  
  $('#dycontainer').magnificPopup({
    delegate: 'a', // child items selector, by clicking on it popup will open
    type: 'image',
    gallery: {
      enabled: true
    }
  });
  
  //$lyric.html("${defaultCmAudio.description}");
});


//-->
</script>
