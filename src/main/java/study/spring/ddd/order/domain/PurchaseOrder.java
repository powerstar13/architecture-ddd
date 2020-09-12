package study.spring.ddd.order.domain;

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
@Table(name = "purchase_order")
public class PurchaseOrder {

    @Id
    @Column(length = 50, nullable = false)
    private String orderNumber;

    private Long version;

    @Column(length = 50)
    private String ordererId;

    @Column(length = 50)
    private String ordererName;

    private Integer totalAmounts;

    @Column(length = 6)
    private String shippingZipCode;

    @Column(length = 100)
    private String shippingAddr1;

    @Column(length = 100)
    private String shippingAddr2;

    @Column(length = 200)
    private String shippingMessage;

    @Column(length = 50)
    private String receiverName;

    @Column(length = 50)
    private String receiverPhone;

    @Column(length = 20)
    private String state;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
}
