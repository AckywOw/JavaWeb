package com.mingxxx.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;

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
        //常用方法
        getServletConfig();
        getServletName();
        String initParameter = getInitParameter("init-param1");
        System.out.println("initParameter：" + initParameter);
        getInitParameterNames();

        ServletContext servletContext = getServletContext();
        //1.可以实现servlet 通信.（重点）
        servletContext.getAttribute("");
        servletContext.getAttributeNames();
        servletContext.removeAttribute("");
        //2.获取文件的MIME类型
        String mimeType = servletContext.getMimeType("oin.html");//不需要全路径
        System.out.println(mimeType);
        //3.写入日志
        log("我是log");
        //4.获取配置信息
        String initParameter1 = servletContext.getInitParameter("context-param1");
        System.out.println("initParameter1：" + initParameter1);
        String initParameter2 = servletContext.getInitParameter("context-param2");
        System.out.println("initParameter2：" + initParameter2);
        servletContext.getInitParameterNames();
        //5.servletContext对象可以获取路径与流
        URL resource = servletContext.getResource("/main/oin.html");//获取指定的名称的路径.这里要全路径
        System.out.println(resource.getPath());
        URL resource2 = servletContext.getResource("/");//获取指定的名称的路径.这里代表当前web应用的全路径
        System.out.println(resource2.getPath());
        InputStream resourceAsStream = servletContext.getResourceAsStream("/main/oin.txt");//这里要全路径
        byte[] bytes = new byte[100];
        resourceAsStream.read(bytes);
        System.out.println(new String(bytes));
        resourceAsStream.close();
        String realPath = servletContext.getRealPath("/");//获取当前web应用在服务器上的磁盘路径。
        System.out.println("realPath:" + realPath);

        String out = servletContext.getRealPath("/out.txt");
        readFile(out);
        String oin = servletContext.getRealPath("/main/oin.txt");
        readFile(oin);
        String in = servletContext.getRealPath("/WEB-INF/in.txt");
        readFile(in);
        String inin = servletContext.getRealPath("/WEB-INF/classes/inin.txt");
        readFile(inin);

        //利用classPath获取路径
        String inin2 = new File(getClass().getResource("/").getPath()).getParent() + "/in.txt";
        readFile(inin2);

        String contextPath = servletContext.getContextPath();//获取服务名字
        System.out.println("contextPath:" + contextPath);
        //6.完成分发请求 不常用
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/main/oin.html");


    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init~~");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy~~");
    }

    private void readFile(String path) throws IOException {
        System.out.println(path);
        BufferedReader br = new BufferedReader(new FileReader(path));
        System.out.println(br.readLine());
        br.close();
    }
}
