<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="jpw" prefix="jp" %>
<c:choose>
  <c:when test="${lang=='zh_CN'}"><fmt:setLocale value="zh_CN" scope="session"/></c:when>
  <c:when test="${lang=='en_SG'}"><fmt:setLocale value="en_SG" scope="session"/></c:when>
</c:choose>
