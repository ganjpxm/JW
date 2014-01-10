<header>
  <div id="global-nav">
    <div class="content">
  	  <div class="projects">
        <div class="project <c:if test="${project=='websiteNavigate'}">active</c:if>" title="<fmt:message key='project.website.navigate'/>">
        	<a href="<c:url value='/vtwo/home'/>"><div class="item website-navigate">&nbsp;</div></a></div>
        <div class="project <c:if test="${project=='knowledge'}">active</c:if>" title="<fmt:message key='project.knowledge'/>">
        	<a href="<c:url value='/vtwo/knowledge'/>"><div class="item knowledge">&nbsp;</div></a></div>
        <div class="project <c:if test="${project=='photoVideo'}">active</c:if>" title="<fmt:message key='project.photo.video'/>">
        	<a href="<c:url value='/vtwo/photoVideo'/>"><div class="item photo-video">&nbsp;</div></a></div>
        <div class="project <c:if test="${project=='ecommerce'}">active</c:if>" title="<fmt:message key='project.ecommerce'/>">
        	<a href="<c:url value='/vtwo/ecommerce'/>"><div class="item ecommerce">&nbsp;</div></a></div>
        <div class="project <c:if test="${project=='demo'}">active</c:if>" title="<fmt:message key='project.demo'/>">
        	<a href="<c:url value='/vtwo/demo'/>"><div class="item demo">&nbsp;</div></a></div>
      </div>
      <div class="links">
    	<div id="searchBtnFrame" class="link">
    		<a href="javascript:displaySearch();" title="<fmt:message key='button.search'/>"><div id="searchBtn" class="item">&nbsp;</div></a>
    	</div>
        <div id="userLoginBtnFrame" class="link">
        	<a href="javascript:userLogin();" title="<fmt:message key='button.login.signup'/>">
        		<div id="userLoginBtn" class="item">
        		  <c:if test="${user!=null}">	
        		     <img style="width:24px;height:24px;" src="<c:if test='${user.photoUrl!=null}'><c:url value='${user.photoUrl}'/></c:if><c:if test='${user.photoUrl==null}'><c:url value='/resources/style/default/image/jp/photo/default-head.png'/></c:if>"/>
        		  </c:if>
        		  <c:if test="${user==null}">&nbsp;</c:if>
        		</div>
        	</a>
        </div>
        <div id="helpBtnFrame" class="link">
        	<a href="javascript:help();" title="<fmt:message key='button.help'/>"><div id="helpBtn" class="item">&nbsp;</div></a>
        </div>
      </div>
    </div>
  </div>
  <div class="clearfix"></div>
