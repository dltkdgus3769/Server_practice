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
<h1>멤버 수정</h1>
<form action="/member/update?mno=${dto.mno}" method="post">
    <div>
        <input type="text" name="mno" value="${dto.mno}" readonly>
    </div>
    <div>
        <input type="text" name="name" value="${dto.name}" placeholder="이름 입력 해주세요.">
    </div>
    <div>
        <input type="date" name="birthdate" value="${dto.birthdate}">
    </div>
    <div>
        <input type="text" name="tel" value="${dto.tel}">
    </div>

    <div>
        <button type="submit">수정하기</button>
        <br>
    </div>
</form>
<form action="/member/delete?mno=${dto.mno}" method="post">
    <button type="submit">삭제</button>
</form>
<a href="/todo/list2">목록가기</a>
</body>
</html>
