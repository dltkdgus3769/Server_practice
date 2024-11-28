<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 21.
  Time: 오전 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>menuList 목록화면</h1>

<h2>${MenuloginInfo.mname}님 환영합니다.</h2>
<form action="/menuLogout" method="post">
    <button type="submit">로그아웃테스트</button>
</form>

<a href="/menu/register2">메뉴 등록 폼 이동</a>
<h1>menuList 하나 조회 화면</h1>
<a href="/menu/read2?tno=1">메뉴 하나 조회 이동</a>


<h3>반복문 foreach, var=변수명, items=데이터목록</h3>
<ul>
    <c:forEach var="dto" items="${list}">
        <li>
            <span>${dto.tno}</span>
            <span><a href="/menu/read2?tno=${dto.tno}">${dto.menu}</a></span>
            <span>${dto.price}</span>
            <span>${dto.description}</span>
        </li>
    </c:forEach>
</ul>


</body>
</html>
