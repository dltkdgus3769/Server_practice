package com.busanit501.helloworld.jdbcex.controller;

import com.busanit501.helloworld.jdbcex.dto.MemberDTO;
import com.busanit501.helloworld.jdbcex.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "MenuLoginController", urlPatterns = "/menuLogin")
public class MenuLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("로그인 컨트롤러 입니다.");
        request.getRequestDispatcher("/WEB-INF/menu/menuLogin.jsp").forward(request,response);
    }
    //로직 처리
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("로그인컨트롤러 doPost");
        String mid = request.getParameter("mid");
        String mpw = request.getParameter("mpw");
        try {
            MemberDTO memberDTO = MemberService.INSTANCE.login(mid,mpw);
            HttpSession session = request.getSession();
            session.setAttribute("MenuloginInfo", memberDTO);
            response.sendRedirect("/menu/list2");

        } catch (SQLException e) {
            response.sendRedirect("/menuLogin?result=error");
        }
    }
}

