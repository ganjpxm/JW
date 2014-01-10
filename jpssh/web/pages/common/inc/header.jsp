<c:if test="${not empty param['error']}">
  <span class="error">${param['errors']}</span>
</c:if>
<c:if test="${not empty param['success']}">
  <span class="success">${param['success']}</span>
</c:if>

<div id="msgDiv" style="display:none;z-index:1;width:300px;position:absolute;left:5;top:5;" class="msgDiv"> 
  <div class="msgtitle">
    <div id="titleTxt" class="title"><fmt:message key="msg.title"/></div>
	<img class="icon" onClick="hide()" src="<c:url value='/resource/style/base/image/icon/wclose.gif'/>"/>	
  </div>
  <div id="content" class="content" style="padding:4px; display:block">
  </div>
</div>

<c:if test="${not empty info}">
<script>
	var msg = "";
	 <c:forEach var="v" items="${info}">
        msg += '<c:out value="${v}" escapeXml="false"/>' + '<br/>';
    </c:forEach>
    <c:remove var="info"/>
    document.getElementById('content').innerHTML=msg;
    document.getElementById('msgDiv').style.display = "block";
</script>
</c:if>

<c:if test="${not empty success}">
<script>
	var msg = "";
	 <c:forEach var="v" items="${success}">
        msg += '<c:out value="${v}" escapeXml="false"/>' + '<br/>';
    </c:forEach>
    <c:remove var="info"/>
    document.getElementById('content').innerHTML=msg;
    document.getElementById('msgDiv').style.display = "block";
</script>
</c:if>

<c:if test="${not empty error}">
<script>
	var msg = "";
	 <c:forEach var="v" items="${error}">
        msg += '<c:out value="${v}" escapeXml="false"/>' + '<br/>';
     </c:forEach>
    <c:remove var="error"/>
    document.getElementById('content').innerHTML=msg;
    document.getElementById('titleTxt').innerHTML="<fmt:message key='msg.error.title'/>";
    document.getElementById('msgDiv').style.display = "block";
</script>
</c:if>

<script type="text/javascript">
<!--
function hide() {
	document.getElementById('msgDiv').style.display = "none";
}
setTimeout(hide,5000);
//-->
</script>
