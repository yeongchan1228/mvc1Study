package mvc.mvc1Study.servlet.mvc;

import mvc.mvc1Study.servlet.domain.member.entity.Member;
import mvc.mvc1Study.servlet.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class MemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    public String newForm(){
        return "new-form";
    }

    @PostMapping("/save")
    public String save(@RequestParam("username") String username, @RequestParam("age") int age, Model model){
        Member member = Member.createMember().username(username).age(age).build();

        Member saveMember = memberRepository.save(member);
        model.addAttribute("member", saveMember);

        return "save-result";
    }

    @GetMapping
    public String findAll(Model model){
        List<Member> all = memberRepository.findAll();

        model.addAttribute("members", all);

        return "members";
    }

}
