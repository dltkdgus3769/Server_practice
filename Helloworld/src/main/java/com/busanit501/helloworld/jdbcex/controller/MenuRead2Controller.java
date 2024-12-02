package com.busanit501.helloworld.jdbcex.controller;



import com.busanit501.helloworld.jdbcex.dto.MenuDTO;
import com.busanit501.helloworld.jdbcex.service.MenuService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "MenuRead2Controller", urlPatterns = "/menu/read2")
public class MenuRead2Controller extends HttpServlet {
    private MenuService menuService = MenuService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet:TodoReadController");
        try {
            Long tno = Long.parseLong(request.getParameter("tno"));
            MenuDTO menuDTO = menuService.get(tno);

            //1. 쿠키 조회
            Cookie findCookie = findCookie(request.getCookies(),"MenuTodos");
            String cookieValue = findCookie.getValue();
            boolean exists = false;
            if(cookieValue != null && cookieValue.indexOf(tno+"-") >= 0) {
                exists = true;
            }
            if(!exists) {
                cookieValue += tno+"-";
                findCookie.setValue(cookieValue);
                findCookie.setMaxAge(60*60*24);
                findCookie.setPath("/");
                response.addCookie(findCookie);
            }

            // 화면에 전달하기.
            request.setAttribute("dto", menuDTO);
            request.getRequestDispatcher("/WEB-INF/menu/menuRead2.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Cookie findCookie(Cookie[] cookies, String name) {
        Cookie findCookie = null;
        if(cookies != null && cookies.length > 0) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals(name)) {
                    findCookie = cookie;
                    break;
                }
            }
        }
        if(findCookie == null) {
            findCookie = new Cookie("MenuTodos", "");
            findCookie.setPath("/");
            findCookie.setMaxAge(60*60*24);

        }
        return  findCookie;
    }
}
