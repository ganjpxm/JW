<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<%@ include file="/pages/common/inc/headjqm.jsp" %>
</head>
<body>
<%@ include file="/pages/common/inc/headerjqm.jsp" %>
<div id="content" data-role="content" class="body">
    <div data-role="collapsible" data-theme="${jqmTheme}" data-content-theme="c" data-collapsed='false' data-mini="true" style="display:inline-block;width:260px;margin-left:20px;">
      <h2>Gan Jianping Website Version</h2>
      <p style="font-weight:bold;">V1.0.3 (19/09/2013)</p>
      	<p style="padding-left:5px;">
      	   1. Add search tag to add article form.
      	   2. Add ref_article_id in the cm_photo table.
      	   3. Add photos in the article.
      	   4. Add the MobileController for mobile (BmConfig, CmArticle, CmPhoto)
      	   5. CmPhoto remark(varchar(255)) to text, remark use rich editor.
      	   6. Change logo and default link icon.
      	</p>
      </p>
      <p style="font-weight:bold;">V1.0.2 (25/05/2013)</p>
      	<p style="padding-left:5px;">
      	  1. Fixed mobile phone display bug. </br>
      	  2. Video support Flickr, Vimeo, Youtube </br>  
      	</p>
      </p>
      <p style="font-weight:bold;">V1.0.1 (18/05/2013)</p>
      	<p style="padding-left:5px;">
      	  1. add edit button in the konwledge detail.<br/>
      	  2. change photo channal's photo popup mode (magnificPopup)<br/>
      	  3. photo, video, audio, file upload to rackspace cloud files.<br/>
      	  4. add system param : AudioUrl, PhotoUrl, AudioUrl, FileUrl<br/>
      	</p>
      </p>
      <p style="font-weight:bold;">V1.0.0 (05/05/2013)</p>
      	<p style="padding-left:5px;">
      	  1. Change logo <br/>
      	  2. Change knowledge content editer (From tinymce to SCEditor)<br/>
      	  3. Fix the mobile scrolling issue (From shapeshift to masonry)<br/>
      	  4. Add null search (For tag value is empty)<br/>
      	  5. Encrypte password<br/>
      	  6. Fix chinese messy code issue 
      	</p>
      </p>
    </div>
    <div data-role="collapsible" data-theme="${jqmTheme}" data-content-theme="c" data-collapsed='false' data-mini="true" style="display:inline-block;width:260px;margin-left:20px;">
      <h2><fmt:message key="server.information"/></h2>
      <p><fmt:message key="server.time"/> : <fmt:formatDate  value='${serverTime}' pattern='dd/MM/yyyy hh:mm:ss'/></p>
      <p><fmt:message key="server.os.name"/> : ${osName}</p>
      <p><fmt:message key="server.os.version"/> : ${osVersion}</p>
      <p><fmt:message key="java.version"/> : ${javaVersion}</p>
      <p><fmt:message key="java.vendor"/> : ${javaVendor}</p>
    </div> 
  <div class="jpw-nav-vertical-menu">
	${leftMenuHtml}    
  </div>
</div>
<%@ include file="/pages/common/inc/footerjqm.jsp" %>
</body>
</html>
<script type="text/javascript">
<!--

//-->
</script>
