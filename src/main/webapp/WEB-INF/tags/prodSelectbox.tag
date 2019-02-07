<%@tag import="java.sql.DriverManager"%>
<%@tag import="java.sql.ResultSet"%>
<%@tag import="java.sql.Statement"%>
<%@tag import="java.sql.Connection"%>
<%@tag import="java.sql.SQLException"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="prod_lgu" type="java.lang.String" required="true" %>
<%	
		String prod_lgu = (String)jspContext.getAttribute("prod_lgu");
		// jspContext 해당 페이지에서 값을 가지고 온다
		
		//DB작업에 필요한 객체변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; // 쿼리문이 select일 경우에 필요함.
		
		try {
			// 1.드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 없어도 되나 있으면 정상적으로 작동하는지 판단하기 좋음
			
			// 2. DB에 접속 (Connection객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "PC06";
			String password = "java";
			conn = DriverManager.getConnection(url,userId,password);
				   // ojdbc6.jar가 사용되는 DriverManager
			
			// 3. Statement객체 생성 -> Connection객체를 이용한다.
			stmt = conn.createStatement();
			
			// 4. SQL문을 Statement객체를 이용하여 실행하고 실행결과를 ResultSet객체에 저장한다.
			String sql = "select * from prod where prod_id like '%"+prod_lgu+"%'"; // 실행할 SQL문
			rs = stmt.executeQuery(sql); // SQL문이 select일 경우에는 executeQuery()메서드를 사용하고
										 // insert,update,delete일 경우는 executeUpdate()메서드 사용
			
			// 5. ResultSet객체에 저장되어 있는 자료를 반복문과 next()메서드를 이용하여 차례로 읽어와 처리한다.
			out.write("실행한 쿼리문 : " + sql+"<br>");
			out.write("=== 쿼리문 실행 결과 ==="+"<br>");
			
			// rs.next() -> ResultSet객체의 데이터를 가리키는 포인터를 다음 레코드로 이동 시키고 그 곳에 자료가 있으면
			//              true , 없으면 false를 반환한다.
			
%>
<select>
<%
			while(rs.next()) { // 한칸 한칸 움직이면서 데이터 유무 판단
%>				
			<option value="<%=rs.getString("prod_id")%>"><%=rs.getString("prod_name") %></option>
<% 
			}
%>
</select>
<%			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassCastException e) {
			e.printStackTrace();
		}finally{
			// 6. 종료(사용했던 자원을 모두 반납한다.)
			if(rs!=null)try {rs.close();}catch(SQLException e2) {}
			if(stmt!=null)try {stmt.close();}catch(SQLException e2) {}
			if(conn!=null)try {conn.close();}catch(SQLException e2) {}
		}
		// localhost/connection/jdbc.jsp
%>