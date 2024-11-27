package com.busanit501.helloworld.member.controller;


import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.service.MemberService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name="MemberRegController", urlPatterns = "/member/register")
public class MemberRegController extends HttpServlet {
    private MemberService memberService = MemberService.INSTANCE;
    private final DateTimeFormatter Date_Time_Format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/member/memberReg.jsp");
        dispatcher.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberDTO memberDTO = MemberDTO.builder()
                .name(request.getParameter("name"))
                .birthdate(LocalDate.parse(request.getParameter("birthdate"),Date_Time_Format))
                .tel(request.getParameter("tel"))
                .build();

        try {
            memberService.register(memberDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/member/list");
    }
}
