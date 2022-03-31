package mvc.mvc1Study.servlet.frontcontroller.v4;

import mvc.mvc1Study.servlet.frontcontroller.ModelView;
import mvc.mvc1Study.servlet.frontcontroller.MyView;
import mvc.mvc1Study.servlet.frontcontroller.v3.ControllerV3;
import mvc.mvc1Study.servlet.frontcontroller.v3.controller.MemberFormControllerV3;
import mvc.mvc1Study.servlet.frontcontroller.v3.controller.MemberListControllerV3;
import mvc.mvc1Study.servlet.frontcontroller.v3.controller.MemberSaveControllerV3;
import mvc.mvc1Study.servlet.frontcontroller.v4.controller.MemberFormControllerV4;
import mvc.mvc1Study.servlet.frontcontroller.v4.controller.MemberListControllerV4;
import mvc.mvc1Study.servlet.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerV4Map = new HashMap<>();

    public FrontControllerServletV4() {
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        ControllerV4 controller = controllerV4Map.get(requestURI);

        if(controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator()
                .forEachRemaining(name -> paramMap.put(name, req.getParameter(name)));
        Map<String, Object> model = new HashMap<>();
        String viewPath = controller.process(paramMap, model);

        MyView myView = viewResolver(viewPath);
        myView.render(model, req, resp);
    }

    // 논리 주소를 물리 주소로 변환 시켜 준다.
    private MyView viewResolver(String viewPath) {
        MyView myView = new MyView("/WEB-INF/views/" + viewPath + ".jsp");
        return myView;
    }
}
