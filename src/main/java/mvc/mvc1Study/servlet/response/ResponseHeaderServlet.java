package mvc.mvc1Study.servlet.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Status Line
        resp.setStatus(HttpServletResponse.SC_OK);

        // Response Header
//        resp.setHeader("Content-Type", "text/plain;charset=utf-8");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

//        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 600초, 유효 기간
        resp.addCookie(cookie);

        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("my-header", "hello");

        resp.sendRedirect("/basic/hello-form.html"); // 리다이렉트 설정

        resp.getWriter().write("OK!");
    }
}
