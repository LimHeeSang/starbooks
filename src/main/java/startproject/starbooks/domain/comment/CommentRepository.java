package startproject.starbooks.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBookIdOrderByCreatedAtDesc(Long books_id);

    Optional<Comment> findByAccountIdAndBookId(Long account_id, Long books_id);

    List<Comment> findByBookId(Long books_id);

    boolean existsByAccountIdAndBookId(Long accountId, Long bookId);

}

