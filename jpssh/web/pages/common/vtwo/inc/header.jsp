<div id="header" style="display:block;">
  <div id="global-nav">
    <div class="content">
  	  <div class="projects">
        <div class="project <c:if test="${project=='websiteNavigate'}">active</c:if>" title="<fmt:message key='project.website.navigate'/>">
        	<a href="<c:url value='/vtwo/home'/>"><div class="item website-navigate">&nbsp;</div></a></div>
        <div class="project <c:if test="${project=='knowledge'}">active</c:if>" title="<fmt:message key='project.knowledge'/>">
        	<a href="<c:url value='/vtwo/knowledge?pageNo=1&pageSize=30'/>"><div class="item knowledge">&nbsp;</div></a></div>
        <div class="project <c:if test="${project=='photo'}">active</c:if>" title="<fmt:message key='project.photo'/>">
        	<a href="<c:url value='/vtwo/photo?pageNo=1&pageSize=30'/>"><div class="item photo">&nbsp;</div></a></div>
        <div class="project <c:if test="${project=='audio'}">active</c:if>" title="<fmt:message key='project.audio'/>">
        	<a href="<c:url value='/vtwo/audio?pageNo=1&pageSize=30'/>"><div class="item audio">&nbsp;</div></a></div>	
        <div class="project <c:if test="${project=='video'}">active</c:if>" title="<fmt:message key='project.video'/>">
        	<a href="<c:url value='/vtwo/video?pageNo=1&pageSize=30'/>"><div class="item video">&nbsp;</div></a></div>	
        <!--  
        <div class="project <c:if test="${project=='ecommerce'}">active</c:if>" title="<fmt:message key='project.ecommerce'/>">
        	<a href="<c:url value='/vtwo/ecommerce'/>"><div class="item ecommerce">&nbsp;</div></a></div>
        -->
        <div class="project <c:if test="${project=='download'}">active</c:if>" title="<fmt:message key='project.download'/>">
        	<a href="<c:url value='/vtwo/download?pageNo=1&pageSize=30'/>"><div class="item download">&nbsp;</div></a></div>	
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
        		     <img id="userThumbnailPhoto" style="width:24px;height:24px;" onclick="javascript:userLoginIE();"
        		     	src="<c:if test='${user.photoUrl!=null}'><c:url value='${user.photoUrl}'/></c:if><c:if test='${user.photoUrl==null}'><c:url value='/resources/style/default/image/jp/photo/default-head.png'/></c:if>"/>
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
  <div class="clearfix" style="height:1px;"></div>
</div>
<div id="hideArea" style="display:block;">
  <div class="content">
    <div id="search" class="item" style="text-align:center;padding-top:25px;">
      <input id="searchTxt" type="text" title="<fmt:message key='search.placeholder'/>" style="padding:3px 10px;" placeholder="<fmt:message key='search.placeholder'/>"/>
      <button onclick="javascript:search();" title="Search" class="searchBtn" style="margin-left:-3px;">&nbsp;</button>
      <div class="clearfix">
        <div id="googleRadio" style="margin:10px;display:inline-block;float:center;"><span><fmt:message key='search.google'/></span></div>
        <div id="baiduRadio" style="margin:10px;display:inline-block;float:center;"><span><fmt:message key='search.baidu'/></span></div>
        <div id="youtubeRadio" style="margin:10px;display:inline-block;float:center;"><span><fmt:message key='search.youtube'/></span></div>
        <div id="findiconRadio" style="margin:10px;display:inline-block;float:center;"><span><fmt:message key='search.icon'/></span></div>
      </div>
    </div>
    <div id="userLogin" class="item" style="margin:0 10%;">
      <div style="margin-bottom:-20px;padding-bottom:10px;<c:if test='${user!=null}'>display:none;</c:if>">
	  <form id="amUserSignInForm" action="<c:url value='/login'/>" method="post" data-ajax="true">
	    <div style="text-align:center;font-size:20px;"><fmt:message key='project.welcome'/></div>
        <div id="loginErrorInfo" style="color:red;"></div>
        <div class="lable" style="padding:10px;"><span class="red">*</span><fmt:message key="amUser.userCd"/> : </div>
    	<input id="userCdOrEmail" name="userCdOrEmail" class="input-text corner-all"/> 
    	<div class="lable" style="padding:10px;"><span class="red">*</span><fmt:message key="amUser.password"/> : </div>
    	<input id="userPassword" name="userPassword" type="password" class="input-text corner-all"/>		
    	<div style="text-align:right;">
    	  <input id="signInBtn" type="button" class="gradient-green-btn" value="<fmt:message key="button.sign.in"/>" onClick="javascript:signIn();"/>
    	</div>	  	
	  </form>
	  </div>	
	  <c:if test="${user!=null}">
	  <div style="padding:10px 0 20px; height:300px;">
	  	<div style="float:left; width:250px; margin-right:20px;">
	  	    <img id="userPhoto" style="width:240px;" 
	  	     src="<c:if test='${user.photoUrl!=null}'><c:url value='${user.photoUrl}'/></c:if><c:if test='${user.photoUrl==null}'><c:url value='/resources/style/default/image/jp/photo/default-head.png'/></c:if>"/>
	  	    <video id="monitor" style="width:240px; height:240px; margin-top:10px;margin-right:20px; display:none" autoplay></video>
	  	    <canvas id="canvas" style="display:none"></canvas>
	  	    <div id="snapshotButton" style="display:none;">
	  	      <input type="button" value="<fmt:message key='button.snapshot'/>" onclick="snapshot();" class="gradient-green-btn"/>
	  	      <input type="button" value="<fmt:message key='button.cancel'/>" onclick="cancelTakePhoto();" class="gradient-green-btn"/>
	  	    </div>
		    <div id="photoButton" class="dp100" style="padding-top:10px;">
		      <label class="upload_btn fileinput-button">
		        <span style="font-weight:bold;font-size:15p;"><fmt:message key="button.upload.change"/></span>
		        <form method="post"><input type="hidden" name="folder" value="photo"/><input type="hidden" name="imgId" value="userPhoto"/><input type="hidden" name="userId" value="${user.userId}"/>
		          <input id="uploadUserPhoto" type="file" name="file" data-url="<c:url value='/uploadFile'/>" multiple/></form>
		      </label>
		      <input type="button" value="<fmt:message key='button.open.camera'/>" class="gradient-green-btn" onClick="openCamera();"/>
	        </div>
	  	</div>
	  	<div style="float:left;margin-bottom:10px">
	  	  <div>
			<div class="lable"><fmt:message key='amUser.userCd'/> : <span class="value">${user.userCd}</span></div>
			<div class="lable"><fmt:message key='amUser.userName'/> : <span class="value">${user.userName}</span></div>
			<div class="lable"><fmt:message key='amUser.email'/> : <span class="value">${user.email}</span></div>
			<div class="lable"><fmt:message key='amUser.mobilePhone'/> : <span class="value">${user.mobilePhone}</span></div>
			<div class="lable"><fmt:message key='amUser.birthday'/> : <span class="value"><fmt:formatDate value='${user.birthday}' pattern='dd/MM/yyyy'/></span></div>
			<div class="lable"><fmt:message key='amUser.loginTimes'/> : <span class="value">${user.loginTimes}</span></div>
			<div class="lable"><fmt:message key='amUser.lifeMotto'/> : <span class="value">${user.lifeMotto}</span></div>
		  </div>
		  <div>
		    <a href="<c:url value='/loginUserJqmEdit/${user.userId}?from=/vtwo/home'/>"><button class="gradient-green-btn"><fmt:message key="amUser.edit.title"/></button></a>
		    <c:if test="${isAdmin=='yes' || isManager=='yes'}">
			  <a href="<c:url value='/console'/>"><button class="gradient-green-btn"><fmt:message key="project.management"/></button></a>
		    </c:if>
		    <a href="<c:url value='/vtwo/logout'/>"><button class="gradient-green-btn" style="margin-top:5px;"><fmt:message key="button.logout"/></button></a>
		  </div>
		</div>
	  </div>
	  </c:if>
	</div>
    <div id="help" class="item" style="padding-bottom:10px;border:1px dashed #CCC;">
	  <fmt:message key='porject.welcome'/>
	</div>
  </div>
