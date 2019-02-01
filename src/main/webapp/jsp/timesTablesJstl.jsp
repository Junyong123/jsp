<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
		스크립틀릿을 사용하지 않고 jstl과 el만을 사용해서 작성
	 --%>
	<table border="1">
	<%	for(int i=1; i<=9; i++){ %>
			<tr>
	<% 		for(int j=2; j<=9; j++){ %>
				<td><%=j %> * <%=i %> = <%=(j*i) %></td>
	<%		} %>
			</tr>
	<%	} %>
	</table>
	
	<table border="1">
	<c:forEach begin="1" end="9" var="staus">
		<tr>
		<c:forEach begin="2" end="9" var="staus2">
			<td>${staus } * ${staus2 } = ${staus * staus2 }</td>
		</c:forEach>
		</tr>
	</c:forEach>
	</table>
</body>
</html>