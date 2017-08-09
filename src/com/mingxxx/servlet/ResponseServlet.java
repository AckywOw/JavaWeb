package com.mingxxx.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;

@WebServlet(name = "ResponseServlet", urlPatterns = "/resp")
public class ResponseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doResponse(response);
    }

    private void doResponse(HttpServletResponse response) throws IOException {
        res5(response);
    }

    /**
     * 重定向
     *
     * @param response
     */
    private void res1(HttpServletResponse response) throws IOException {
        //设置状态吗
//        response.setStatus(HttpServletResponse.SC_FOUND);
        //2.设置location
//        response.setHeader("location", "https://www.baidu.com");
//        response.sendRedirect("https://www.baidu.com"); //设置这个不需要再设置状态码
//        response.sendRedirect("/index.jsp"); //设置这个不需要再设置状态码
        response.sendRedirect("/hello"); //设置这个不需要再设置状态码
    }

    /**
     * 定时跳转
     *
     * @param response
     */
    private void res2(HttpServletResponse response) {
        response.setHeader("Refresh", "3;url=https://www.baidu.com");
    }

    /**
     * 禁止浏览器缓存
     *
     * @param response
     */
    private void res3(HttpServletResponse response) {
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");

        response.setDateHeader("expires", -1);
//        response.setDateHeader("expires",new Date().getTime() + 1000 * 60 * 60 * 24); //一天缓存
    }

    /**
     * 输出信息
     *
     * @param response
     */
    private void res4(HttpServletResponse response) throws IOException {
        //3.操作响应正文(向页面输出文本信息)
//       // response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 1.获取一个字符输出流
        PrintWriter out = response.getWriter();

        // 2.输出住处到页面
        out.write("了撒大声地");

        //out.println(97);  //将信息转换成字符串输出  String.valueOf(97)
        //out.write(97);  //将97码值对应的字符输出。

        //向页面输出一个表格.
        out.println("<table border='1'>");
        out.println("<tr><td>编号</td><td>姓名</td><td>年龄</td></tr>");
        out.println("<tr><td>1</td><td>张三</td><td>20</td></tr>");
        out.println("<tr><td>2</td><td>李四</td><td>22</td></tr>");
        out.println("</table>");
        out.flush();
        out.close();
    }

    /**
     * 输出图片
     *
     * @param response
     */
    private void res5(HttpServletResponse response) throws IOException {
        // 通知浏览器是一张图片
        response.setContentType("image/jpeg");
        // 通过流将图片写到浏览器端
        // 通过java程序完成一张图片.
        BufferedImage image = new BufferedImage(120, 30,
                BufferedImage.TYPE_INT_RGB);
        // 得到这个图片的画笔.
        Graphics g = image.getGraphics();
        // 设置背景
        g.setColor(Color.green);
        g.fillRect(0, 0, 120, 30);
        g.setColor(Color.red);
        // 设置验证码
        int px = 0;
        g.setFont(new Font("宋体", Font.BOLD, 20));

        String msg = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 4; i++) {
            char n = msg.charAt(random.nextInt(msg.length()));
            g.drawString(String.valueOf(n), 30 + px, 20);
            px += 20;
        }
        // 画干扰线
        for (int i = 0; i < 100; i++) {
            g.setColor(new Color(random.nextInt(255), random.nextInt(255),
                    random.nextInt(255)));
            int x1 = random.nextInt(120);
            int y1 = random.nextInt(30);
            g.fillOval(x1, y1, 2, 2);
        }
        g.dispose();

        // 将图片写到浏览器端
        ImageIO.write(image, "jpeg", response.getOutputStream());
    }
}