</div>

<script type="text/javascript">
<!--
var searchDisplay = "hidden";
var userLoginDisplay = "hidden";
var helpDisplay = "hidden";

function userLoginIE() {
  if (jp.isIE) {
	 userLogin();
  }
}

var searchWebsite = "http://www.google.com/search?ie=UTF-8&q=";
function displaySearch() {
  if (searchDisplay == "display") {
	searchDisplay = "hidden";
	$("#search").hide();	
	$("#searchBtnFrame").removeClass("active");
  } else {
	searchDisplay = "display";
	$("#search").show();
	if (!jp.isSafari) {
		$('#searchTxt').focus();	
	}
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
	  $("#loginErrorInfo").html(String.format("<fmt:message key='errors.required'/>", "<fmt:message key='amUser.userCd'/>"));
  } else if (jp.isEmpty(password)) {
	  $("#loginErrorInfo").html(String.format("<fmt:message key='errors.required'/>", "<fmt:message key='amUser.password'/>"));
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

var monitor = document.getElementById("monitor");
var photo = document.getElementById('userPhoto');

var localMediaStream = null;
window.URL = window.URL || window.webkitURL;
navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia;

function openCamera() {
  $("#monitor").show();
  $("#snapshotButton").show();
  $("#userPhoto").hide();
  $("#photoButton").hide();
  
  if (navigator.getUserMedia) {
    navigator.getUserMedia({audio: true, video: true}, onSuccess, onFail);
  } else {
    monitor.src = 'somevideo.webm'; // fallback. alert('getUserMedia() is not supported in your browser');
  }
}

function cancelTakePhoto() {
  $("#monitor").hide();
  $("#snapshotButton").hide();
  $("#userPhoto").show();
  $("#photoButton").show();	
}

function onSuccess(stream) {
  monitor.src = window.URL.createObjectURL(stream);
  localMediaStream = stream;
}

var onFail = function(e) {
  showAlert('Fail!');
};

function snapshot() {
  var canvas = document.getElementById("canvas");
  var ctx = canvas.getContext('2d');	
  if (localMediaStream) {
	canvas.width = monitor.videoWidth;
	canvas.height = monitor.videoHeight;  
    ctx.drawImage(monitor, 0, 0);
    var imgData = canvas.toDataURL("image/png");
    var data = imgData.substring(22);
    $.ajax({
      type: "POST",
      url: "<c:url value='/capturePhoto'/>",
      data: {"img":data, "userId":"${user.userId}", "folder":"photo"},
      success: function(msg){
    	if (msg.result=="Success") {
    	  photo.src = imgData;
    	  $("#userThumbnailPhoto").attr("src", imgData);
    	  $("#monitor").hide();
    	  $("#snapshotButton").hide();
    	  $("#userPhoto").show();
    	  $("#photoButton").show();
    	} else {
    	  alert(msg.result);
    	}
      }
    });
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
		$("#userThumbnailPhoto").attr("src", data.result.showUrl);
	  } else {
	    alert(data.result.result);
	  }
	}
  });
});

//-->
</script>
