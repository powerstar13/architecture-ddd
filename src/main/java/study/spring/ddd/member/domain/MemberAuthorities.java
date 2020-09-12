package study.spring.ddd.member.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "member_authorities")
@ToString(exclude = "member")
public class MemberAuthorities implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, updatable = false)
    private Member member;

    @Id
    @Column(length = 50, nullable = false)
    private String authority;
}
