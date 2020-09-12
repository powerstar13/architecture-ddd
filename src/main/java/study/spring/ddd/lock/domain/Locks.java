package study.spring.ddd.lock.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "locks")
public class Locks implements Serializable {

    @Id
    @Column(nullable = false)
    private String type;

    @Id
    @Column(nullable = false)
    private String id;

    @Column(unique = true)
    private String lockid;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationTime;
}
