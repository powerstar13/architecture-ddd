package study.spring.ddd.order.domain;

import lombok.*;
import study.spring.ddd.catalog.domain.Product;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "order_line", indexes = {
    @Index(columnList = "order_number"),
    @Index(columnList = "lineIdx"),
    @Index(columnList = "product_id")
})
@ToString(exclude = {"purchaseOrder", "product"})
public class OrderLine {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_number", nullable = false, updatable = false)
    private PurchaseOrder purchaseOrder;

    @Id
    @Column(nullable = false)
    private int lineIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

    private Integer price;

    private Integer quantity;

    private Integer amounts;
}
