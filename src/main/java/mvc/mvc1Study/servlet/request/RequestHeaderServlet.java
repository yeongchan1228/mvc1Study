package mvc.mvc1Study.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printStartLine(req);
        printHeaders(req);
        printHeaderUtils(req);
        printEtc(req);
    }

    private void printStartLine(HttpServletRequest req) {
        System.out.println("----Request Line Start----");

        System.out.println("req.getMethod() = " + req.getMethod());
        System.out.println("req.getProtocol() = " + req.getProtocol());
        System.out.println("req.getScheme() = " + req.getScheme());

        System.out.println("req.getRequestURL() = " + req.getRequestURL());

        System.out.println("req.getRequestURI() = " + req.getRequestURI());

        System.out.println("req.getQueryString() = " + req.getQueryString());
        // https를 사용하는지?
        System.out.println("req.isSecure() = " + req.isSecure());

        System.out.println("----Request Line End----");
        System.out.println();
    }

    private void printHeaders(HttpServletRequest req) {
        System.out.println("----Request Header Start----");

//        Enumeration<String> headerNames = req.getHeaderNames();
//        while (headerNames.hasMoreElements()){
//            String headerName = headerNames.nextElement();
//            System.out.println("headerName = " + headerName);
//        }
        req.getHeaderNames().asIterator().forEachRemaining(headerName -> System.out.println(headerName +" " + req.getHeader(headerName)));

        System.out.println("----Request Header End----");
        System.out.println();
    }

    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " +
        request.getServerPort()); //Host 헤더
        System.out.println();
        System.out.println("[Accept-Language 편의 조회]"); request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));

        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();
        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " +
                request.getContentType());
        System.out.println("request.getContentLength() = " +
                request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " +
                request.getCharacterEncoding());
        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    private void printEtc(HttpServletRequest request) { System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());
        System.out.println();

        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        System.out.println("request.getLocalPort() = " + request.getLocalPort());
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }

}
