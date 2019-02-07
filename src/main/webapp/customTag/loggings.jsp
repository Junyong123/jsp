<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- localhost/customTag/loggings.jsp --%>

	<h2>loggings.tag</h2>
	logging.tag 출력<br>
	<tags:loggings/>
	
	logging.tag 출력<br>
	<tags:loggings/>
	
	<h2>colorLoggings.tag</h2>
	colorLoggings.tag 출력<br>
	<tags:colorLogging color= "blue" size="50"/>
	<tags:colorLogging color= "red" size="100"/>
	
</body>
</html>