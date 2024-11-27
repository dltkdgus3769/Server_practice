<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 27.
  Time: 오후 4:11
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>memberList 목록화면</h1>
<a href="/member/register">멤버 등록 이동</a>

<h2>JSTL 연습장</h2>
<h3>반복문 foreach, var=변수명, items=데이터목록</h3>
<ul>
    <c:forEach var="dto" items="${list}">
        <li>
            <span>${dto.mno}</span>
            <span><a href="/member/read?mno=${dto.mno}">${dto.name}</a></span>
            <span>${dto.birthdate}</span>
            <span>${dto.tel}</span>
        </li>
    </c:forEach>
</ul>

</body>
</html>
