<%@page import="javax.naming.*"%>
<%@page import="java.sql.*" %>
<%@page import="javax.sql.*" %>
<%@page import="oracle.jdbc.driver.OracleDriver" %>
<%@page import="testPjt.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%

request.setCharacterEncoding("UTF-8");
String URL = "jdbc:oracle:thin:@localhost:1521:XE";

String USER = ""; //oracle DB id 입력
String PASSWORD = ""; // oracle DB pw 입력

String TABLE_NAME = "NUMBER_GAME_RANK";
String COL_NAME = "NAME";
String COL_SCORE = "SCORE";

request.setCharacterEncoding("UTF-8");
String returns = "";
String userNAME = request.getParameter("user_name");
String userSCORE = request.getParameter("score");

String[] names = new String[5];
int[] scores = new int[5];

%>

<%
Connection conn = null;
Statement stmt = null;
ResultSet resultSet = null;
String name = null;
int rankNumber = 5;
int i = 0;


try {
	
	System.out.println("Get Ranking");
	
	DriverManager.deregisterDriver(new OracleDriver());
	conn = DriverManager.getConnection(URL, USER, PASSWORD);
	stmt = conn.createStatement();
	
	
	//sql 문 내의 데이터를 5개 가져기.
	String sql_insert = "SELECT * FROM (SELECT * FROM " +TABLE_NAME+ " ORDER BY "+COL_SCORE+" DESC) WHERE ROWNUM<="+rankNumber;
	resultSet = stmt.executeQuery(sql_insert);
	
	while(resultSet.next()) {
		
		returns += resultSet.getString("NAME")+"#"+ Integer.toString(resultSet.getInt("SCORE"))+"@";
	}
	out.println(returns);
	
%>

<%
}catch(SQLException e) {
	e.printStackTrace();	
	%>

<%
}finally {
	try{
		stmt.close();
		conn.close();
	} catch (Exception e) {
		
		
		e.printStackTrace();
	}
}
%>