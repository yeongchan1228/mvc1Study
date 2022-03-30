package mvc.mvc1Study.servlet.frontcontroller.v2;

import mvc.mvc1Study.servlet.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    MyView process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;
}

