<%@page import="data.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String id = (String)session.getAttribute("sessionId");
	
	MemberDAO dao = new MemberDAO();
	
	String name = dao.getName(id);
%>
<div style="margin: 0 auto; width: 100%;">
	<b><%=name%>님은 현재 로그인 중입니다.</b>
	<br><br>
	<button type="button" class="btn btn-del" onclick="location.href='login/logoutAction.jsp'">로그아웃</button>
	<button type="button" class="btn btn-update" onclick="location.href='index.jsp'">메인페이지</button>
	<button type="button" class="btn btn-add" onclick="location.href='index.jsp?go=apply/applyMain.jsp&menu_one=8&menu_two=9'">참가신청 페이지</button>
</div>
</html>