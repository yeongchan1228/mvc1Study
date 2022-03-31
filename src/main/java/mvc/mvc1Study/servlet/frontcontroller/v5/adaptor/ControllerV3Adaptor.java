package mvc.mvc1Study.servlet.frontcontroller.v5.adaptor;

import mvc.mvc1Study.servlet.frontcontroller.ModelView;
import mvc.mvc1Study.servlet.frontcontroller.v3.ControllerV3;
import mvc.mvc1Study.servlet.frontcontroller.v5.MyHandlerAdaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3Adaptor implements MyHandlerAdaptor {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(name -> paramMap.put(name, request.getParameter(name)));

        ModelView modelView = controller.process(paramMap);

        return modelView;
    }

}
