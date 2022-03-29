package mvc.mvc1Study.servlet.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Member {

    private Long id;
    private String username;
    private int age;

    @Builder(builderMethodName = "createMember")
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
