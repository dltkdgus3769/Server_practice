<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 27.
  Time: 오전 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>menu - tno 번호로 수정</h1>
<form action="/menu/update?tno=${dto.tno}" method="post">
    <div>
        <input type="text" name="tno" value="${dto.tno}" readonly>
    </div>
    <div>
        <input type="text" name="menu" value="${dto.menu}" placeholder="메뉴 입력 해주세요.">
    </div>
    <div>
        <input type="text" name="price" value="${dto.price}">
    </div>
    <div>
        <input type="text" name="description" value= "${dto.description}" >
    </div>

    <div>
        <button type="submit">수정하기</button>
        <br>
    </div>
</form>
<form action="/menu/delete?tno=${dto.tno}" method="post">
    <button type="submit">삭제</button>
</form>
<a href="/menu/list2">목록가기</a>
</body>
</html>
