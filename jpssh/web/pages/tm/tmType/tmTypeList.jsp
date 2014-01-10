<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
	<head>
		<title>User List</title>
	</head>
	<body>
		User List
		<table>
			<c:forEach items="${key}" var="user">
				<tr>
					<td>
						<a href="<c:url value="/user/showUser/${user.userName}.html"/>">
						${user.userName}
                        </a>
					</td>
				</tr>
			</c:forEach>
		<table>
	</body>
</html>
