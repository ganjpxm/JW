<div id="page" data-role="page" class="jpw">
  <div id="header" data-role="header" data-theme="${jqmTheme}" class="small_head" style="height:30px;">
    <div class="left">
    	<a href="javascript:hiddenNavMenuAdv();" data-role="button" data-icon="delete" data-iconpos="notext" 
    		data-mini="true" data-inline="true" style="margin-top:-10px;">Hidden</a>
    </div>
    <div class="right">
	  <div data-role="controlgroup"  data-type="horizontal" >
	    <c:if test="${user==null}">
	    <a href="#popupSignUp" data-rel="popup" data-role="button" data-icon="jp-sign-up" data-iconpos="right" 
		   data-theme="${jqmTheme}"data-transition="fade" data-inline="true"><fmt:message key="button.sign.up"/>
		</a>
		<a href="#popupSignIn" data-rel="popup" data-role="button" data-icon="jp-sign-in" data-iconpos="right" 
		   data-theme="${jqmTheme}" data-transition="fade" data-inline="true"><fmt:message key="button.sign.in"/>
		</a>
		</c:if>
		<c:if test="${user!=null}">
		<a href="#popupUserMenu" data-rel="popup" data-role="button" data-icon="jp-sign-in" data-iconpos="right" 
		   data-theme="${jqmTheme}" data-transition="fade" data-inline="true">${user.userName}
		</a>
		</c:if>
		<a href="#popupThemeMenu" data-rel="popup" data-role="button" data-inline="true" data-icon="jp-theme" 
		   data-iconpos="right" data-transition="fade"><c:if test="${jqmTheme=='a'}"><fmt:message key="common.theme.black"/></c:if><c:if test="${jqmTheme=='b'}"><fmt:message key="common.theme.blue"/></c:if><c:if test="${jqmTheme=='c'}"><fmt:message key="common.theme.slive"/></c:if><c:if test="${jqmTheme=='d'}"><fmt:message key="common.theme.gray"/></c:if><c:if test="${jqmTheme=='e'}"><fmt:message key="common.theme.yellow"/></c:if><c:if test="${jqmTheme=='f'}"><fmt:message key="common.theme.green"/></c:if></a>
		<a href="#popupLangMenu" data-rel="popup" data-role="button" data-inline="true" data-icon="jp-lang" 
		   data-iconpos="right" data-transition="fade"><c:if test="${lang=='zh_CN'}"><fmt:message key="common.language.zh_CN"/></c:if><c:if test="${lang=='en_SG'}"><fmt:message key="common.language.en_SG"/></c:if></a>
      </div>
      <div data-role="popup" id="popupThemeMenu" data-overlay-theme="${jqmTheme}">
		<ul data-role="listview" data-inset="true" style="width:180px;" data-theme="${jqmTheme}">
		  <li data-theme="a"><a data-rel="popup" href="javascript:changeTheme('a')"><fmt:message key="common.theme.black"/></a></li>
		  <li data-theme="b"><a data-rel="popup" href="javascript:changeTheme('b')"><fmt:message key="common.theme.blue"/></a></li>
		  <li data-theme="c"><a data-rel="popup" href="javascript:changeTheme('c')"><fmt:message key="common.theme.slive"/></a></li>
		  <li data-theme="d"><a data-rel="popup" href="javascript:changeTheme('d')"><fmt:message key="common.theme.gray"/></a></li>
		  <li data-theme="e"><a data-rel="popup" href="javascript:changeTheme('e')"><fmt:message key="common.theme.yellow"/></a></li>
		  <li data-theme="f"><a data-rel="popup" href="javascript:changeTheme('f')"><fmt:message key="common.theme.green"/></a></li>
		</ul>
	  </div>
	  <div data-role="popup" id="popupLangMenu" data-overlay-theme="${jqmTheme}">
		<ul data-role="listview" data-inset="true" style="width:200px;" data-theme="${jqmTheme}">
		  <li><a data-rel="popup" href="javascript:changeLang('zh_CN')"><fmt:message key="common.language.zh_CN"/></a></li>
		  <li><a data-rel="popup" href="javascript:changeLang('en_SG')"><fmt:message key="common.language.en_SG"/></a></li>
		</ul>
	  </div>
	  <div data-role="popup" id="popupUserMenu" data-overlay-theme="${jqmTheme}">
		<ul data-role="listview" data-inset="true" style="width:200px;" data-theme="${jqmTheme}">
		  <c:if test="${isAdmin=='yes' || isManager=='yes'}">
		  	<li><a data-rel="popup" href="<c:url value='/console'/>" rel="external"><fmt:message key="project.management"/></a></li>
		  </c:if>
		  <li><a data-rel="popup" href="<c:url value='/loginUserJqmEdit/${user.userId}'/>" rel="external"><fmt:message key="amUser.information"/></a></li>
		  <li><a data-rel="popup" href="<c:url value='/logout'/>" rel="external"><fmt:message key="button.logout"/></a></li>
		</ul>
	  </div>
	  <div data-role="popup" id="popupSignUp" data-theme="c" class="ui-corner-all">
		<form id="amUserSignUpForm" action="<c:url value='/signUp'/>" method="post" data-ajax="true">
			<div style="padding:10px 20px;">
			  <!--<h3>Please sign in</h3>-->
	          <div class="lable"><span class="red">*</span><fmt:message key="amUser.userCd"/> : </div>
      		  <input id="userCd" name="userCd" class="validate[required]"/>
      		  <div class="lable"><span class="red">*</span><fmt:message key="amUser.email"/> : </div>
      		  <input id="email" name="email" class="validate[required,custom[email]]"/>
      		  <div class="lable"><span class="red">*</span><fmt:message key="amUser.password"/> : </div>
      		  <input id="password" name="password" class="validate[required]"/>			  	
	          <a data-role='button' data-mini="false" data-inline="true" href='javascript:signUp();' 
	  				data-theme="${jqmTheme}" ><fmt:message key="button.sign.up"/></a>
			</div>
		</form>
	  </div>
	  <div data-role="popup" id="popupSignIn" data-theme="c" class="ui-corner-all">
		<form id="amUserSignInForm" action="<c:url value='/login'/>" method="post" data-ajax="true">
			<div style="padding:10px 20px;">
			  <!--<h3>Please sign in</h3>-->
	          <div class="lable"><span class="red">*</span><fmt:message key="amUser.userCd"/> : </div>
      		  <input id="userCdOrEmail" name="userCdOrEmail" class="validate[required]"/>
      		  <div class="lable"><span class="red">*</span><fmt:message key="amUser.password"/> : </div>
      		  <input id="userPassword" name="userPassword" class="validate[required]" type="password"/>			  	
	          <a data-role='button' data-mini="false" data-inline="true" href='javascript:signIn();' data-mini="true" 
	  				data-theme="${jqmTheme}" ><fmt:message key="button.sign.in"/></a>
			</div>
		</form>
	  </div>
    </div>
  </div>
  <div id="content" data-role="content">
    <div id="nav_menu" class="nav_menu">
	  <div data-role="controlgroup" data-type="horizontal">
		<c:forEach var="map" items="${mainMenuMaps}" varStatus="rowCounter">
	      <a href="<c:url value='${map.url}'/>" data-role="button" <c:if test="${map.menuId==menuId}">data-theme="${jqmTheme}</c:if>" data-inline="true" rel="external">
	    	${map.menuName}</a>	  
		</c:forEach>
	  </div>
	  <c:if test="${secondMenuMaps!=null}" >
	  	<div data-role="controlgroup" data-type="horizontal" style="margin-top:-10px;" data-mini="true">
		  <c:forEach var="map" items="${secondMenuMaps}" varStatus="rowCounter">
	        <a href="<c:url value='${map.url}'/>" data-role="button" <c:if test="${map.menuId==secondMenuId}">data-theme="${jqmTheme}</c:if>" data-inline="true" rel="external">
	    	${map.menuName}</a>	  
		  </c:forEach>
	  	</div>	
	  </c:if>
    </div>
	<div id="photoSlider" class="nbspslider">
	  <ul>
		<c:forEach var="map" items="${photoMaps}" varStatus="rowCounter">
		  <li><a href="javascript:void(0);"><img src="<c:url value='${map.url}'/>" alt="${map.title}" /></a></li>
		</c:forEach>				
	  </ul>
	</div>
