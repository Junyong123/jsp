<%@page import="org.apache.tomcat.dbcp.dbcp2.BasicDataSource"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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

		//DB작업에 필요한 객체변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; // 쿼리문이 select일 경우에 필요함.
		
		BasicDataSource bds = null;
		
		try {
			
			// Connection pool 준비
			bds = new BasicDataSource();
			bds.setUsername("pc06");
			bds.setUrl("jdbc:oracle:thin:@localhost:1521/xe");
			bds.setPassword("java");
			bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			bds.setInitialSize(5);
			
			// 위에 입력한 사항을 bds를 이용해 연결
			conn = bds.getConnection(); 
			
			// 3. Statement객체 생성 -> Connection객체를 이용한다.
			stmt = conn.createStatement();
			
			// 4. SQL문을 Statement객체를 이용하여 실행하고 실행결과를 ResultSet객체에 저장한다.
			String sql = "select * from lprod"; // 실행할 SQL문
			rs = stmt.executeQuery(sql); // SQL문이 select일 경우에는 executeQuery()메서드를 사용하고
										 // insert,update,delete일 경우는 executeUpdate()메서드 사용
			
			// 5. ResultSet객체에 저장되어 있는 자료를 반복문과 next()메서드를 이용하여 차례로 읽어와 처리한다.
			out.write("실행한 쿼리문 : " + sql+"<br>");
			out.write("=== 쿼리문 실행 결과 ==="+"<br>");
			
			// rs.next() -> ResultSet객체의 데이터를 가리키는 포인터를 다음 레코드로 이동 시키고 그 곳에 자료가 있으면
			//              true , 없으면 false를 반환한다.
			
			while(rs.next()) { // 한칸 한칸 움직이면서 데이터 유무 판단
				// 컬럼의 자료를 가져오는 방법
				// 방법1) rs.get자료형이름("컬러명")
				// 방법2) rs.get자료형이름(컬럼번호) => 컬럼번호는 1부터 시작
				out.write("lprod_id : "+rs.getInt("lprod_id")+"<br>");
				out.write("lprod_gu : "+rs.getString("lprod_gu")+"<br>");
				out.write("lprod_nm : "+rs.getString("lprod_nm")+"<br>");
				out.write("================================"+"<br>");
			}
			
			out.write("출력 끄으읕");
			
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
		bds.close();
%>

</body>
</html>