package com.busanit501.helloworld.login2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="LoginInputController2", urlPatterns = "/login2/input")
public class LoginInputController2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet 호출 함.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login2/login_input2.jsp");
        dispatcher.forward(request,response);
    }
}
