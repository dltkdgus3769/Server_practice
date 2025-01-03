package com.busanit501.helloworld.jdbcex.controller;


import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.listener.TestListener;
import com.busanit501.helloworld.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Log4j2
@WebServlet(name="TodoList2Controller", urlPatterns = "/todo/list2")
public class TodoList2Controller extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext context = request.getServletContext();
        String result = (String)context.getAttribute("appTestName");
        log.info("TodoListController ServletContext 값 조회 확인 : " + result);

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
