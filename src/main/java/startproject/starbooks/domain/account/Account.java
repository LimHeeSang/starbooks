package startproject.starbooks.domain.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import startproject.starbooks.domain.comment.Comment;
import startproject.starbooks.domain.heart.Heart;
import startproject.starbooks.domain.refresh.RefreshToken;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column
    private String userName;

    @Column(unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int birthDate;

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


    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "refresh_id")
    private RefreshToken refreshToken;

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

    /**
     * refresh token 할당
     */
    public RefreshToken setRefreshToken(String tokenValue) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshValue(tokenValue);
        this.refreshToken = refreshToken;

        return refreshToken;
    }

    /**
     * refresh token
     */
    public Optional<RefreshToken> getRefreshToken() {
        return Optional.ofNullable(refreshToken);
    }
}
