package com.busanit501.helloworld.member.controller;


import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.service.TodoService;
import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@WebServlet(name="MemberListController", urlPatterns = "/member/list")
public class MemberListController extends HttpServlet {
    private MemberService memberService = MemberService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        log.info("doGet MemberListController 확인");
        List<MemberDTO> memberList = null;
        try {
            memberList = memberService.listAll();
            request.setAttribute("list",memberList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/memberList.jsp");
            dispatcher.forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
