package startproject.starbooks.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startproject.starbooks.domain.Book;
import startproject.starbooks.domain.dto.BooksResponseDto;
import startproject.starbooks.domain.dto.Item;
import startproject.starbooks.repository.BookSaveRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookInsert {

    private final BookApiClient bookApiClient;
    private final BookSaveRepository bookRepository;
    private List<String> keywords = new ArrayList<>();

    public void insert() {
        log.info("[BookInsert.insert()]");

        keywords.add("파이썬");
        keywords.add("자바");
        keywords.add("보안");
        keywords.add("네트워크");
        keywords.add("사랑");
        keywords.add("유투브");
        keywords.add("데이터베이스");
        keywords.add("인생");
        keywords.add("주식");
        keywords.add("인간");
        keywords.add("메이플");
        keywords.add("살아남기");
        keywords.add("미적분");
        keywords.add("코인");
        keywords.add("자료구조");
        keywords.add("이산수학");

        BooksResponseDto responseDto = bookApiClient.requestBook("자바");
        Item[] items = responseDto.getItems();
        for (Item item : items) {

            log.info("[item = {}]", item.toString());
            Book book = new Book();

            book.setImgUrl(item.getImage());
            book.setTitle(item.getTitle().replaceAll("<b>", "").replaceAll("</b>", ""));
            book.setAuthor(item.getAuthor());
            book.setPublisher(item.getPublisher());

            book.setPrice(item.getPrice());
            book.setDescription(item.getDescription().replaceAll("<b>", "").replaceAll("</b>", ""));
            book.setIsbn(item.getIsbn());

            log.info("[book = {}]", book.toString());

            bookRepository.save(book);

        }


    }

}
