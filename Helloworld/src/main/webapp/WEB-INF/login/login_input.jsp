<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 20.
  Time: 오후 2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>미니 실습 로그인 화면 접근(서블릿)</h1>
<form action="/login/result" method="post">
    <input type="text" name="ID">
    <input type="password" name="Password">
    <button type="submit">전송</button>
</body>
</html>