</header>
<div id="hideArea">
  <div class="content">
    <div id="search" class="item">
      <input id="searchTxt" type="text" title="<fmt:message key='search.placeholder'/>" style="padding:0px 10px;" placeholder="<fmt:message key='search.placeholder'/>"/>
      <button onclick="javascript:search();" title="Search" class="searchBtn">&nbsp;</button>
      <div class="clear">
        <div id="googleRadio" style="margin-top: 10px; display:inline-block;"><span><fmt:message key='search.google'/></span></div>
        <div id="baiduRadio" style="margin-top: 10px; display:inline-block;"><span><fmt:message key='search.baidu'/></span></div>
        <div id="youtubeRadio" style="margin-top: 10px; display:inline-block;"><span><fmt:message key='search.youtube'/></span></div>
        <div id="findiconRadio" style="margin-top: 10px; display:inline-block;"><span><fmt:message key='search.icon'/></span></div>
      </div>
    </div>
    <div id="userLogin" class="item">
      <div style="margin-bottom:-20px;padding-bottom:10px;<c:if test="${user!=null}">display:none;</c:if>">
	  <form id="amUserSignInForm" action="<c:url value='/login'/>" method="post" data-ajax="true">
	    <div style="text-align:center;font-size:20px;"><fmt:message key='project.welcome'/></div>
        <div id="loginErrorInfo" style="color:red;"></div>
        <div class="lable" style="padding:10px;"><span class="red">*</span><fmt:message key="amUser.userCd"/> : </div>
    	<input id="userCdOrEmail" name="userCdOrEmail" class="input-text corner-all"/> 
    	<div class="lable" style="padding:10px;"><span class="red">*</span><fmt:message key="amUser.password"/> : </div>
    	<input id="userPassword" name="userPassword" type="password" class="input-text corner-all"/>		
    	<div style="text-align:right;">
    		<input id="signInBtn" type="button" class="gradient-btn" value="<fmt:message key="button.sign.in"/>" onClick="javascript:signIn();"/>
    	</div>	  	
	  </form>
	  </div>	
	  <c:if test="${user!=null}">
	  <div style="padding:10px 0 20px;">
	  	<div style="display:inline-block; width:210px;">
	  	    <img id="userPhoto" style="width:200px;margin-top:10px;margin-right:20px;" 
	  	     src="<c:if test='${user.photoUrl!=null}'><c:url value='${user.photoUrl}'/></c:if><c:if test='${user.photoUrl==null}'><c:url value='/resources/style/default/image/jp/photo/default-head.png'/></c:if>"/>
	  	    <video id="monitor" style="width:200px; height:200px; margin-top:10px;margin-right:20px; display:none" autoplay></video>
	  	    <canvas id="canvas" style="display:none"></canvas>
	  	    <div id="snapshotButton" style="display:none;">
	  	      <input type="button" value="Snapshot" onclick="snapshot();" data-role="none"/>
	  	      <input type="button" value="Cancel" onclick="cancelTakePhoto();" data-role="none"/>
	  	    </div>
		    <div id="photoButton" class="dp100" style="padding-top:10px;">
		      <label class="upload_btn fileinput-button" style="height:25px;">
		        <span style="font-weight:bold;font-size:15p;">Change...</span>
		        <form method="post"><input type="hidden" name="folder" value="photo"/><input type="hidden" name="imgId" value="userPhoto"/><input type="hidden" name="userId" value="${user.userId}"/>
		          <input id="uploadUserPhoto" type="file" name="file" data-url="<c:url value='/uploadFile'/>" multiple/></form>
		      </label>
		      <input type="button" value="From Camera" class="gradient-btn" onClick="openCamera();"/>
	        </div>
	  	</div>
	  	<div style="display:inline-block;">
		  <c:if test="${isAdmin=='yes' || isManager=='yes'}">
			<a href="<c:url value='/console'/>"><button class="gradient-btn"><fmt:message key="project.management"/></button></a>
		  </c:if>
		  <a href="<c:url value='/loginUserJqmEdit/${user.userId}'/>"><button class="gradient-btn"><fmt:message key="amUser.information"/></button></a>
		  <a href="<c:url value='/vtwo/logout'/>"><button class="gradient-btn"><fmt:message key="button.logout"/></button></a>
		</div>
	  </div>
	  </c:if>
	</div>
    <div id="help" class="item">&nbsp;</div>
  </div>
</div>
<script type="text/javascript">
<!--
var searchDisplay = "hidden";
var userLoginDisplay = "hidden";
var helpDisplay = "hidden";

