<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.rangers.service.RangersService"%>
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
	<%-- 
		특정 스코프에 속성을 생성
		default는 scope = "page" 적용
	--%>
	 
	<c:set var="userId" value="brown" scope="session" /><%-- var 변수선언 --%>
	<%-- 
		pageContext.setAtribute("uesrId","brown"); 과 동일 
		 session.setAttribute("uesrId","brown"); 과 동일
	--%>
	
	<%-- userId(표현식-page) : <%=pageContext.getAttribute("userId") %><br> --%>
	userId(표현식-session) : <%= session.getAttribute("userId") %><br>
	userId(el) : ${userId }<br>
	=====================================
	
	<%--
		pageContext.setAttribute("test",new RangerVO("brown","브라운",15);
		pageContext.removeAttribute("test");
	--%>
	객체 생성 
	<c:set var ="test" value ="<%= new RangerVO(\"brown\",\"브라운\",15) %>" />
	<%-- <c: set var ="test" value ="${RangerVO("brown","브라운",15 } /> 도 가능--%>
	test : ${test }<br>
	
	삭제 
	<c:remove var="test"/>
	test : ${test }<br>
	
	 <%
	 	RangerVO rangerVO = new RangerVO("brown","브라운",8);
	 	session.setAttribute("ranger", rangerVO);
	 %>
	
	<c:set target="${ranger }" property="age" value="10"/>
	ranger - 나이변경 : ${ranger } <br>
	<%-- localhost/jstl/core.jsp --%>
	
	<h2>core - if</h2>
	<%-- pageContext에 code 속성 추가 --%>
	<c:set var = "code" value="01" /> <%-- 데이터 임의로 삽입 --%>
	
	<c:if test="${code == '00'}">
		<span>code가 00 값입니다</span>
	</c:if>
	
	<c:if test="${code == '01'}">
		<span>code가 01 값입니다</span>
	</c:if>
	
	<%-- if elseif문은 choose로 사용 --%>
	<h2>core - choose</h2>
	<c:remove var="code"/>
	<c:set var="code" value="03" scope="request"/>
	<%-- request.setAttribute("code","03") --%>
	<%-- 
	<c:choose>
		<c:when test="${code == '00' }"> code가 ${code)값 입니다 </c:when>
		<c:when test="${code == '01' }"> code가 ${code)값 입니다 </c:when>
		<c:when test="${code == '02' }"> code가 ${code)값 입니다 </c:when>
		<c:when test="${code == '03' }"> code가 ${code)값 입니다 </c:when>
		<c:when test="${code == '04' }"> code가 ${code)값 입니다 </c:when>
		<c:otherwise> code가 ${code}입니다.</c:otherwise>
	</c:choose>
	--%>
	
	<h2>core - forEach</h2>
	<%
		RangersService rangerService = new RangersService();
		request.setAttribute("rangersList",rangerService.getRangerVoAll());
	%>
	
	<h3>향상된 for문</h3>
	<c:forEach items="${rangersList }" var="ranger">
		${ranger.name } / ${ranger.alias } / ${ranger.age }<br>
	</c:forEach>
	
	<%-- 
		for(int i=0;i<rangerList.size();i++)
		syso(rangerList.get(i).name);
	--%>
	<h3>일반 for문</h3>
	<c:forEach begin="0" end="10" var="staus" step="3">
		<span> test${staus } </span>
	</c:forEach>
	<%-- step에 -값을 집어 넣고자 하면 안에 숫자-i라는 변수 하나 생성해서 해야한다  --%>
	
	<c:forEach begin="0" end="${rangersList.size()-1 }" var="i">
		${rangersList.get(i).name } / ${rangersList.get(i).alias } / ${rangersList.get(i).age }
	</c:forEach>
	
	
	<h2>core - forEach (map)</h2>
	<%
		Map<String,String> map = new HashMap<String,String>();
		map.put("ranger1","brown");
		map.put("ranger2","cony");
		map.put("ranger3","sally");
		pageContext.setAttribute("map", map);
	%>
	
	<c:forEach items="${map }" var="entry">
		${entry.key } / ${entry.value }<br>
		<%-- key와 value를 따로 가지고 오는게 가능하다 --%>
	</c:forEach>
	
	
</body>
</html>