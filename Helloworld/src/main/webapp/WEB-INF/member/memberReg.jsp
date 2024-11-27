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
  <h1>jdbcex - 멤버 등록 폼 화면</h1>
  <form action="/member/register" method="post">
      <div>
          <input type="text" name="name" placeholder="이름 입력">
      </div>
      <div>
          <input type="date" name="birthdate">
      </div>
      <div>
          <input type="text" name="tel">
      </div>
      <button type="reset">리셋</button>
      <button type="submit">등록</button>
  </form>
  </body>
</html>
