package mvc.mvc1Study.servlet.frontcontroller.v3.controller;

import mvc.mvc1Study.servlet.domain.member.entity.Member;
import mvc.mvc1Study.servlet.domain.member.repository.MemberRepository;
import mvc.mvc1Study.servlet.frontcontroller.ModelView;
import mvc.mvc1Study.servlet.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = Member.createMember().username(username).age(age).build();
        memberRepository.save(member);

        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", member);
        return modelView;
    }
}
