package study.spring.ddd.board.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "article_content")
public class ArticleContent {

    @Id
    @Column(nullable = false)
    private int id;

    private String content;

    private String contentType;

}
