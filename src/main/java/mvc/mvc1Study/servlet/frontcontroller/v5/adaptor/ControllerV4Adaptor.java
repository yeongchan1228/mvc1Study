package mvc.mvc1Study.servlet.frontcontroller.v5.adaptor;

import mvc.mvc1Study.servlet.frontcontroller.ModelView;
import mvc.mvc1Study.servlet.frontcontroller.v4.ControllerV4;
import mvc.mvc1Study.servlet.frontcontroller.v5.MyHandlerAdaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4Adaptor implements MyHandlerAdaptor {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(name -> paramMap.put(name, request.getParameter(name)));

        Map<String, Object> model = new HashMap<>();

        String viewPath = controller.process(paramMap, model);

        ModelView modelView = new ModelView(viewPath);
        modelView.setModel(model);

        return modelView;
    }

}
