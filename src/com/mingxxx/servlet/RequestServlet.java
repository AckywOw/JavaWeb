package com.mingxxx.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

@WebServlet(name = "RequestServlet", urlPatterns = "/req")
public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        req5(request, response);
    }

    /**
     * include 可以由多个程序一同生成响应 ----- 常用来页面布局(不常用)
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void req5(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher d1 = request.getRequestDispatcher("/head.html");

        d1.include(request, response);

        RequestDispatcher d2 = request.getRequestDispatcher("/main.html");

        d2.include(request, response);

        RequestDispatcher d3 = request.getRequestDispatcher("/copy.html");

        d3.include(request, response);

    }

    /**
     * 请求转发 在跳转时注意流的问题: 在最后的路径中使用流，不要在开始时使用.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void req4(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("req servlet");
        System.out.println(request.getParameter("username"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/disp");
        request.setAttribute("dispatch", "来了");
        dispatcher.forward(request, response);
        System.out.println("d5 servlet over");

    }

    /**
     * 3.获取请求参数(重点)
     *
     * @param request
     */
    private void req3(HttpServletRequest request) throws UnsupportedEncodingException {
//        request.setCharacterEncoding("utf-8");
        // 获取姓名
        String username = request.getParameter("username");
        System.out.println(username);
        // // 获取密码
        System.out.println(request.getParameter("password"));
        // // 获取性别
        System.out.println(new String(request.getParameter("sex").getBytes(), "utf-8"));
        // // 获取爱好
        System.out.println(Arrays.toString(request.getParameterValues("hobby")));

        Map<String, String[]> map = request.getParameterMap();
        System.out.println(map.toString());

        Enumeration<String> parameterNames = request.getParameterNames();

        /*
        乱码解决方案"utf-8"
		1.post:
				request.setCharacterEncoding("utf-8");
        2.get
				解决编码问题，需要进行手动.（Tomcat8 中get请求默认已经是utf-8,已经不需要对get请求做编码处理）
                username=new String(username.getBytes("iso8859-1"),"utf-8");
                最终:无论是post,get都可以使用get这种方式处理.
        * */

        // 进行数据校验
        if (username != null && username.trim().length() > 0) { //非空
            System.out.println(username);
        }
    }

    /**
     * 2.获取请求头信息
     *
     * @param request
     */
    private void req2(HttpServletRequest request) {
        // 获取请求头住处 Referer
        String referer = request.getHeader("Referer");
        System.out.println(referer); //防盗链

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            String values = request.getHeader(header);
            System.out.println(header + ":" + values);
        }
    }

    /**
     * 1.request获取客户机信息(通过request去获取浏览器信息)
     *
     * @param request
     */
    private void req1(HttpServletRequest request) {
        System.out.println(request);
        String addr = request.getRemoteAddr(); //得到客户端ip地址
        System.out.println(addr);

        String protocol = request.getProtocol(); //获取协议
        System.out.println(protocol);

        /*URL与URI区别
        URL: 统一资源定位符   http://localhost:8080/hello/req
        URI: 统一资源标识符   /hello/req
        URL包含URI*/
        System.out.println(request.getRequestURL()); //应用比较多
        System.out.println(request.getRequestURI()); //应用比较多
        //在开发中经常使用  request.getRequestURI()-request.getContxtPath() 去获取资源路径.
        System.out.println(request.getContextPath());  //是到是虚拟目录

        System.out.println(request.getQueryString()); //获取的是请求中的参数
    }
}
