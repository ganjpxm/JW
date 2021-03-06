<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/common/inc/taglib.jsp" %>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="author" content="GanJianping"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <title id='Description'>Masonry</title>
  <!--[if lt IE 9]>
	<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-1.9.1.${jsSuffix}'/>"></script>
  <script type="text/javascript" src="<c:url value='/resources/js/jquery/plugin/jquery.masonry.min.${jsSuffix}'/>"></script>
  <style type="text/css">
  	#container {background: #FFF; padding: 5px; margin-bottom: 20px; border-radius: 5px; clear: both; 
  		-webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;}
  	.box {margin: 5px; padding: 5px; background: #D8D5D2; font-size: 11px; line-height: 1.4em; float: left;
  		-webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px;}
  </style>
</head>
<body class="jpw2">
<h1 style="text-align:center"><a href="http://masonry.desandro.com">masonry</a></h1>
<div id="container" class="clearfix masonry" style="width:99%">
  <div class="box">
    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede.</p>
  </div>
  <div class="box">
    <p>Morbi purus libero, faucibus adipiscing, commodo quis, gravida id, est. Sed lectus. Praesent elementum hendrerit tortor. Sed semper lorem at felis.</p><p>Sed ac risus. Phasellus lacinia, magna a ullamcorper laoreet, lectus arcu pulvinar risus, vitae facilisis libero dolor a purus. Sed vel lacus. Mauris nibh felis, adipiscing varius, adipiscing in, lacinia vel, tellus. Suspendisse ac urna. Etiam pellentesque mauris ut lectus. Nunc tellus ante, mattis eget, gravida vitae, ultricies ac, leo. Integer leo pede, ornare a, lacinia eu, vulputate vel, nisl.</p>
  </div>
  <div class="box">
    <p>Donec nec justo eget felis facilisis fermentum. Aliquam porttitor mauris sit amet orci. </p>
  </div>
  <div class="box">
    <p>Fusce accumsan mollis eros. Pellentesque a diam sit amet mi ullamcorper vehicula  </p>
  </div>
  <div class="box">
    <h2>Sit amet mi ullamcorper vehicula</h2><p>Ut convallis, sem sit amet interdum consectetuer, odio augue aliquam leo, nec dapibus tortor nibh sed augue.  Sed vel lacus. Mauris nibh felis, adipiscing varius, adipiscing in, lacinia vel, tellus. Suspendisse ac urna. Etiam pellentesque mauris ut lectus. Nunc tellus ante, mattis eget, gravida vitae, ultricies ac, leo. Integer leo pede, ornare a, lacinia eu, vulputate vel, nisl.</p>
  </div>
  <div class="box">
    <p>adipiscing in, lacinia vel, tellus. Suspendisse ac urna. Etiam pellentesque mauris ut lectus.</p>
  </div>
  <div class="box">
    <p>Sit amet mi ullamcorper vehicula</p>
  </div>
  <div class="box">
    <p>Phasellus pede arcu, dapibus eu, fermentum et, dapibus sed, urna.</p>
  </div>
  <div class="box">
    <p>Ut condimentum mi vel tellus. Suspendisse laoreet. Fusce ut est sed dolor gravida convallis. Morbi vitae ante. Vivamus ultrices luctus nunc. Suspendisse et dolor. </p>
    <p>Phasellus pede arcu, dapibus eu, fermentum et, dapibus sed, urna.</p>
  </div>
  <div class="box">
    <p>Sed ac risus. Phasellus lacinia, magna a ullamcorper laoreet, lectus arcu pulvinar risus, vitae facilisis libero dolor a purus. Sed vel lacus. </p>
  </div>
  <div class="box">
    <p>Morbi purus libero, faucibus adipiscing, commodo quis, gravida id, est. Sed lectus. Praesent elementum hendrerit tortor. Sed semper lorem at felis.</p>
  </div>
  <div class="box">
    <p>Vestibulum volutpat, lacus a ultrices sagittis,</p>
    <p>Fusce accumsan mollis eros. Pellentesque a diam sit amet mi ullamcorper vehicula  </p>
  </div>
</div>
</body>
</html>
<script>
<!--
var width;
function scaleWidth() {
  var winWidth = $(window).width();
  if (winWidth>1280) {
	  winWidth=winWidth-118;
	  width = winWidth/4;
  }	else if (winWidth>=1000) {
	  winWidth = winWidth-90;
	  width = winWidth/3;
  }	else if (winWidth>=600) {
	  winWidth=winWidth-70;
	  width = winWidth/2;
  } else {
	  winWidth=winWidth-45;
	  width = winWidth;
  }
  $("#container div.box").css("width", width);	
  $('#container').masonry({itemSelector: '.box'});
}

$(function() {
  scaleWidth();
  $(window).resize(function() {
	scaleWidth();
  });
});

//-->
</script>
