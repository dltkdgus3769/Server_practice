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
import java.util.List;

@WebServlet(name="MenuList2Controller", urlPatterns = "/menu/list2")
public class MenuList2Controller extends HttpServlet {
    private MenuService menuService = MenuService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<MenuDTO> menuList = null;
        try {
            menuList = menuService.listAll();
            request.setAttribute("list",menuList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/menu/menuList2.jsp");
            dispatcher.forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
