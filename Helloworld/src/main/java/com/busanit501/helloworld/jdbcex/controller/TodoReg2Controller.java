package com.busanit501.helloworld.jdbcex.controller;

import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.service.TodoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name="TodoReg2Controller", urlPatterns = "/todo/register2")
public class TodoReg2Controller extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter Date_Time_Format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/todo/todoReg2.jsp");
        dispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //PRG 패턴
        //POST 처리 후, Redirect, GET 호출, 무한 POST 방지효과
        TodoDTO todoDTO = TodoDTO.builder()
                .title(request.getParameter("title"))
                .dueDate(LocalDate.parse(request.getParameter("dueDate"),Date_Time_Format))
                .build();
        try {
            todoService.register(todoDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("doPost: 글쓰기 처리하는 로직, 디비 연결 전, 리스트로 이동함");
        response.sendRedirect("/todo/list2");
    }
}
