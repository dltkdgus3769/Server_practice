<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 27.
  Time: 오후 4:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>사용자 하나 조회하는 화면, 상세보기</h1>
<div>
    <input type="text" name="mno" value="${dto.mno}" readonly>
</div>
<div>
    <input type="text" name="name" value="${dto.name}" placeholder="이름 입력 해주세요." readonly>
</div>
<div>
    <input type="date" name="birthdate" value="${dto.birthdate}" readonly>
</div>
<div>
    <input type="text" name="tel" value="${dto.tel}" readonly>
</div>
<div>
    <a href ="/member/update?mno=${dto.mno}">수정/삭제</a>
    <a href="/member/list">목록가기</a>
</div>
</body>
</html>
