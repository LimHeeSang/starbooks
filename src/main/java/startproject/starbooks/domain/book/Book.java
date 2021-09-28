package startproject.starbooks.domain.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import startproject.starbooks.domain.comment.Comment;
import startproject.starbooks.domain.heart.Heart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String imgUrl;

    @Column(nullable = false)
    private String title;

    private String author;
    private String publisher;
    private int price;


    @Lob
    @Column(nullable = false)
    private String description;

    private String isbn;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Heart> hearts = new ArrayList<>();

    @CreatedDate
    private String createdAt;

    @LastModifiedDate
    private String modifiedAt;

    /**
     * 연관관계 메소드
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setBook(this);
    }

    public void deleteComment(Comment comment) {
        this.comments.remove(comment);
        comment.deleteComment();
    }

    public void addHeart(Heart heart) {
        this.hearts.add(heart);
        heart.setBook(this);
    }

    public void deleteHeart(Heart heart) {
        this.hearts.remove(heart);
        heart.deleteHeart();
    }
}
