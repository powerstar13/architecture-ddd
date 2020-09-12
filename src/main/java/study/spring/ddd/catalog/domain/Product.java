package study.spring.ddd.catalog.domain;

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
@Table(name = "product")
public class Product {

    @Id
    @Column(length = 50, nullable = false)
    private String productId;

    @Column(length = 100)
    private String name;

    private Integer price;

    @Column(columnDefinition = "TEXT")
    private String detail;
}
