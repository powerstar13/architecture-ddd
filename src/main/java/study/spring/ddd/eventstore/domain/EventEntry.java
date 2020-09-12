package study.spring.ddd.eventstore.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "evententry")
public class EventEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    private String contentType;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String payload;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

}
