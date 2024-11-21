package com.busanit501.helloworld.todo.controller;

import com.busanit501.helloworld.todo.dto.TodoDTO;
import com.busanit501.helloworld.todo.service.TodoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name="TodoReadController", urlPatterns = "/todo/read")
public class TodoReadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet:TodoReadController");
        Long tno = Long.parseLong(request.getParameter("tno"));

        TodoDTO todoDTO = TodoService.INSTANCE.getone(tno);
        request.setAttribute("dto",todoDTO);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/todo/todoRead.jsp");
        dispatcher.forward(request,response);
    }
}
