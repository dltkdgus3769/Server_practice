package com.busanit501.helloworld.jdbcex.controller;

import com.busanit501.helloworld.HelloServlet;
import com.busanit501.helloworld.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "TodoDeleteController", urlPatterns = "/todo/delete")
public class TodoDeleteController extends HelloServlet {
    private TodoService todoService = TodoService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long tno = Long.valueOf(request.getParameter("tno"));
        //
        log.info("doPost TodoDeleteController 확인");
        try {
            todoService.delete(tno);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/todo/list2");
    }
}
