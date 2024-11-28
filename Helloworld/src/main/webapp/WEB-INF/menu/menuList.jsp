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
<a href="/menu/register">메뉴 등록 폼 이동</a>
<h1>menuList 하나 조회 화면</h1>
<a href="/menu/read">메뉴 하나 조회 이동</a>


<h3>반복문 foreach, var=변수명, items=데이터목록</h3>
<ul>
    <c:forEach var="dto" items="${list}">
        <li>
                ${dto}
        </li>
    </c:forEach>
</ul>

<h3>반복문 foreach, var=변수명, items=데이터목록, begin,end. 이용해보기</h3>
<ul>
    <c:forEach var="dto" items="${list}" begin="1" end="5">
        <li>
                ${dto}
        </li>
    </c:forEach>
</ul>

<h3>if 조건문 확인 해보기.</h3>
<ul>
    <c:forEach var="dto" items="${list}">
        <c:if test="${dto.price%2000 ==0}">
            <li>
                짝수, ${dto}
            </li>
        </c:if>
        <c:if test="${dto.price%2000 !=0}">
            <li>
                홀수, ${dto}
            </li>
        </c:if>
    </c:forEach>
</ul>

<h3>JSTL 변수 설정, 사용</h3>
<c:set var = "menuDTO" value="${list[4]}" />

<c:forEach var="dto" items="${list}">
    <c:if test="${dto.price == menuDTO.price}">
        ${dto}
    </c:if>
</c:forEach>

</body>
</html>
