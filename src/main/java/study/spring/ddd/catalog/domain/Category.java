package study.spring.ddd.catalog.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "category")
@ToString(exclude = "productCategory")
public class Category {

    @Id
    @Column(nullable = false)
    private long categoryId;

    @Column(length = 100)
    private String name;

    //---------------------------------------------------------------------

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<ProductCategory> productCategory;
}
