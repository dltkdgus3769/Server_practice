package com.busanit501.helloworld.login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="loginInputContorller" ,urlPatterns = "/login/input")
public class LoginInputContorller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet 호출 함.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login/login_input.jsp");
        dispatcher.forward(request,response);
    }
}
