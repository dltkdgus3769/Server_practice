package com.busanit501.helloworld.calc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name ="calcResultController", urlPatterns = "/calc/result")
public class CalcResultController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");

        System.out.println("num1:"+num1+" num2:"+num2);

//        response.sendRedirect("/WEB-INF/calc/calc_result.jsp");
        response.sendRedirect("/calc/input");
    }
}
