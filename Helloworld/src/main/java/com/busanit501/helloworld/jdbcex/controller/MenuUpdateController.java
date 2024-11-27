package com.busanit501.helloworld.jdbcex.controller;

import com.busanit501.helloworld.jdbcex.dto.MenuDTO;
import com.busanit501.helloworld.jdbcex.service.MenuService;
import lombok.extern.log4j.Log4j2;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name="menuUpdateController", urlPatterns = "/menu/update")
public class menuUpdateController extends HttpServlet {
    private MenuService menuService = MenuService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 클릭한 게시글 번호를 가지고 와야함.
            Long tno = Long.parseLong(request.getParameter("tno"));
            MenuDTO menuDTO = menuService.get(tno);
            // 화면에 전달하기.
            request.setAttribute("dto", menuDTO);
            request.getRequestDispatcher("/WEB-INF/menu/menuUpd.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MenuDTO menuDTO = MenuDTO.builder()
                .tno(Long.parseLong(request.getParameter("tno")))
                .menu(request.getParameter("menu"))
                .price(Integer.parseInt(request.getParameter("price")))
                .description(request.getParameter("description"))
                .build();
        // Controller -> Service
        try {
            menuService.update(menuDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/menu/list2");

    }
}
