package com.mingxxx;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DispatchServlet", urlPatterns = "/disp")
public class DispatchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("disp Servlet");
        System.out.println(request.getParameter("username"));
        String str = (String) request.getAttribute("dispatch");
        System.out.println("receive: " + str);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("disp Servlet" + "收到");
    }
}
