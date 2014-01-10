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
      <form id="cmArticleForm" action="<c:url value='/vtwo/knowledge'/>" method="post">
	    <input type="hidden" name="edit" value="${edit}"/>
	    <input type="hidden" name="pageNo" value="1"/>
	    <input type="hidden" name="pageSize" value="${pageSize}"/>
        <input id="searchTagTxt" name="tag" type="text" value="${tag}" title="<fmt:message key='search.placeholder.tag'/>" 
      	  placeholder="<fmt:message key='search.placeholder.tag'/>" style="width:85%;height:28px;"/>
        <button onclick="javascript:searchArticleByTag();" title="<fmt:message key='button.search'/>" class="searchBtn" style="height:35px;margin-top:-10px;">&nbsp;</button>
	  </form>
    </div>
        <div id="dycontainerProperty">
          <input type="checkbox" id="image" checked="checked"/><label for="image"><fmt:message key='common.field.image'/></label>
          <input type="checkbox" id="summary" checked="checked"/><label for="summary"><fmt:message key='cmArticle.summary'/></label>
  		  <input type="checkbox" id="drag" /><label for="drag"><fmt:message key='common.field.drag'/></label>
  		  <input type="checkbox" id="tag" /><label for="tag"><fmt:message key='common.field.tag'/></label>
  		  <c:if test="${user!=null}">
  		    <div style="float:right">
  		      <input type="checkbox" id="showEditArticle" onclick="javascript:displayEdit();"/><label for="showEditArticle"><fmt:message key="button.edit"/></label>
  		    </div>
  		    <div style="float:right">
  		      <button id="addArticle" onclick="javascript:addArticle();"><fmt:message key="button.add"/></button>
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
          <div style="text-align:left;margin-left:-20px;">${articleCategoryCheckboxHtml}</div>
        </div>
	    <div id="dycontainer" class="dycontainer">
		    <c:forEach var="map" items="${articleMaps}" varStatus="status">
		        <div title="${map.tag}" class="object" style="background-color:white;display:block;">
		          <a href="<c:url value='/articleJqmDetail/${map.articleId}?edit=${edit}'/>" style="color:#333; text-decoration:none; font-size: 18px; font-family:"MuseoSlabRegular",serif;line-height: 1.27;">
		          <c:if test="${map.imageUrl!=''}">
				    <img src="<c:url value='${map.imageUrl}'/>" width="100%" style="margin-bottom:0px;"/><br/>
			      </c:if>
			      <!-- articleId,articleCd,title,summary,author,imageUrl,tag,updateDate -->
			      <div style="padding:10px;">
			          ${map.title}<br/>
			          <div style="font-size:16px;color:#777;text-align:left;" class="summary">${map.summary}</div>
			      </div>
			      </a>
			   	  <c:if test="${user!=null}">
			   	    <div class="editArticleBtns" style="float:right;display:none;">
			   	  	  <button class="gradient-green-btn" onclick="javascript:editArticle('${map.articleId}');"><fmt:message key="button.edit"/></button>
			   	  	  <button class="gradient-green-btn" onclick="javascript:confirmDeleteArticle('${map.articleId}','${map.title}');">
			   	  	    <fmt:message key="button.delete"/></button>
			   	  	</div>
			   	  </c:if>
			   	</div>  		
		    </c:forEach>
	  	</div>
	  	<!--  
	  	<c:if test="${nextPageNo>0}">
	  	  <div>
	  		<button style="width:80%;height:50px;font-size:16px;padding:5px;">(${showCount}/${totalCount}) Show More...</button>
	  	  </div>
	  	</c:if>
	  	-->
	  	<c:if test="${totalPages>1}">
	  	<c:forEach var="i" begin="1" end="${totalPages}" step="1">
		  <button onclick="javascript:toPage(${i});" class="gradient-green-btn" style="padding:5px 15px;">${i}<c:if test="${pageNo==i}">-${pageSize}</c:if></button>
		</c:forEach>
		</c:if>
	</div>  	
  </div>
