package com.mingxxx.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("post~~");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().write("get_writer");
        System.out.println("get~~");
        getServletConfig();
        getServletContext();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init~~");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println("service~~");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy~~");
    }
}
