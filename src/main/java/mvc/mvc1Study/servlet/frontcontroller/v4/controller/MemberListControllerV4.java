package mvc.mvc1Study.servlet.frontcontroller.v4.controller;

import mvc.mvc1Study.servlet.domain.member.entity.Member;
import mvc.mvc1Study.servlet.domain.member.repository.MemberRepository;
import mvc.mvc1Study.servlet.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> all = memberRepository.findAll();

        model.put("members", all);

        return "members";
    }
}