<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 21.
  Time: 오전 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>jdbcex - menu의 글쓰기 폼 화면</h1>
<form action="/menu/register2" method="post">
    <div>
        <input type="text" name="menu" placeholder="메뉴 입력">
    </div>
    <div>
        <input type="text" name="price" placeholder="가격 입력">
    </div>
    <div>
        <input type="text" name="description" placeholder="설명 입력">
    </div>
    <button type="reset">리셋</button>
    <button type="submit">등록</button>
</form>
</body>
</html>