</div>
<div class="clearfix"></div>
<div id="dialog-confirm-delete-article" title="" style="display:none;">
  <p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>
  The item will be permanently deleted and cannot be recovered. Are you sure?</p>
</div>
<%@ include file="/pages/common/vtwo/inc/footer.jsp" %>
</body>
</html>
<script>
<!--
function addArticle() {
  window.location.href = "<c:url value='/cmArticleJqmAdd?edit=${edit}&from=/vtwo/knowledge'/>" + "&pageNo=1&pageSize=30";
}

function editArticle(articleId) {
  window.location.href = "<c:url value='/cmArticleJqmEdit/'/>" + articleId + "?edit=${edit}&from=/vtwo/knowledge&pageNo=${pageNo}&pageSize=${pageSize}&tag=" + $("#searchTagTxt").val();
}

var $editArticleBtns = $("#dycontainer .editArticleBtns");
var $showEditArticle = $('#showEditArticle');
function displayEdit() {
  //window.location.href = "<c:url value='/vtwo/knowledge?edit=yes'/>";
  if ($showEditArticle.is(':checked')) {
	$editArticleBtns.show();  
	shapeshift();
  } else {
	$editArticleBtns.hide();
	shapeshift();
  }
}

if ("${edit}"=="yes") {
  $editArticleBtns.show();
  $showEditArticle.attr('checked','checked');
}

function cancelEdit() {
  window.location.href = "<c:url value='/vtwo/knowledge?edit=no'/>";
}
	
function confirmDeleteArticle(articleId,title) {
  $("#dialog-confirm-delete-article").attr("title", I18N.msg_delete_article_title);
  $("#dialog-confirm-delete-article p").html(String.format(I18N.msg_delete_content, title));
  $("#dialog-confirm-delete-article").dialog({
    resizable: false,
    height: 240,
    modal: true,
    buttons: {
      "<fmt:message key='button.delete'/>": function() {
    	  $(this).dialog("close");
    	  window.location.href="<c:url value='/deleteCmArticle/'/>" + articleId + "?from=/vtwo/knowledge";
      },
      "<fmt:message key='button.cancel'/>": function() {
        $(this).dialog("close");
      }
    }
  });
}
function searchArticleByTag() {
  $("#cmArticleForm").submit();
  //window.location.href = "<c:url value='/vtwo/knowledge?edit=${edit}&tag='/>" + $("#searchTagTxt").val() + "&pageNo=1&pageSize=${pageSize}";
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
  window.location.href = "<c:url value='/vtwo/knowledge?edit=${edit}&tag='/>" + $("#searchTagTxt").val() + "&pageNo=" + pageNo + "&pageSize=${pageSize}";
}

$(document).ready(function () {
  shapeshift();
  if (!jp.isIE) {
     setTimeout(shapeshift,1000);
	 setTimeout(shapeshift,5000); 
	 //var searchList = new Array("Java", "JavaScript");
	 //$("#searchTagTxt").jqxInput({placeHolder: "<fmt:message key='search.placeholder'/>", minLength: 1, theme: "office", source: searchList });  
  }
  $('#searchTagTxt').focus();
  $("#searchTagTxt").keypress(function(event) { if ( event.keyCode == 13 ) searchArticleByTag(); });
  
  $("#dycontainerProperty" ).buttonset();

  $("#image").click(function() {
	  if ($('#image').is(':checked')) {
		  $("img").show();
		  shapeshift();
	  } else {
		  $("img").hide();
		  shapeshift();
	  }
  });
  
  $("#summary").click(function() {
	  if ($('#summary').is(':checked')) {
		  $("#dycontainer .summary").show();
		  shapeshift();
	  } else {
		  $("#dycontainer .summary").hide();
		  shapeshift();
	  }
  });
  
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
  
});


//-->
</script>
