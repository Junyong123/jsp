<%@page import="kr.or.ddit.rangers.model.RangerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	jstl을 사용 하기 위한 준비 : jsp 디렉티브(taglib)를 이용하여 사용하고자 하는 라이브러리 선언
							prefix = "임의로 작성 가능하나 일반적으로 사용하는 이름" / uri= "자동완성기능"
							core : c , format : fmt , function : fn 
	
 --%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>core : set</h2>
	<%-- 특정 스코프에 속성을 생성
		 default는 scope = "page" 적용
	 --%>
	 
	<c:set var="userId" value="brown" scope="session" /><%-- var 변수선언 --%>
	<%-- pageContext.setAtribute("uesrId","brown"); 과 동일 
		 session.setAttribute("uesrId","brown"); 과 동일
	--%>
<%-- 	userId(표현식-page) : <%=pageContext.getAttribute("userId") %><br> --%>
	userId(표현식-session) : <%= session.getAttribute("userId") %><br>
	userId(el) : ${userId }<br>
	=====================================
	
	 <%
	 	RangerVO rangerVO = new RangerVO("brown","브라운",8);
	 	session.setAttribute("ranger", rangerVO);
	 %>
	
	<c:set target="${ranger }" property="age" value="10"/>
	ranger - 나이변경 : ${ranger } <br>
	<%-- localhost/jstl/core.jsp --%>
</body>
</html>