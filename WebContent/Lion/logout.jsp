<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>logout</title>
</head>
<body>
<%
	String path = request.getContextPath();
%>
<script>
	alert("로그아웃 되었습니다.");
	location.href="<%=path %>/main.tm3";
</script>
</body>
</html>