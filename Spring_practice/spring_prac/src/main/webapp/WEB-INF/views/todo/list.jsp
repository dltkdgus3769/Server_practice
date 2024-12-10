<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>

<body>
<div class="container-fluid">


    <div class="row">
        <!--        <h1>Header</h1>-->
        <!--        네비게이션바 추가 시작-->
        <div class="row">
            <div class="col">
                <nav class="navbar navbar-expand-lg bg-body-tertiary">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#">Navbar</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                                aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarNav">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Features</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Pricing</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>

            </div>
        </div>
        <!--        네비게이션바 추가 끝-->
        <%--    검색 및 필터 화면 추가--%>
        <div class="row content">
            <div class="col">
                <!--        카드 시작 부분-->
                <div class="card">
                    <%--                <div class="card-header">--%>
                    <%--                    검색 및 필터--%>
                    <%--                </div>--%>
                    <div class="card-body">
                        <h5 class="card-title">검색 및 필터</h5>
                        <form action="/todo/list" method="get">
                            <input type="hidden" name="size" value="${pageRequestDTO.size}">
                            <div class="mb-3">
                                <input type="checkbox" name="finished" ${pageRequestDTO.finished ?"checked":""}>완료여부
                            </div>
                            <div class="mb-3">
                                <input type="checkbox" name="types" value="t" ${pageRequestDTO.checkType("t")?"checked":""}>제목
                                <input type="checkbox" name="types" value="w" ${pageRequestDTO.checkType("w")?"checked":""}>작성자
                                <input type="text" name="keyword" class="form-control" value="${pageRequestDTO.keyword}">
                            </div>
                            <div class="input-group mb-3 dueDateDiv">
                                <input type="date" name="from" class="form-control" value="${pageRequestDTO.from}">
                                <input type="date" name="to" class="form-control" value="${pageRequestDTO.to}">
                            </div>
                            <div class="input-group mb-3">
                                <div class="float-end">
                                    <button class="btn btn-primary" type="submit">검색</button>
                                    <button class="btn btn-secondary clearBtn" type="reset">초기화</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!--        카드 끝 부분-->
            </div>
        </div>
        <%--    검색 및 필터 화면 추가--%>
        <!--        class="row content"-->
        <div class="row content">
            <!--        col-->
            <div class="col">
                <!--        카드 시작 부분-->
                <div class="card">
                    <%--                    <div class="card-header">--%>
                    <%--                        Featured--%>
                    <%--                    </div>--%>
                    <div class="card-body">
                        <%--                        Todo List 부분 작성--%>
                        <h5 class="card-title">리스트 목록</h5>
                        <button type="button" class="btn btn-primary insertTodoBtn">글쓰기</button>
                        <table class="table">
                            <thead>
                            <tr>
                                <%--                                    소제목--%>
                                <th scope="col">Tno</th>
                                <th scope="col">Title</th>
                                <th scope="col">Writer</th>
                                <th scope="col">DueDate</th>
                                <th scope="col">Finished</th>
                            </tr>
                            </thead>
                            <tbody>

                            <%--                                본문--%>
                            <c:forEach items="${pageResponseDTO.dtoList}" var="dto">
                                <tr>
                                    <th scope="row"><c:out value="${dto.tno}"></c:out></th>
                                    <td><a href="/todo/read?tno=${dto.tno}&${pageRequestDTO.link}"
                                           class="text-decoration-none"><c:out
                                            value="${dto.title}"></c:out></a></td>
                                    <td><c:out value="${dto.writer}"></c:out></td>
                                    <td><c:out value="${dto.dueDate}"></c:out></td>
                                    <td><c:out value="${dto.finished}"></c:out></td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                        <%--                        Todo List 부분 작성--%>
                        <div class="float-end">
                            <ul class="pagination">
                                <%--                                    이전 버튼--%>
                                <c:if test="${pageResponseDTO.prev}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${pageResponseDTO.end - 10}">Previous</a>
                                    </li>
                                </c:if>
                                <%--                                    출력할 페이지 개수,10개--%>
                                <c:forEach begin="${pageResponseDTO.start}" end="${pageResponseDTO.end}" var="num">

                                    <li class="page-item ${pageResponseDTO.page == num ? "active":""}">
                                        <a class="page-link" data-num="${num}">${num}</a>
                                    </li>

                                </c:forEach>

                                <%--                                    다음 버튼--%>
                                <c:if test="${pageResponseDTO.next}">
                                    <li class="page-item">
                                        <a class="page-link" data-num="${pageResponseDTO.end + 1}">Next</a>
                                    </li>
                                </c:if>
                            </ul>
                        </div>
                    </div>
                </div>
                <!--        카드 끝 부분-->
            </div>
            <!--        col-->
        </div>
        <!--        class="row content"-->
    </div>
    <div class="row content">
        <h1>Content</h1>
    </div>
    <div class="row footer">
        <!--        <h1>Footer</h1>-->
        <div class="row fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>
</div>
<script>
    const serverValidResult = {}
    <c:forEach items="${errors}" var="error">
    serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
    </c:forEach>
    console.log(serverValidResult)
</script>
<script>
    document.querySelector(".insertTodoBtn").addEventListener("click",
        function (e) {
            // 글쓰기폼으로 가야함. 그러면, 필요한 준비물 tno 번호가 필요함
            self.location = "/todo/register"
                , false
        })

    //페이지네이션, 이동할 페이지 번호 data-num
    //해당 번호를 가지고, 링크 이동하는 이벤트 추가.
    document.querySelector(".pagination").addEventListener("click",
        function (e) {
            e.preventDefault()
            e.stopPropagation()
            //a 태그에 접근 하려면, 해당 요소 선택자 필요.
            const target = e.target
            // a 태그인 경우에 이벤트 리슨너 동작
            if (target.tagName !== "A") {
                return
            }
            const num = target.getAttribute("data-num")

            //백틱, 숫자 키보드 1번 왼쪽
            // self.location = `/todo/list?page=\${num}`
            const formObj = document.querySelector("form")
            formObj.innerHTML += `<input type='hidden' name='page' value='\${num}'>`
            formObj.submit()
        }, false)

    document.querySelector(".clearBtn").addEventListener("click",
        function (e) {
            e.preventDefault();
            e.stopPropagation();

            self.location = "/todo/list"
        }, false)


</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
