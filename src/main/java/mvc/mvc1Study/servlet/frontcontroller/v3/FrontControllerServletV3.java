package mvc.mvc1Study.servlet.frontcontroller.v3;

import mvc.mvc1Study.servlet.frontcontroller.ModelView;
import mvc.mvc1Study.servlet.frontcontroller.MyView;
import mvc.mvc1Study.servlet.frontcontroller.v3.controller.MemberFormControllerV3;
import mvc.mvc1Study.servlet.frontcontroller.v3.controller.MemberListControllerV3;
import mvc.mvc1Study.servlet.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerServletV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        ControllerV3 controller = controllerV3Map.get(requestURI);

        if(controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(name -> paramMap.put(name, req.getParameter(name)));

        ModelView modelView = controller.process(paramMap);
        String viewPath = modelView.getViewName();

        MyView myView = viewResolver(viewPath);
        myView.render(modelView.getModel(), req, resp);
    }

    // 논리 주소를 물리 주소로 변환 시켜 준다.
    private MyView viewResolver(String viewPath) {
        MyView myView = new MyView("/WEB-INF/views/" + viewPath + ".jsp");
        return myView;
    }
}
