<script type="text/javascript">
  var pageSize = <c:out value='${page.pageSize}'/>;  //per page display record size
  var currentPage = <c:out value='${page.pageNo}'/>; //the number of page
  var totalCount = <c:out value='${page.totalCount}'/>;
  var totalPage = <c:out value='${page.totalPages}'/>;
  var searchFormEl = document.forms[form];
  var url = "<c:out value='${url}'/>";
  /**
   * set per page's record size
   *
   * @param size 
   */
  function setPageSize(size) {
    if (searchFormEl) {
	  submitTargetForm(searchFormEl, 1, size);
	} else {
	  gotoTargetPage(1, size);
	}
  }
  /**
   * use form submit page information
   *
   * @param form: search form
   * @param number: the number of page
   * @param size:  per page's record size
   */
  function submitTargetForm(formEl, number, size) { 
  	addQueryFilterEl(formEl);
  	formEl.appendChild(Jp.createHidden("pageNo", number));
  	formEl.appendChild(Jp.createHidden("pageSize", size));
  	formEl.submit();
  }
  /**
   * go to select page
   *
   * @param number 
   * @param size 
   */ 
  function gotoTargetPage(number, size) {
    window.location.href = url + '?pageNo=' + number + '&pageSize=' + size;
  }
  /**
   * go to select page
   *
   * @param number 
   */
  function gotoSelectedPage(number) {
    if (searchFormEl) {
      submitTargetForm(searchFormEl, number, pageSize);
    } else {
      gotoTargetPage(number, pageSize);
    }
  }
</script>
<div class="pagelist">
  <ul>
  <c:if test="${page.pageNo>0}">
    <c:choose>
      <c:when test="${page.pageNo>2}">
        <li><a title="<fmt:message key="page.first"/>" href="javascript:gotoSelectedPage(1)">|&#60;</a></li>
      </c:when>
      <c:otherwise>
        <li class="disable">|&#60;</li>
      </c:otherwise>
    </c:choose>
    <c:choose>
      <c:when test="${page.pageNo-3>1}">
      <li><a title="<fmt:message key="page.backward"><fmt:param value="${3}"/></fmt:message>"
             href="javascript:gotoSelectedPage(<c:out value='${page.pageNo-3}'/>)">&#60;&#60;</a></li>
      </c:when>
      <c:otherwise>
      <li class="disable">&#60;&#60;</li>
      </c:otherwise>
    </c:choose>
    <c:choose>
      <c:when test="${page.pageNo>1}">
        <li><a title="<fmt:message key="page.previous"/>" href="javascript:gotoSelectedPage(<c:out value='${page.pageNo-1}'/>)">&#60;</a></li>
      </c:when>
      <c:otherwise>
        <li class="disable">&#60</li>
      </c:otherwise>
    </c:choose>
    <c:forEach begin="${page.pageNo}" end="${page.pageNo-1+3}" varStatus="i">
      <c:if test="${i.index-3>0}">
        <li>
          <a title="<fmt:message key="page.at"><fmt:param value="${i.index-3}"/></fmt:message>"
             href="javascript:gotoSelectedPage(<c:out value='${i.index-3}'/>)"><c:out value='${i.index-3}'/></a>
        </li>
      </c:if>
    </c:forEach>
    <li class="current"><c:out value='${page.pageNo}'/></li>
    <c:forEach begin="${page.pageNo+1}" end="${page.pageNo+3}" varStatus="i">
      <c:if test="${i.index<=page.totalPages}">
        <li><a title="<fmt:message key="page.at"><fmt:param value="${i.index}"/></fmt:message>"
                href="javascript:gotoSelectedPage(<c:out value='${i.index}'/>)"><c:out value='${i.index}'/></a></li>
      </c:if>
    </c:forEach>
    <c:choose>
      <c:when test="${page.pageNo<page.totalPages}">
        <li><a title="<fmt:message key="page.next"/>" href="javascript:gotoSelectedPage(<c:out value='${page.pageNo+1}'/>)">&#62;</a></li>
      </c:when>
      <c:otherwise>
        <li class="disable"> &#62;</li>
      </c:otherwise>
    </c:choose>
    <c:choose>
      <c:when test="${page.pageNo+3<page.totalPages}">
        <li><a title="<fmt:message key="page.forward"><fmt:param value="${3}"/></fmt:message>"
                 href="javascript:gotoSelectedPage(<c:out value='${page.pageNo+3}'/>)">&#62;&#62;</a></li>
      </c:when>
      <c:otherwise>
        <li class="disable">&#62;&#62;</li>
      </c:otherwise>
    </c:choose>
    <c:choose>
      <c:when test="${page.pageNo<page.totalPages-1}">
        <li><a title="<fmt:message key="page.last"/>"
               href="javascript:gotoSelectedPage(<c:out value='${page.totalPages}'/>)">&#62;|</a>
        </li>
      </c:when>
      <c:otherwise>
        <li class="disable">&#62;|</li>
      </c:otherwise>
    </c:choose>
  </c:if>
  </ul>
  <span class="pageinfo">
    <span><fmt:message key="page.each"/></span>
    <select class="timestamp" onchange="setPageSize(this.value);" style="height: 22px;">
      <c:forEach items="${pageSizes}" var="pageSize">
        <c:choose>
          <c:when test="${pageSize eq page.pageSize}">
            <option value="<c:out value='${item}'/>" selected="selected"><c:out value='${pageSize}'/></option>
          </c:when>
          <c:otherwise>
            <option value="<c:out value='${pageSize}'/>"><c:out value='${pageSize}'/></option>
          </c:otherwise>
        </c:choose>
      </c:forEach>
    </select>
    <span><fmt:message key="page.record"/></span>&nbsp;&nbsp;
	<span><fmt:message key="page.No"/></span>
    <span><input type="text" value="<c:out value='${page.pageNo}'/>" onMouseOver="this.focus()" onFocus="this.select()"
           class="timestamp" style="width:22px;height:17px;padding-top:1px;margin:1px"></span>
    <span><fmt:message key="page.page"/>&nbsp;&nbsp;</span><fmt:message key="page.total"/><span class="timestamp"><c:out value='${page.totalPages}'/></span>
    <fmt:message key="page.page"/><span class="timestamp"><c:out value='${page.totalCount}'/></span><fmt:message key="page.record"/>
  </span>
</div>

<script type="text/javascript">
<!--
$(function() {
  $('input[type="text"].timestamp').keyup(function(event) {
    if ((this.value.match(/\D/) || this.value > totalPage || this.value < 1) && this.value!="")  {
      alert("<fmt:message key='page.max.pageno.tip'><fmt:param value='${page.totalPages}'/></fmt:message>");
      this.value = currentPage;
      return;
    }
    if (event.keyCode == '13') {
      gotoSelectedPage(this.value);
    }
  });
});
//-->
</script>