package study.spring.ddd.catalog.domain;

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
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    @Column(length = 50)
    private String productId;

    private Integer listIdx;

    @Column(length = 10)
    private String imageType;

    private String imagePath;

    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadTime;

}
