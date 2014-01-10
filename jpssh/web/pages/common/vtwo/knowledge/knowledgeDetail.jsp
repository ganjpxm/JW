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
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/thirdparty/SCEditor/minified/jquery.sceditor.default.min.css'/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jpw.jq.${cssSuffix}'/>">
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/${jpTheme}/jquery.ui.${cssSuffix}'/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/thirdparty/jqwidgets/styles/jqx.base.${cssSuffix}'/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/style/default/thirdparty/bootstrap.css'/>" />
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/thirdparty/MagnificPopup/magnific-popup.css'/>"/>
  <link rel="stylesheet" type="text/css" href="<c:url value='/resources/thirdparty/jqwidgets/styles/jqx.base.${cssSuffix}'/>"/>
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
  <script type="text/javascript" src="<c:url value='/resources/thirdparty/MagnificPopup/jquery.magnific-popup.${jsSuffix}'/>"></script>

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
  	#dycontainer {padding: 5px; margin-bottom: 20px; border-radius: 5px; clear: both; 
  		-webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;}
  	.box {margin: 5px; line-height: 1.4em; float: left; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;}
  	div {min-height:0;}
  </style>
</head>
<body class="jpw2">
<%@ include file="/pages/common/vtwo/inc/header.jsp" %>
<div id="body">
  <div class="content" style="text-align:center;padding:10px;font-size:20px;min-height:500px;background-color:#fff;width:90%;">
	<div id="articleHeader" style="line-height:30px;">
		<span style="font-size:25px;font-weight:bold;text-align:center;">${cmArticle.title}</span>
		<a href="javascript:back();"><img style="width:32px;float:right;" src="<c:url value='/resources/style/default/image/jp/icon/go-back-icon.png'/>"/></a>
  		<c:if test="${user!=null}">
			<button class="gradient-green-btn" style="float:right;margin:0px 15px;" onclick="javascript:editArticle('${cmArticle.articleId}');"><fmt:message key="button.edit"/></button>
			<button class="gradient-green-btn" style="float:right;margin:0px 15px;" onclick="javascript:addPhoto('${cmArticle.articleId}');"><fmt:message key="cmArticle.add.photo"/></button>
		</c:if>
	</div>
	  
	<div class="clearfix"></div>
	<div style="padding:2px 0 20px;">
      <div class="fl fs1"><fmt:message key="common.field.author"/> : <a href="${cmArticle.originUrl}" target="_blank">${cmArticle.author}</a></div>
      <div class="fr fs1"><fmt:message key="common.field.updateTime"/> : <fmt:formatDate  value='${cmArticle.modifyTimestamp}' pattern='HH:mm yyyy-MM-dd'/></div>
    </div>
    <div class="clearfix"></div>
    <!-- 
    <div>
      <c:if test="${cmArticle.imageUrl!=''}">
	    <img src="<c:url value='${cmArticle.imageUrl}'/>" width="100%"/>
      </c:if>
    </div>
     -->
     
    <div class="tl" style="text-align:left;line-height:1.3em;">${cmArticle.content}</div>
    <c:forEach var="cmPhoto" items="${cmPhotos}">
       <div style="display:inline-block;margin-left:10px;width:220px;" >
          <div>
	      <c:choose>
			<c:when test="${fn:startsWith(cmPhoto.url, 'http') || fn:startsWith(cmPhoto.url, '/')}">
				<a href="<c:url value='${cmPhoto.url}'/>">
			  	  <img id="image" src="<c:url value='${cmPhoto.url}'/>" width="200"/>
		        </a>
			</c:when>
			<c:when test="${cmPhoto.url !='' && cmPhoto.url!=null && !fn:startsWith(cmPhoto.url, 'http')}">
			   <a href="<c:url value='${ImageUrl}/${cmPhoto.url}'/>">
			  	  <img id="image" src="<c:url value='${ImageUrl}/${cmPhoto.url}'/>" width="200"/>
		       </a>
			</c:when>
		  </c:choose>
		  </div>
		  <div style="font-size:14px;margin:5px;">
		  ${cmPhoto.remark}
		  </div>
		  <c:if test="${user!=null}">
	   	   <div class="editPhotoBtns">
	   	  	  <button class="gradient-green-btn" onclick="javascript:editPhoto('${cmPhoto.photoId}');"><fmt:message key="button.edit"/></button>
	   	  	  <button class="gradient-green-btn" onclick="javascript:confirmDeletePhoto('${cmPhoto.photoId}','${cmPhoto.photoName}','${cmPhoto.title}');">
	   	  	    <fmt:message key="button.delete"/></button>
	   	  	</div>
	   	  </c:if>
		</div>  
    </c:forEach>
	<div>
		<a href="javascript:back();"><img src="<c:url value='/resources/style/default/image/jp/icon/go-back-icon.png'/>" style="width:32px;float:right;"/></a>
	</div>        	
  </div>
</div>

<form id="cmArticleForm" action="<c:url value='/vtwo/knowledge'/>" method="post">
  <input id="edit" name="edit" type="hidden" value="${edit}"/>
  <input id="pageNo" name="pageNo" type="hidden" value="${pageNo}"/>
  <input id="pageSize" name="pageSize" type="hidden" value="${pageSize}"/>
  <input id="tag" name="tag" type="hidden" value="${tag}"/>
</form>
<div id="dialog-confirm-delete-photo" title="" style="display:none;">
  <p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
  The item will be permanently deleted and cannot be recovered. Are you sure?</p>
</div>
<div class="clearfix"></div>
<%@ include file="/pages/common/vtwo/inc/footer.jsp" %>
</body>
</html>
<script>
<!--
function back() {
	$("#cmArticleForm").submit();
}
function editArticle(articleId) {
  window.location.href = "<c:url value='/cmArticleJqmEdit/'/>" + articleId + "?edit=${edit}&from=/vtwo/knowledgeDetail&pageNo=${pageNo}&pageSize=${pageSize}&tag=${tag}";
}
$(document).ready(function () {

});

function editPhoto(photoId) {
  window.location.href = "<c:url value='/cmPhotoJqmEdit/'/>" + photoId + "?edit=" + edit + "&from=/vtwo/knowledgeDetail?articleId=${cmArticle.articleId}&pageNo=${pageNo}&pageSize=${pageSize}";//&tag= + $("#searchTagTxt").val();
}

function confirmDeletePhoto(photoId,photoName,title) {
  $("#dialog-confirm-delete-photo").attr("title", I18N.msg_delete_photo_title);
  $("#dialog-confirm-delete-photo p").html(String.format(I18N.msg_delete_content, title));
  $("#dialog-confirm-delete-photo").dialog({
    resizable: false,
    height: 240,
    modal: true,
    buttons: {
      "<fmt:message key='button.delete'/>": function() {
    	  $(this).dialog("close");
    	  window.location.href="<c:url value='/deleteCmPhoto/'/>" + photoId + "?tag=" + $("#searchTagTxt").val() + "&photoName=" + photoName +
    	  	"&from=/vtwo/knowledgeDetail?articleId=${cmArticle.articleId}&edit=${edit}&pageNo=${pageNo}&pageSize=${pageSize}";
      },
      "<fmt:message key='button.cancel'/>": function() {
        $(this).dialog("close");
      }
    }
  });
}

function addPhoto(articleId) {
	window.open("<c:url value='/cmPhotoJqmAdd?tag=Article&edit=&from=/vtwo/knowledgeDetail?articleId=" + articleId + "&pageNo=1&pageSize=30&articleId='/>" + articleId, "_blank");	
}

//-->
</script>
