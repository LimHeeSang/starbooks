package startproject.starbooks.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    //@JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();


    //@JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<Heart> hearts = new ArrayList<>();

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /*@ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();*/

    /**
     * 연관관계 메소드
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setAccount(this);
    }

    public void deleteComment(Comment comment) {
        this.comments.remove(comment);
        comment.setAccount(null);
    }

    public void addHeart(Heart heart) {
        this.hearts.add(heart);
        heart.setAccount(this);
    }

    public void deleteHeart(Heart heart) {
        this.hearts.remove(heart);
        heart.setAccount(null);
    }
}
