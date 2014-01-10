<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title><fmt:message key="test.jquery.mobile.tile"/></title>
	<%@ include file="/pages/common/inc/headjqm.jsp" %>
	<style type="text/css">
	</style>
</head>
<body>
<div id="page" data-role="page" class="jpw-page">
  <div id="header" data-role="header" data-theme="${jqmTheme}">
    <h1><fmt:message key="test.jquery.mobile.tile"/></h1>
    <div class="jqm-header-left">
       <a href="#" data-role="button" data-icon="home" data-iconpos="notext"><fmt:message key="button.home"/></a>
    </div>
    <div class="jqm-header-right">
		<fieldset data-role="controlgroup" data-type="horizontal">
			<select id="jqmTheme" name="jqmTheme" >
				<option value="a" <c:if test="${jqmTheme=='a'}">selected</c:if>><fmt:message key="common.theme.black"/></option>
				<option value="b" <c:if test="${jqmTheme=='b'}">selected</c:if>><fmt:message key="common.theme.blue"/></option>
				<option value="c" <c:if test="${jqmTheme=='c'}">selected</c:if>><fmt:message key="common.theme.slive"/></option>
				<option value="d" <c:if test="${jqmTheme=='d'}">selected</c:if>><fmt:message key="common.theme.gray"/></option>
				<option value="e" <c:if test="${jqmTheme=='e'}">selected</c:if>><fmt:message key="common.theme.yellow"/></option>
				<option value="f" <c:if test="${jqmTheme=='f'}">selected</c:if>><fmt:message key="common.theme.green"/></option>
			</select>
			<select name="lang" id="lang">
				<option value="zh_CN" <c:if test="${lang=='zh_CN'}">selected</c:if>><fmt:message key="common.language.zh_CN"/></option>
				<option value="en_SG" <c:if test="${lang=='en_SG'}">selected</c:if>><fmt:message key="common.language.en_SG"/></option>
			</select>
		</fieldset>
    </div>
  </div> 
  <div id="content" data-role="content">
    <div class="jpw-content">
    </div>
    <div class="jpw-nav-vertical-menu">
	  <div data-role="collapsible" data-collapsed="true" data-theme="${jqmTheme}" data-content-theme="d">
	    <h3><fmt:message key="common.theme"/></h3>
	    <ul data-role="listview" data-theme="c" data-dividertheme="d">
	      <!--<li data-role="list-divider"></li>-->
	      <li><a href="javascript:changeJqmTheme('a')"><fmt:message key="common.theme.black"/></a></li>
		  <li><a href="javascript:changeJqmTheme('b')"><fmt:message key="common.theme.blue"/></a></li>
		  <li><a href="javascript:changeJqmTheme('c')"><fmt:message key="common.theme.slive"/></a></li>
		  <li><a href="javascript:changeJqmTheme('d')"><fmt:message key="common.theme.gray"/></a></li>
		  <li><a href="javascript:changeJqmTheme('e')"><fmt:message key="common.theme.yellow"/></a></li>
		  <li><a href="javascript:changeJqmTheme('f')"><fmt:message key="common.theme.green"/></a></li>
		</ul>
	  </div>
	  <div data-role="collapsible" data-theme="b" data-content-theme="d" data-collapsed-icon="arrow-r" 
	  	data-expanded-icon="arrow-d" data-inset="false" >
		<h3><fmt:message key="common.language"/></h3>
		<ul data-role="listview">
			<li><a href="javascript:changeLanguage('zh_CN')"><fmt:message key="common.language.zh_CN"/></a></li>
			<li><a href="javascript:changeLanguage('en_SG')"><fmt:message key="common.language.en_SG"/></a></li>
		</ul>
	  </div>
	</div>
  </div> 
</div>
</body>
</html>
<script type="text/javascript">
<!--
function changeJqmTheme(value) {
  $.cookie("jqmTheme", value, {expires:7});
  location.reload(true);
}
function changeLanguage(value) {
  $.cookie("lang", value, {expires:7});
  location.reload(true);  
}
$(function() {
  $('#jqmTheme').change(function() {changeJqmTheme($(this).val());});
  $('#lang').change(function() {changeLanguage($(this).val());});
});
//-->
</script>