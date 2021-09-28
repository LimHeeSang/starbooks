package startproject.starbooks.domain.refresh;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import startproject.starbooks.domain.account.Account;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue
    @Column(name = "refresh_id")
    private Long id;

    @OneToOne(mappedBy = "refreshToken", fetch = FetchType.LAZY)
    private Account account;

    private String refreshValue;

    public RefreshToken updateValue(String token) {
        this.refreshValue = token;
        return this;
    }

    /**
     * 생성 메소드
     */
    public void setRefreshValue(String refreshValue) {
        this.refreshValue = refreshValue;
    }

}
