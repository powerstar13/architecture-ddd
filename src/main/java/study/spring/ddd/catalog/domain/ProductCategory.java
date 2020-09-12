package study.spring.ddd.catalog.domain;

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
@Table(name = "product_category")
public class ProductCategory implements Serializable {

    @Id
    @Column(length = 50, nullable = false)
    private String productId;

    @Id
    @Column(nullable = false)
    private long categoryId;
}
