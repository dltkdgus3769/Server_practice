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
import java.util.List;

@WebServlet(name="MenuListController", urlPatterns = "/menu/list")
public class MenuListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<MenuDTO> menuList = MenuService.INSTANCE.getList();
        request.setAttribute("list",menuList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/menu/menuList.jsp");
        dispatcher.forward(request,response);
    }
}
