<div id="footer" style="margin-top:-20px;">
  <div class="content" style="padding-top:10px;">
    <table width="100%">
      <tr style="color:#cccccc">
        <td width="25%"><fmt:message key='footer.project'/></td>
        <td width="25%"><fmt:message key='footer.language'/></td>
        <td width="25%"><fmt:message key='footer.follow.me'/></td>
      	<td width="25%"><fmt:message key='footer.about.me'/></td>
      </tr>
      <tr style="color:#0abfff">
      	<td><a href="javascript:openOverviewDialog();"><fmt:message key='footer.project.introduce'/></a></td>
      	<td><a href="javascript:changeLang('zh_CN')"><fmt:message key="common.language.zh_CN"/></a></td>
      	<td>
      	  <c:choose>
		 	<c:when test="${lang == 'zh_CN'}"><a href="http://www.weibo.com/u/1313516924" target="_blank"><fmt:message key='footer.follow.me.sina.weibo'/></a></c:when>
		 	<c:otherwise><a href="http://www.facebook.com/ganjpxm" target="_blank"><fmt:message key='footer.follow.me.facebook'/></a></c:otherwise>
		  </c:choose>
      	</td>
      	<td><a href="javascript:openProfileDialog();"><fmt:message key='footer.about.me.profile'/></a></td>
      </tr>
      <tr style="color:#0abfff">
      	<td><a href="mailto:ganjpxm@gmail.com"><fmt:message key='footer.project.feedback'/></a></td>
      	<td><a href="javascript:changeLang('en_SG')"><fmt:message key="common.language.en_SG"/></a></td>
      	<td>
		  <c:choose>
		 	<c:when test="${lang == 'zh_CN'}"><a href="http://t.qq.com/ganjpxm" target="_blank"><fmt:message key='footer.follow.me.tencent.weibo'/></a></c:when>
		 	<c:otherwise><a href="https://twitter.com/ganjpxm" target="_blank"><fmt:message key='footer.follow.me.twitter'/></a></c:otherwise>
		  </c:choose>
		</td>
		<td><a href="javascript:openContactDialog();"><fmt:message key='footer.about.me.contact'/></a></td>      	
      </tr>
      <tr style="color:#0abfff">
        <td></td>
        <td></td>
      	<td>
		  <c:choose>
		 	<c:when test="${lang == 'zh_CN'}"><a href="http://user.qzone.qq.com/279875467" target="_blank"><fmt:message key='footer.follow.me.qzone'/></a></c:when>
		 	<c:otherwise><a href="https://plus.google.com/104677680729178039721" target="_blank"><fmt:message key='footer.follow.me.google.plus'/></a></c:otherwise>
		  </c:choose>
		</td>
        <td></td>
      </tr>
      <tr style="color:#0abfff">
      	<td></td>
      	<td></td>
      	<td>
		  <c:choose>
		 	<c:when test="${lang == 'zh_CN'}"></c:when>
		 	<c:otherwise><a href="http://www.linkedin.com/profile/view?id=213212300" target="_blank"><fmt:message key='footer.follow.me.linkin'/></a></c:otherwise>
		  </c:choose>
		</td>
      	<td></td>
      </tr>
      <tr style="color:#0abfff">
      	<td></td>
      	<td></td>
      	<td>
		  <c:choose>
		 	<c:when test="${lang == 'zh_CN'}"></c:when>
		 	<c:otherwise><a href="http://pinterest.com/ganjpxm" target="_blank"><fmt:message key='footer.follow.me.pinterest'/></a></c:otherwise>
		  </c:choose>	
		</td>
      	<td></td>
      </tr>
    </table>
	<div class="copyright"><fmt:message key="project.copyright"/></div>  
  </div>
</div>
<div id="dialog-overview" title="<fmt:message key='project.name'/> <fmt:message key='footer.project.introduce'/>" style="display:none;">
  <div><fmt:message key='porject.welcome'/></div>
</div>
<div id="dialog-profile" title="<fmt:message key='footer.about.me.profile'/>" style="display:none;">
  <div>
	<div class="lable"><fmt:message key='common.field.name'/> : <span class="value"><fmt:message key='common.value.name'/></span></div>
	<div class="lable"><fmt:message key='common.field.birth.place'/> : <span class="value"><fmt:message key='common.value.birth.place'/></span></div>
	<div class="lable"><fmt:message key='common.field.birth.date'/> : <span class="value"><fmt:message key='common.value.birth.date'/></span></div>
	<div class="lable"><fmt:message key='common.field.school'/> : <span class="value"><fmt:message key='common.value.school'/></span></div>
	<div class="lable"><fmt:message key='common.field.work.experiences'/> : <span class="value"><fmt:message key='common.value.work.experiences'/></span></div>
	<div class="lable"><fmt:message key='common.field.favorite'/> : <span class="value"><fmt:message key='common.value.favorite'/></span></div>
	<div class="lable"><fmt:message key='common.field.life.motto'/> : <span class="value"><fmt:message key='common.value.life.motto'/></span></div>
  </div>
</div>
<div id="dialog-contact" title="<fmt:message key='footer.about.me.contact'/>" style="display:none;">
  <div>
    <div class="lable"><fmt:message key='common.field.qq'/> : <span class="value"><fmt:message key='common.value.qq'/></span></div>
    <div class="lable"><fmt:message key='common.field.email'/> : <span class="value"><fmt:message key='common.value.email'/></span></div>
    <div class="lable"><fmt:message key='common.field.skype'/> : <span class="value"><fmt:message key='common.value.skype'/></span></div>
    <div class="lable"><fmt:message key='common.field.address'/> : <span class="value"><fmt:message key='common.value.address'/></span></div>
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

function openProfileDialog() {
  $("#dialog-profile").dialog({
    resizable: true,
	height: 550,
	width: 450,
	modal: true,
	buttons: {
	  "<fmt:message key='button.close'/>": function() {
	    $(this).dialog("close");
	  }
	}  
  });
}

function openContactDialog() {
  $("#dialog-contact").dialog({
    resizable: true,
	height: 300,
	modal: true,
	buttons: {
	  "<fmt:message key='button.close'/>": function() {
	    $(this).dialog("close");
	  }
	}  
  });
}

function openOverviewDialog() {
  $("#dialog-overview").dialog({
    resizable: true,
	height: 300,
	modal: true,
	buttons: {
	  "<fmt:message key='button.close'/>": function() {
	    $(this).dialog("close");
	  }
	}  
  });
}

//-->
</script>