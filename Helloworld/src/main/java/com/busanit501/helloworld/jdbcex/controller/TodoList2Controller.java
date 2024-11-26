package com.busanit501.helloworld.jdbcex.controller;


import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@WebServlet(name="TodoList2Controller", urlPatterns = "/todo/list2")
public class TodoList2Controller extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("doGet TodoList2Controller 확인");
        List<TodoDTO> todoList = null;
        try {
            todoList = todoService.listAll();
            request.setAttribute("list",todoList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/todo/todoList2.jsp");
            dispatcher.forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
