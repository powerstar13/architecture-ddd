package study.spring.ddd.member.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "member")
@ToString(exclude = "memberAuthorities")
public class Member {

    @Id
    @Column(length = 50, nullable = false)
    private String memberId;

    @Column(length = 50)
    private String name;

    private String password;

    private Boolean blocked; // TINIYINT(nullable = true) --> `Boolean`으로 처리

    //---------------------------------------------------------------------

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<MemberAuthorities> memberAuthorities;
}
