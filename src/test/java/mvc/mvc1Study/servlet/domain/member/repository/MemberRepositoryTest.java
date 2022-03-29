package mvc.mvc1Study.servlet.domain.member.repository;

import mvc.mvc1Study.servlet.domain.member.entity.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    public void save() throws Exception {
        // given
        Member memberA = Member.createMember().username("memberA").age(20).build();

        // when
        Member saveMember = memberRepository.save(memberA);

        // then
        Member findMember = memberRepository.findById(memberA.getId());
        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    public void findAll() throws Exception {
        // given
        Member memberA = Member.createMember().username("memberA").age(20).build();
        Member memberB = Member.createMember().username("memberB").age(30).build();

        // when
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        // then
        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}