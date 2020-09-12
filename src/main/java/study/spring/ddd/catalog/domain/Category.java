package study.spring.ddd.catalog.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "category")
public class Category {

    @Id
    @Column(nullable = false)
    private long categoryId;

    @Column(length = 100)
    private String name;

}
