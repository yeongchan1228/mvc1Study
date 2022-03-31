package mvc.mvc1Study.servlet.frontcontroller.v5;

import mvc.mvc1Study.servlet.frontcontroller.ModelView;
import mvc.mvc1Study.servlet.frontcontroller.MyView;
import mvc.mvc1Study.servlet.frontcontroller.v3.controller.MemberFormControllerV3;
import mvc.mvc1Study.servlet.frontcontroller.v3.controller.MemberListControllerV3;
import mvc.mvc1Study.servlet.frontcontroller.v3.controller.MemberSaveControllerV3;
import mvc.mvc1Study.servlet.frontcontroller.v4.controller.MemberFormControllerV4;
import mvc.mvc1Study.servlet.frontcontroller.v4.controller.MemberListControllerV4;
import mvc.mvc1Study.servlet.frontcontroller.v4.controller.MemberSaveControllerV4;
import mvc.mvc1Study.servlet.frontcontroller.v5.adaptor.ControllerV3Adaptor;
import mvc.mvc1Study.servlet.frontcontroller.v5.adaptor.ControllerV4Adaptor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdaptor> handlerAdaptors = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();

        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdaptors.add(new ControllerV3Adaptor());
        handlerAdaptors.add(new ControllerV4Adaptor());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Object handler = getHandler(req);

        if(handler == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdaptor handlerAdapter = getHandlerAdapter(handler);

        ModelView mv = handlerAdapter.handle(req, resp, handler);

        String viewPath = mv.getViewName();
        MyView view = viewResolver(viewPath);
        view.render(mv.getModel(), req, resp);
    }

    private MyView viewResolver(String viewPath) {
        MyView myView = new MyView("/WEB-INF/views/" + viewPath + ".jsp");
        return myView;
    }

    private MyHandlerAdaptor getHandlerAdapter(Object handler) {
        for (MyHandlerAdaptor handlerAdaptor : handlerAdaptors) {
            if(handlerAdaptor.supports(handler)){
                return handlerAdaptor;
            }
        }

       throw new IllegalArgumentException("handler 찾기 오류");
    }

    private Object getHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        Object handler = handlerMappingMap.get(requestURI);
        return handler;
    }
}
