package com.mingxxx.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "CookieServlet", urlPatterns = "/cookie")
public class CookieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //1.创建Cookie,响应添加Cookie
        Cookie cookie = new Cookie("lasttime", new Date().toLocaleString().replaceAll(" ", "_"));
        //如果不设置cookie的MaxAge（或将其值设置为负值），则默认情况下浏览器会将cookie保存在浏览器的内存中，会随着浏览器关闭而消失。
        // 如果设置为一个正值，则代表该Cookie要保存的以秒为单位的时间值，如此，该cookie将会被浏览器保存到硬盘中去。
        // 如果将MaxAge设置为0，则是通知浏览器去删除该Cookie。
        cookie.setMaxAge(3600 * 24 * 30);//单位秒，这里是1个月
        //用来指定访问哪个ULR及其子URL时带上此cookie，如果不设置此值，则浏览器默认会将发送该cookie的servlet所在的路径作为path使用
        //可在同一应用服务器内共享方法：设置cookie.setPath("/");
        //设置cookie.setPath("/webapp_b"),是指在webapp_b下面才可以使用cookie，这样就不可以在产生cookie的应用webapp_a下面获取cookie了
        //有多条cookie.setPath("XXX");语句的时候，起作用的以最后一条为准。
        cookie.setPath("/hello");
        //设置cookie对应的域名，此方法一旦调用，如果域名与本web服务的域名不一致，则浏览器会认为该cookie是一个第三方cookie而拒收
        // 新版tomcat不允许domain以.开头
        cookie.setDomain("localhost");
        //用来指定访问哪个ULR及其子URL时带上此cookie，如果不设置此值，则浏览器默认会将发送该cookie的servlet所在的路径作为path使用
        response.addCookie(cookie);
        //2.请求获取Cookie
        Cookie[] cs = request.getCookies();
        Cookie findC = null;
        if (cs != null) {
            for (Cookie c : cs) {
                if ("lasttime".equals(c.getName())) {
                    findC = c;
                    break;
                }
            }
        }

        if (findC == null) {
            response.getWriter().write("首次登录");
            return;
        } else {
            String time = findC.getValue();
            response.getWriter().write("上次登录时间:" + time);
            return;
        }

    }
}
