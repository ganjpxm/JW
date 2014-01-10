  <div id="footer" data-role="footer" data-position="fixed" data-theme="${jqmTheme}">
	<!-- <div class="jqm-footer-left"><fmt:message key="project.copyright"/></div> -->
	<div class="jqm-footer-right">
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
</div>
<script type="text/javascript">
<!--
var url = window.location.href;
function changeLang(lang) {
  $.cookie("lang", lang, {expires:7});
  var index = url.indexOf("changeLange");
  if (index!=-1) {
	  url = url.substring(0,index+12) + lang; 
  } else {
	  if (url.indexOf("?")!=-1) {
		url += "&changeLange=" + lang;
	  } else {
		url += "?changeLange=" + lang;
	  }   
  }
  window.location.href = url;
}

$(function() {
  $('#jqmTheme').change(function() {
	var value = $(this).val();
	$.cookie("jqmTheme", value, {expires:7});
	location.reload(true);
  });
  $('#lang').change(function() {
	var value = $(this).val();
	changeLang(value);
  });
});
//-->
</script>