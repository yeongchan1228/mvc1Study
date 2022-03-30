package mvc.mvc1Study.servlet.frontcontroller.v3;

import mvc.mvc1Study.servlet.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
