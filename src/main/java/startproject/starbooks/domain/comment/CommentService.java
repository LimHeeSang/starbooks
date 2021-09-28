package startproject.starbooks.domain.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startproject.starbooks.domain.account.Account;
import startproject.starbooks.domain.book.Book;
import startproject.starbooks.dto.CommentRequestDto;
import startproject.starbooks.domain.account.AccountRepository;
import startproject.starbooks.domain.book.BookRepository;
import startproject.starbooks.dto.CommentResponseDto;
import startproject.starbooks.exception.ApiException;
import startproject.starbooks.exception.ExceptionEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final AccountRepository accountRepository;
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    /**
     * 리뷰 조회
     */
    public Map<String, Object> readComment(Long bookId) {
        float totalStarRate = 0;
        float avgStarRate;
        int starRateCount;
        Map<String, Object> map = new HashMap<>();

        List<Comment> comments = commentRepository.findByBookIdOrderByCreatedAtDesc(bookId);
        List<CommentResponseDto> commentsDto = new ArrayList<>();

        for (Comment comment : comments) {
            totalStarRate += comment.getStarRate();

            CommentResponseDto responseDto = CommentResponseDto.builder()
                    .commentId(comment.getId())
                    .userId(comment.getAccount().getUserId())
                    .comment(comment.getComment())
                    .starRate(comment.getStarRate())
                    .build();

            commentsDto.add(responseDto);
        }


        starRateCount = comments.size();
        avgStarRate = totalStarRate / starRateCount;
        avgStarRate = Float.parseFloat(String.format("%.1f", avgStarRate));

        map.put("starRateCount", starRateCount);
        map.put("avgStarRate", avgStarRate);
        map.put("comment", commentsDto);

        return map;
    }

    /**
     * 리뷰 등록
     */
    @Transactional
    public Comment createComment(CommentRequestDto requestDto, Long bookId, Long accountId) {

        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_EXIST_BOOK)
        );

        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_REGISTER_ID)
        );

        if (commentRepository.existsByAccountIdAndBookId(accountId, bookId)) {
            throw new ApiException(ExceptionEnum.DUPLICATION_COMMENT);
        }

        Comment comment = new Comment(requestDto);
        book.addComment(comment);
        account.addComment(comment);
        commentRepository.save(comment);

        return comment;
    }

    /**
     * 리뷰 수정
     */
    @Transactional
    public Comment updateComment(CommentRequestDto requestDto, Long commentId, Long accountId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_EXIST_COMMENT)
        );

        accountRepository.findById(accountId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_REGISTER_ID)
        );


        if (!comment.getAccount().getId().equals(accountId)) {
            // "직접 작성한 댓글만 수정할 수 있습니다."
            throw new ApiException(ExceptionEnum.SECURITY_01);
        } else {
            comment.updateComment(requestDto);
        }
        return comment;
    }

    /**
     * 리뷰 삭제
     */
    @Transactional
    public Comment deleteComment(Long bookId, Long commentId, Long accountId) {

        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_EXIST_BOOK)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_EXIST_COMMENT)
        );

        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_REGISTER_ID)
        );

        if (!comment.getAccount().getId().equals(accountId)) {
            // "직접 작성한 댓글만 삭제할 수 있습니다."
            throw new ApiException(ExceptionEnum.SECURITY_01);
        } else {
            book.deleteComment(comment);
            account.deleteComment(comment);
            commentRepository.deleteById(commentId);
            return comment;
        }
    }


}
