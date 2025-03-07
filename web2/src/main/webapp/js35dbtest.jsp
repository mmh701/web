<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>

{
"jikwon":
[
<%

Connection conn = null;
PreparedStatement pstmt = null;
ResultSet rs = null;
String result = "";
String gen = request.getParameter("jikwongen");

try {
 	Class.forName("org.mariadb.jdbc.Driver");
 	conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "123");
 } catch (Exception ex) {
 	System.out.println("연결 오류 : " + ex.getMessage());
 	return;
 }

try{
	String sql = "SELECT * FROM jikwon";
	if (gen != null && !gen.equals("all")) {
	    sql += " WHERE jikwongen = '" + gen + "'";  
	}
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, gen);
	rs = pstmt.executeQuery();
	
	while (rs.next()) {
		result += "{";
		result += "\"jikwonno\":" + "\"" + rs.getString("jikwonno") + "\",";
		result += "\"jikwonname\":" + "\"" + rs.getString("jikwonname") + "\",";
		result += "\"jikwonjik\":" + "\"" + rs.getString("jikwonjik") + "\",";
		result += "\"jikwonibsail\":" + "\"" + rs.getString("jikwonibsail") + "\"";
		result += "},";
	}
	if (result.length() > 0) {
		result = result.substring(0, result.length() - 1);
	}
	out.print(result);
} catch (Exception e) {
	System.out.println("처리 오류:" + e.getMessage());
}

%>
]
}
 