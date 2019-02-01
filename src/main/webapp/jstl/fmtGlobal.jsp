<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 국제화 코드 --%>
<%-- locale 임의 설정 --%>
<fmt:setLocale value="ko"/>

<%
	session.setAttribute("userId", "brown");
%>

<h2>fmt:bundle</h2>
<%-- msg, msg_ko, msg_ja, msg_en : msg -> 모두 포함 --%>
<fmt:bundle basename="kr.or.ddit.msg.msg">
	<%-- bundle은 이 안에만 적용 --%>
	<fmt:message key="hello"/><br>
	<fmt:message key="visitor">
		<fmt:param value="${userId }"/>
	</fmt:message><br>
</fmt:bundle>

<fmt:setLocale value="ja"/>
<fmt:bundle basename="kr.or.ddit.msg.msg">
	<fmt:message key="hello"/><br>
	<fmt:message key="visitor">
		<fmt:param value="${userId }"/>
	</fmt:message><br>
</fmt:bundle>

<h2>fmt:setBundle</h2>
<fmt:setLocale value="en"/>
<fmt:setBundle basename="kr.or.ddit.msg.msg" var="msg_en"/>
<%-- 다른 setBundle로 변경하기 전까지 밑은 계속 이걸로 적용 --%>
<fmt:message bundle="${msg_en }" key="hello"/><br>
<fmt:message bundle="${msg_en }" key="visitor"><br>
	<fmt:param value="${userId }" />
</fmt:message>

</body>
</html>