package com.busanit501.helloworld.jdbcex.controller;



import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@Log4j2
@WebServlet(name="TodoRead2Controller", urlPatterns = "/todo/read2")
public class TodoRead2Controller extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet:TodoRead2Controller");

        try {
            Long tno = Long.parseLong(request.getParameter("tno"));
            TodoDTO todoDTO = todoService.get(tno);
            // 화면에 전달하기.

            //1. 쿠키 조회
            Cookie findCookie = findCookie(request.getCookies(),"viewTodos");
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


            request.setAttribute("dto", todoDTO);
            request.getRequestDispatcher("/WEB-INF/todo/todoRead2.jsp").forward(request, response);
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
            findCookie = new Cookie("viewTodos", "");
            findCookie.setPath("/");
            findCookie.setMaxAge(60*60*24);

        }
        return  findCookie;
    }
}
