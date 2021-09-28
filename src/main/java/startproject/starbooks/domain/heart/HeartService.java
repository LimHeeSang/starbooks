package startproject.starbooks.domain.heart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import startproject.starbooks.domain.account.Account;
import startproject.starbooks.domain.account.AccountRepository;
import startproject.starbooks.domain.book.Book;
import startproject.starbooks.domain.book.BookRepository;
import startproject.starbooks.exception.ApiException;
import startproject.starbooks.exception.ExceptionEnum;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final BookRepository bookRepository;
    private final AccountRepository accountRepository;

    /**
     * 비로그인
     */
    public HashMap<String, Object> readHeart(Long bookId) {

        List<Heart> heartCount = heartRepository.findByBookId(bookId);

        HashMap<String, Object> map = new HashMap<>();

        map.put("check", false);
        map.put("heartCount", heartCount.size());

        return map;
    }

    /**
     * 로그인
     */
    public HashMap<String, Object> readLoginHeart(Long bookId, Long accountId) {

        Optional<Heart> heart = heartRepository.findByBookIdAndAccountId(bookId, accountId);

        List<Heart> heartCount = heartRepository.findByBookId(bookId);
        Integer count = heartCount.size();

        HashMap<String, Object> map = new HashMap<>();

        if (heart.isEmpty()){
            map.put("check", false);
            map.put("heartCount", count);
            return map;
        }

        map.put("check", true);
        map.put("heartCount", count);
        return map;
    }


    @Transactional
    public Heart createHeart(Long bookId, Long accountId){

        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_EXIST_BOOK)
        );

        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_REGISTER_ID)
        );

        Optional<Heart> heart = heartRepository.findByBookIdAndAccountId(bookId, accountId);

        if (heart.isEmpty()) {
            Heart newHeart = new Heart(book, account);

            heartRepository.save(newHeart);
            return newHeart;
        }

        throw new ApiException(ExceptionEnum.ALREADY_EXIST_HEART);
    }

    @Transactional
    public Heart deleteHeart(Long bookId, Long accountId){

        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_EXIST_BOOK)
        );

        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_REGISTER_ID)
        );

        Optional<Heart> heart = heartRepository.findByBookIdAndAccountId(bookId, accountId);
        Heart getHeart = heart.get();

        if (heart.isPresent()){
            book.deleteHeart(getHeart);
            account.deleteHeart(getHeart);

            heartRepository.deleteById(getHeart.getId());
            return getHeart;
        }

        throw new ApiException(ExceptionEnum.NOT_EXIST_HEART);
    }

}
