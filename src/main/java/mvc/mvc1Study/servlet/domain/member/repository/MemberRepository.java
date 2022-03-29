package mvc.mvc1Study.servlet.domain.member.repository;

import mvc.mvc1Study.servlet.domain.member.entity.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemberRepository {

    private static HashMap<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    // 싱글톤 패턴을 위해
    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){
    }

    public Member save(Member member){
        member.setId(sequence++);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
