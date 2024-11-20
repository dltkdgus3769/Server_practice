package com.busanit501.helloworld.test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginInput", urlPatterns = "/test/input")
public class LoginInput extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher =request.getRequestDispatcher("/WEB-INF/test/login_input.jsp");
        dispatcher.forward(request,response);
    }
}
