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

<form action="${pageContext.request.contextPath}/selectLang" method="get">
	<select id="selectlang">
		<option value="ko" id="ko">한국어</option>
		<option value="ja" id="ja">日本語</option>
		<option value="en" id="en">English</option>
	</select><br>
</form>

<fmt:setLocale value="${selectlang}" />
<fmt:bundle basename="kr.or.ddit.msg.msg">
	<fmt:message key="hello"/>
	<fmt:message key="visitor">
		<fmt:param value="brown"/>
	</fmt:message><br>
</fmt:bundle>
<script>
$(document).ready(function(){
	$("#selectlang > option").attr("selected",false);
	$("#selectlang > option[value=${lang}]").attr("selected",true);
	
	$("#selectlang").on("change",function(){
		$("#frm").submit();
	});
});
</script>
</body>
</html>