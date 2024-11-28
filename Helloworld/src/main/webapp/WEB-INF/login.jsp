<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 27.
  Time: 오후 3:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Login Page</h1>
<c:if test="${param.result == 'error'}">
    <h1>로그인 정보 다시 확인 후 로그인 해주세요.</h1>
</c:if>
<form action="/login" method="post">
    <input type="text" name="mid">
    <input type="text" name="mpw">
    <input type="checkbox" name="auto">자동로그인
    <button type="submit">로그인</button>
</form>
</body>
</html>
