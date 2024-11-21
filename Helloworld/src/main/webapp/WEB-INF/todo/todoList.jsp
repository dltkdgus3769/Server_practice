<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 21.
  Time: 오전 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>todoList 목록화면</h1>
<a href="/todo/register">글쓰기 폼 이동</a>
<h2>더미 데이터 단순 출력</h2>
${list}
<h2>JSTL 연습장</h2>
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
        <c:if test="${dto.tno%2 ==0}">
            <li>
             짝수, ${dto}
            </li>
        </c:if>
        <c:if test="${dto.tno%2 !=0}">
            <li>
            홀수, ${dto}
            </li>
        </c:if>
    </c:forEach>
</ul>

<h3>choose 조건문, if else 확인 해보기.</h3>
<ul>
    <c:forEach var="dto" items="${list}">
        <c:choose>
            <c:when test="${dto.tno%2 ==0}">
                <li>짝수, ${dto}</li>
            </c:when>
            <c:otherwise>
                <li>홀수, ${dto}</li>
            </c:otherwise>

        </c:choose>
    </c:forEach>
</ul>

<h3>JSTL 변수 설정, 사용</h3>
<c:set var = "todoDTO" value="${list[0]}" />

<c:forEach var="dto" items="${list}">
    <c:if test="${dto.tno == todoDTO.tno}">
        ${dto}
    </c:if>
</c:forEach>

</body>
</html>
