<footer>
  <div class="content" style="padding-top:10px;">
    <table width="100%">
      <tr style="color:#cccccc">
        <td width="25%">Theme</td>
        <td width="25%">Language</td>
        <td width="25%">Follow Me</td>
      	<td width="25%">About Me</td>
      </tr>
      <tr style="color:#0abfff">
      	<td>Default</td>
      	<td><a href="javascript:changeLang('zh_CN')"><fmt:message key="common.language.zh_CN"/></a></td>
      	<td>Facebook</td>
      	<td>Profile</td>
      </tr>
      <tr style="color:#0abfff">
      	<td></td>
      	<td><a href="javascript:changeLang('en_SG')"><fmt:message key="common.language.en_SG"/></a></td>
      	<td>Twitter</td>
		<td>Contact Me</td>      	
      </tr>
      <tr style="color:#0abfff">
        <td></td>
        <td></td>
      	<td>Google+</td>
        <td>Feedback</td>
      </tr>
      <tr style="color:#0abfff">
      	<td></td>
      	<td></td>
      	<td>Pinterest</td>
      	<td></td>
      </tr>
      <tr style="color:#0abfff">
      	<td></td>
      	<td></td>
      	<td>Flickr</td>
      	<td></td>
      </tr>
    </table>
	<div class="copyright"><fmt:message key="project.copyright"/></div>  
  </div>
</footer>
<script type="text/javascript">
<!--
var url = window.location.href;
var index = url.indexOf("#page");
if (index!=-1) {
  url = url.substring(0,index)
}
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

//-->
</script>