<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// Cookie[]로 return 하는 getCookies()
	Cookie[] cookies = request.getCookies();
	
	for(Cookie cookie : cookies){
		out.write("cookeName : "+cookie.getName() + "/ cookieValue = " + cookie.getValue()+"<br>");
	}
	
	// 1.웹브라우저에서 주소줄에 : localhost/cookieList
	// 2. /cookieList url을 처리하는 CookieSevlet
	// 3. 웹 브라우저의 주소줄로 요청을 보냈기 때문에 get 요청이 되고
	// 4. cookiesServlet의 doGet()가 실행
	// 5. request.getRequestDisPatcher("jsp/cookiList.jsp").forward(request,response)을 이용하여 주소 위임
%>

	<h3>session</h3>
	session.getId() : <%= session.getId() %>

	<h3>서버 사이드 cookie 생성</h3>
	<%
		Cookie newCookie = new Cookie("serverSideCookie","serverSideValue");
		
		// setMaxAge는 초단위 값을 인자로 받는다
		// 5일 : 5*24(hour)*60*(minute)*60(second) 초
		newCookie.setMaxAge(5*24*60*60);
		response.addCookie(newCookie);
	%>
	
	
	
</body>
</html>