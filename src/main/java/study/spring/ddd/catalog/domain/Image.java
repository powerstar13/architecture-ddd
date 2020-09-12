package study.spring.ddd.catalog.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "image", indexes = {
    @Index(columnList = "product_id")
})
@ToString(exclude = "product")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

    private Integer listIdx;

    @Column(length = 10)
    private String imageType;

    private String imagePath;

    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadTime;

}