var searchWebsite = "http://www.google.com/search?ie=UTF-8&q=";
function displaySearch() {
  if (searchDisplay == "display") {
	searchDisplay = "hidden";
	$("#search").hide();	
	$("#searchBtnFrame").removeClass("active");
  } else {
	searchDisplay = "display";
	$("#search").show();
	$('#searchTxt').focus();
	$("#searchBtnFrame").addClass("active");
	$("#userLoginBtnFrame").removeClass("active");
	$("#helpBtnFrame").removeClass("active");
	$("#searchTxt").val("");
  }
  $("#userLogin").hide();
  $("#help").hide();
}
function userLogin() {
  if (userLoginDisplay == "display") {
	userLoginDisplay = "hidden";
	$("#userLogin").hide();	
	$("#userLoginBtnFrame").removeClass("active");
  } else {
	userLoginDisplay = "display";
	$("#userLogin").show();
	$('#userCdOrEmail').focus();
	$("#userLoginBtnFrame").addClass("active");
	$("#searchBtnFrame").removeClass("active");
	$("#helpBtnFrame").removeClass("active");
  }
  $("#search").hide();
  $("#help").hide();
}
function help() {
  if (helpDisplay == "display") {
	helpDisplay = "hidden";
	$("#help").hide();
	$("#helpBtnFrame").removeClass("active");
  } else {
	helpDisplay = "display";
	$("#help").show();
	$("#helpBtnFrame").addClass("active");
	$("#userLoginBtnFrame").removeClass("active");
	$("#searchBtnFrame").removeClass("active");
  }
  $("#userLogin").hide();
  $("#search").hide();
}

function search() {
  window.open(searchWebsite + $("#searchTxt").val(), "_blank");	
}

function signIn() {
  var password = $("#userPassword").val();
  var userId = $("#userCdOrEmail").val();
  if (jp.isEmpty(userId)) {
	  $("#loginErrorInfo").html(String.format("<fmt:message key="errors.required"/>", "<fmt:message key="amUser.userCd"/>"));
  } else if (jp.isEmpty(password)) {
	  $("#loginErrorInfo").html(String.format("<fmt:message key="errors.required"/>", "<fmt:message key="amUser.password"/>"));
  } else {
	  $("#signInBtn").attr("disabled", "disabled");
	  $("#amUserSignInForm").ajaxSubmit({dataType:'json', success : function(data) {
		if (data.result=="success") {
		  window.location.href = url;
		} else {
		  $("#signInBtn").removeAttr("disabled");	
		  $("#loginErrorInfo").html(I18N.msg_error_user_cd_password);
		}
	  }});  
  }
}
$(document).ready(function () {
  var theme = "office";
  if (!jp.isIE) {
	  var searchList = new Array("Java", "JavaScript");
	  $("#searchTxt").jqxInput({placeHolder: "<fmt:message key='search.placeholder'/>", minLength: 1, theme: theme, source: searchList });  
  }
  $("#googleRadio").jqxRadioButton({ width: 80, height: 30, checked: true, theme: theme });
  $("#baiduRadio").jqxRadioButton({ width: 80, height: 30, theme: theme });
  $("#youtubeRadio").jqxRadioButton({ width: 80, height: 30, theme: theme });
  $("#findiconRadio").jqxRadioButton({ width: 80, height: 30, theme: theme });
  $("#googleRadio").bind('change', function (event) { if (event.args.checked) searchWebsite = "http://www.google.com/search?ie=UTF-8&q="; });
  $("#baiduRadio").bind('change', function (event) { if (event.args.checked) searchWebsite = "http://www.baidu.com/s?wd="; });
  $("#youtubeRadio").bind('change', function (event) { if (event.args.checked) searchWebsite = "http://www.youtube.com/results?search_query="; });
  $("#findiconRadio").bind('change', function (event) { if (event.args.checked) searchWebsite = "http://findicons.com/search/"; });
  $("#searchTxt").keypress(function(event) { if ( event.keyCode == 13 ) search(); });

  $('#userPassword').bind('keypress', function(e) {
    var code = (e.keyCode ? e.keyCode : e.which);
	if(code == 13) signIn(); 
  });
  
  $("#uploadUserPhoto").fileupload({
	dataType: 'json',
	done: function (e, data) {
	  if (data.result.result == "success") {
		$("#" + data.result.imgId).attr("src", data.result.showUrl);
	  } else {
	    alert(data.result.result);
	  }
	}
  });
});
//-->
</script>
