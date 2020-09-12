package study.spring.ddd.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "order_line", indexes = {
    @Index(columnList = "orderNumber"),
    @Index(columnList = "lineIdx")
})
public class OrderLine {

    @Id
    @Column(length = 50, nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private int lineIdx;

    @Column(length = 50, nullable = false)
    private String productId;

    private Integer price;

    private Integer quantity;

    private Integer amounts;
}
