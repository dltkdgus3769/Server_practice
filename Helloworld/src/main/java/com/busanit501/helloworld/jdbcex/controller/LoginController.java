package com.busanit501.helloworld.jdbcex.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(name="LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("로그인 컨트롤러 입니다.");
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    }
    //로직 처리
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("로그인컨트롤러 doPost");
        String mid = request.getParameter("mid");
        String mpw = request.getParameter("mpw");

        //실제론, 디비에 가서 해당유저가 있으면
        String tempInfo = mid+mpw;
        //세션에 위의 고르인 정보를 저장,
        HttpSession session = request.getSession();
        session.setAttribute("loginInfo", tempInfo);
        response.sendRedirect("/todo/list2");


    }
}