<script type="text/javascript">
<!--
var url = window.location.href;
var index = url.indexOf("#page");
if (index!=-1) {
  url = url.substring(0,index)
}
var photoSliderWidth = $(window).width()*0.8;
if ($(window).width()<=640) photoSliderWidth = $(window).width()*0.95;
var photoSliderHeight = photoSliderWidth*0.2;
$(document).ready(function(){
  $("#photoSlider").nbspSlider({
	  widths: photoSliderWidth,
	  heights : photoSliderHeight,
	  autoplay : 1,  //0,1
	  delays : 6000, 
	  effect : "vertical",
	  speeds : 800,
	  prevId : 'prevBtns',
	  nextId : 'nextBtns',
	  altOpa : 0.5,
	  altBgColor : '#ccc',
	  altHeight : '40px',
	  altShow : 1,
	  altFontColor : 'black',
	  btnFontColor :'red',
	  btnBorderColor : '#ccc',
	  btnBgColor : 'yellow',
	  btnActBgColor : 'green',
	  starEndNoEff : 0,
	  preNexBtnShow : 1,
	  numBtnSty : "square",
	  numBtnShow : "1",
	  starEndNoEff: 1
  });
  
  $("#amUserSignUpForm").validationEngine();
  $("#amUserSignInForm").validationEngine();
});

