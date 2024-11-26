package com.busanit501.helloworld.jdbcex.controller;



import com.busanit501.helloworld.jdbcex.dto.MenuDTO;
import com.busanit501.helloworld.jdbcex.service.MenuService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name="MenuReg2Controller", urlPatterns = "/menu/register2")
public class MenuReg2Controller extends HttpServlet {
    private MenuService menuService = MenuService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/menu/menuReg2.jsp");
        dispatcher.forward(request,response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MenuRegController doPost: 메뉴등록 로직");
        MenuDTO menuDTO = MenuDTO.builder()
                .menu(request.getParameter("menu"))
                .price(Integer.parseInt(request.getParameter("price")))
                .description(request.getParameter("description"))
                .build();
        try {
            menuService.register(menuDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/menu/list2");

    }
}
