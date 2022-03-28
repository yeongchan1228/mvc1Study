package mvc.mvc1Study.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = {"/hello"})
public class HelloServlet extends HttpServlet {

    /**
     * Servlet이 호출되면 service 메서드 실행
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        System.out.println("request.getMethod() = " + request.getMethod());
        String username = request.getParameter("username");
        System.out.println("request.getParameter(\"username\") = " + username);

        // 헤더 값
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        // 바디 값
        response.getWriter().write("hello "+ username);


    }
}
