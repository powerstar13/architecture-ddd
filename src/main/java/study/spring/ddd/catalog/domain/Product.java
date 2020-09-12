package study.spring.ddd.catalog.domain;

import lombok.*;
import study.spring.ddd.order.domain.OrderLine;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "product")
@ToString(exclude = {"productCategory", "orderLine", "image"})
public class Product {

    @Id
    @Column(length = 50, nullable = false)
    private String productId;

    @Column(length = 100)
    private String name;

    private Integer price;

    @Column(columnDefinition = "TEXT")
    private String detail;

    //---------------------------------------------------------------------

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductCategory> productCategory;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<OrderLine> orderLine;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Image> image;
}
