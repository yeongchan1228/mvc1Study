package mvc.mvc1Study.servlet.frontcontroller.v3.controller;

import mvc.mvc1Study.servlet.domain.member.entity.Member;
import mvc.mvc1Study.servlet.domain.member.repository.MemberRepository;
import mvc.mvc1Study.servlet.frontcontroller.ModelView;
import mvc.mvc1Study.servlet.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        List<Member> all = memberRepository.findAll();

        ModelView modelView = new ModelView("members");

        modelView.getModel().put("members", all);
        return modelView;
    }
}
