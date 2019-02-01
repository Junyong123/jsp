<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%--
	select 변경 (ko -> ja) -- selectLang.jsp
	1. select로 변경한 로케일이 설정된 selectLang.jsp가 화면에 출력되도록
	2. select box option태그가 요청한 로케일로 선택이 되도록 설정
	3. 만약 로케일 파라미터가 없을 경우 기본값은 ko
 --%>
<form action="selectLang.jsp" method="get">
<select id="select" name="select">
	<option value="ko" id="ko">한국어</option>
	<option value="ja" id="ja">日本語</option>
	<option value="en" id="en">English</option>
</select>
</form>

<c:choose>
	<c:when test="${param.select=='ko'}">
		<fmt:setBundle basename="kr.or.ddit.msg.msg" var="msg_ko"/>
	</c:when>
	<c:when test="${param.select=='ja'}">
		<fmt:setBundle basename="kr.or.ddit.msg.msg" var="msg_ja"/>
	</c:when>
	<c:when test="${param.select=='en'}">
		<fmt:setBundle basename="kr.or.ddit.msg.msg" var="msg_eb"/>
	</c:when>
</c:choose>


<fmt:bundle basename="kr.or.ddit.msg.msg">
	<fmt:message key="hello"/>
	<fmt:message key="visitor">
		<fmt:param value="brown"/>
	</fmt:message>
</fmt:bundle>

</body>
</html>