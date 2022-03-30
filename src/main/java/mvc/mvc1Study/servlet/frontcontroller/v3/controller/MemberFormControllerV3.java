package mvc.mvc1Study.servlet.frontcontroller.v3.controller;

import mvc.mvc1Study.servlet.frontcontroller.ModelView;
import mvc.mvc1Study.servlet.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
