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
  <div class="jpw-content">
    <div data-role="collapsible" data-theme="${jqmTheme}" data-content-theme="c" data-collapsed='false' data-mini="true" style="width:300px">
      <h2><fmt:message key="server.information"/></h2>
      <p><fmt:message key="server.time"/> : <fmt:formatDate  value='${serverTime}' pattern='dd/MM/yyyy hh:mm:ss'/></p>
      <p><fmt:message key="server.os.name"/> : ${osName}</p>
      <p><fmt:message key="server.os.version"/> : ${osVersion}</p>
      <p><fmt:message key="java.version"/> : ${javaVersion}</p>
      <p><fmt:message key="java.vendor"/> : ${javaVendor}</p>
    </div> 
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
