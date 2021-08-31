package startproject.starbooks.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import startproject.starbooks.domain.Book;
import startproject.starbooks.repository.BookRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BookController {

    private final BookRepository bookRepository;

    @GetMapping("/api/books")
    public Page<Book> getAllBooks(
            @RequestParam("page") int page, //요청 페이지
            @RequestParam("size") int size, //요청 사이즈(게시글에 몇개씩 보여줄지)
            @RequestParam("sort") String sort   // starRate, createdAt, heart
    ) {
        Sort sort1 = Sort.by(Sort.Direction.DESC, sort);
        Pageable pageable = PageRequest.of(page - 1, size);

        if(sort.equals("starRate")) {
            log.info("[starRate Request : sort = {}, page = {}, size = {}]", sort, page, size);

            int start = (page-1) * size;
            List<Book> bookList  = bookRepository.findAllByOrderByStarRate(start, size);
            return new PageImpl<>(bookList, pageable, bookList.size());
        }

        if(sort.equals("heart")) {
            log.info("[heart Request : sort = {}, page = {}, size = {}]", sort, page, size);


            int start = (page-1) * size;
            List<Book> bookList  = bookRepository.findAllByOrderByHeart(start, size);
            return new PageImpl<>(bookList, pageable, bookList.size());
        }

        log.info("[createdAt Request : sort = {}, page = {}, size = {}]", sort, page, size);

        Page<Book> books = bookRepository.findAllByOrderByCreatedAtDesc(pageable);
        return books;
    }

    @GetMapping("/api/books/{book_id}")
    public Book getBookById(@PathVariable Long book_id) {
        Book book = bookRepository.findById(book_id).orElse(null);
        return book;
    }
}
