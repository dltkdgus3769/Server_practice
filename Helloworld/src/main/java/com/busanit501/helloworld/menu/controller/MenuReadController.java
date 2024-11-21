package com.busanit501.helloworld.menu.controller;

import com.busanit501.helloworld.menu.dto.MenuDTO;
import com.busanit501.helloworld.menu.service.MenuService;
import com.busanit501.helloworld.todo.dto.TodoDTO;
import com.busanit501.helloworld.todo.service.TodoService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name="MenuReadController", urlPatterns = "/menu/read")
public class MenuReadController extends HttpServlet {
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("doGet:TodoReadController");
    String menu = request.getParameter("menuName");

    MenuDTO menuDTO = MenuService.INSTANCE.getone(menu);
    request.setAttribute("dto",menuDTO);


    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/menu/menuRead.jsp");
    dispatcher.forward(request,response);
}
}
