package study.spring.ddd.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "member_authorities")
public class MemberAuthorities implements Serializable {

    @Id
    @Column(length = 50, nullable = false)
    private String memberId;

    @Id
    @Column(length = 50, nullable = false)
    private String authority;
}
