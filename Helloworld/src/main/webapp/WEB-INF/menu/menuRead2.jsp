<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 21.
  Time: 오후 4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>jdbcex - tno 번호로 하나 조회하는 화면, 상세보기</h1>
<div>
    <input type="text" name="tno" value="${dto.tno}" readonly>
</div>
<div>
    <input type="text" name="title" value="${dto.menu}" placeholder="메뉴 입력 해주세요." readonly>
</div>
<div>
    <input type="text" name="price" value="${dto.price}" readonly>
</div>
<div>
    <input type="text" name="description" value="${dto.description}" readonly>
</div>
<div>
    <a href ="/menu/update?tno=${dto.tno}">수정/삭제</a>
    <a href="/menu/list2">목록가기</a>
</div>
</body>
</html>
