package com.busanit501.helloworld.login;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="LoginResultController", urlPatterns = "/login/result")
public class LoginResultController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ID = request.getParameter("ID");
        String Password = request.getParameter("Password");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>ID:"+ID+"</h1>");
        out.println("<h1>Password:"+Password+"</h1>");
        out.println("</body></html>");


        System.out.println("ID:"+ID+" Password:"+Password);

//        response.sendRedirect("/WEB-INF/calc/calc_result.jsp");
//        response.sendRedirect("/login/input");
    }
}
