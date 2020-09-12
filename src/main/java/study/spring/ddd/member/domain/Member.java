package study.spring.ddd.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "member")
public class Member {

    @Id
    @Column(length = 50, nullable = false)
    private String memberId;

    @Column(length = 50)
    private String name;

    private String password;

    private Boolean blocked; // TINIYINT(nullable = true) --> `Boolean`으로 처리
}
