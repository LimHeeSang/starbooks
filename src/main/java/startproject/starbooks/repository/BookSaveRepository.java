package startproject.starbooks.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import startproject.starbooks.domain.Book;
import startproject.starbooks.domain.dto.Item;

import javax.persistence.EntityManager;

@Repository
@Slf4j
@RequiredArgsConstructor
public class BookSaveRepository {

    /**
     * 책 데이터 넣기
     */
    private final EntityManager em;

    public void save(Book book) {
        log.info("[BookRepository.save()]");
        em.persist(book);
    }

}
