package mvc.mvc1Study.servlet.frontcontroller.v2.controller;

import mvc.mvc1Study.servlet.domain.member.entity.Member;
import mvc.mvc1Study.servlet.domain.member.repository.MemberRepository;
import mvc.mvc1Study.servlet.frontcontroller.MyView;
import mvc.mvc1Study.servlet.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = Member.createMember().username(username).age(age).build();
        memberRepository.save(member);

        request.setAttribute("member", member);

        String viewPath = "/WEB-INF/views/save-result.jsp";
        return new MyView(viewPath);
    }
}
