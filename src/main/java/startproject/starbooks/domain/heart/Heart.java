package startproject.starbooks.domain.heart;

import lombok.*;
import startproject.starbooks.domain.account.Account;
import startproject.starbooks.domain.book.Book;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Heart {

    @Id
    @GeneratedValue
    private Long id;

    private boolean isHeart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public Heart(Book book, Account account) {
        this.setHeart(true);
        book.addHeart(this);
        account.addHeart(this);
    }

    public void deleteHeart() {
        this.isHeart = false;
    }

    public void setHeart(boolean heart) {
        isHeart = heart;
    }


}