function changeTheme(theme) {
  $.cookie("jqmTheme", theme, {expires:7});
  //location.reload(true);
  window.location.href = url;
};

function changeLang(lang) {
  $.cookie("lang", lang, {expires:7});
  window.location.href = url;
}

function signUp() {
  if (!$('#amUserSignUpForm').validationEngine('validate')) {
	return false;
  }
  var processing = showLoading(I18N.processing);
  $("#amUserSignUpForm").ajaxSubmit({dataType:'json', success : function(data) {
	hiddenLoading(processing);
	if (data.result=="success") {
	   showAlert(I18N.msg_sign_up_success);
	} else if (data.result=="userCd") {
		$('#userCd').validationEngine('showPrompt', '<fmt:message key="amUser.userCd"/>' + I18N.msg_exist, 'red');
	} else if (data.result=="email") {
		$('#email').validationEngine('showPrompt', '<fmt:message key="amUser.email"/>' + I18N.msg_exist, 'red');
	} else {
	   showAlert(I18N.msg_sign_up_fail);
	}
  }});
}
function signIn() {
  if (!$('#amUserSignInForm').validationEngine('validate')) {
	return false;
  }
  var processing = showLoading(I18N.processing);
  $("#amUserSignInForm").ajaxSubmit({dataType:'json', success : function(data) {
	hiddenLoading(processing);
	if (data.result=="success") {
	  window.location.href = url;
	} else {
	  $('#userCdOrEmail').validationEngine('showPrompt', I18N.msg_error_user_cd_password, 'red');
	}
  }});	
}
var hide = "no";
var nativeBodyHeight = 0;
function hiddenNavMenuAdv() {
	if (hide=="no") {
		$("#nav_menu").hide();
		$("#photoSlider").hide();
		hide = "yes";
		if ($("#leftMenu").height()!=null) {
			if (nativeBodyHeight==0) {
				nativeBodyHeight = $("#leftMenu").height();				
			}
			$("#leftMenu").height($(window).height()-50);
		}
	} else {
		$("#nav_menu").show();
		$("#photoSlider").show();
		hide = "no";
		if ($("#leftMenu").height()!=null) {
			$("#leftMenu").height(nativeBodyHeight);
		}
	}
}
//-->
</script>
