<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 12. 3.
  Time: 오전 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>/food/register 화면입니다.</h1>
<form action="/food/register" method="post">
    <div>
        Title : <input type="text" name="title">
    </div>
    <div>
        DueDate : <input type="date" name="dueDate" value="2024-12-03">
    </div>
    <div>
        Writer : <input type="text" name="writer">
    </div>
    <div>
        finished : <input type="checkbox" name="finished">
    </div>
    <button type="submit">음식작성</button>
</form>
</body>
</html>
