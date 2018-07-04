<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String name = (String)session.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Main Page</title>
</head>
<body>
<h1>Main Page</h1>
<% if (name != null) { %>
	<%=name %> 님 환영합니다. <br>
	<a href="<%=path %>/admin.tm3">admin page</a><br>
	<a href="<%=path %>/logout.tm3">logout</a><br>
<% } else { %>
	<h3>로그인 또는 회원가입을 하세요.</h3>
	<a href="<%=path %>/login.tm3">login</a><br>
	<a href="<%=path %>/joinus.tm3">join us</a><br>
<% } %>
<br><br>
<div class="container">
  <h3>Hello</h3>
  <p>Create a stacked progress bar by placing multiple bars into the same div with class .progress:</p> 
  <div class="progress">
    <div class="progress-bar progress-bar-success" role="progressbar" style="width:40%">
      Free Space
    </div>
    <div class="progress-bar progress-bar-warning" role="progressbar" style="width:10%">
      Warning
    </div>
    <div class="progress-bar progress-bar-danger" role="progressbar" style="width:20%">
      Danger
    </div>
  </div>
  <ul class="pagination pagination-lg">
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
  </ul>
</div>
</body>
</html>