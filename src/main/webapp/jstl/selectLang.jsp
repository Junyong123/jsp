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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<%--
	select 변경 (ko -> ja) -- selectLang.jsp
	1. select로 변경한 로케일이 설정된 selectLang.jsp가 화면에 출력되도록
	2. select box option태그가 요청한 로케일로 선택이 되도록 설정
	3. 만약 로케일 파라미터가 없을 경우 기본값은 ko
 --%>

<%
	// localhost/jstl/selectlang.jsp -> 직접접근 selectlang 파라미터 미존재
	// form (button -> select ) ==> lang 파라미터 존재
	String lang = request.getParameter("selectlang");
	lang = lang == null ? "ko" : lang;
	// el사용을 위한 속성 주기 페이지든 리퀘스트든 상관없음
	pageContext.setAttribute("lang", lang);
%>

<form action="${pageContext.request.contextPath }/jstl/selectLang.jsp" id="frm">
	<select id="selectlang" name="selectlang">
		<option value="ko" id="ko" <c:if test="${lang=='ko' }">selected</c:if>>한국어</option>
		<option value="ja" id="ja" <c:if test="${lang=='ja' }">selected</c:if>>日本語</option>
		<option value="en" id="en" <c:if test="${lang=='en' }">selected</c:if>>English</option>
	</select><br>
	<%-- 버튼은 button 따로 효과를 줘야한다 / submit 따로 효과를 주어지지 않아도 기본적으로 전송한다 --%>
</form>


<%-- <c:choose> --%>
<%-- 	<c:when test="${param.select=='ko'}"> --%>
<%-- 		<fmt:setBundle basename="kr.or.ddit.msg.msg" var="msg_ko"/> --%>
<%-- 	</c:when> --%>
<%-- 	<c:when test="${param.select=='ja'}"> --%>
<%-- 		<fmt:setBundle basename="kr.or.ddit.msg.msg" var="msg_ja"/> --%>
<%-- 	</c:when> --%>
<%-- 	<c:when test="${param.select=='en'}"> --%>
<%-- 		<fmt:setBundle basename="kr.or.ddit.msg.msg" var="msg_eb"/> --%>
<%-- 	</c:when> --%>
<%-- </c:choose> --%>

<fmt:setLocale value="${selectlang }" />
<fmt:bundle basename="kr.or.ddit.msg.msg">
	<fmt:message key="hello"/>
	<fmt:message key="visitor">
		<fmt:param value="brown"/>
	</fmt:message><br>
</fmt:bundle>

<script>
$(document).ready(function(){
	$("#selectlang").on("change",function(){
		$("#frm").submit();
		//form을 바로 실행
	});
});
</script>

</body>
</html>