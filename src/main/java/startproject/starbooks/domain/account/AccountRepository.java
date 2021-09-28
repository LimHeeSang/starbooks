package startproject.starbooks.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(String userId);

    boolean existsByUserId(String userId);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
