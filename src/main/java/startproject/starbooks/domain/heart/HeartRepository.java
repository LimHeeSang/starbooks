package startproject.starbooks.domain.heart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Optional<Heart> findByBookIdAndAccountId(Long bookId, Long accountId);

    List<Heart> findByBookId(Long bookId);

}


