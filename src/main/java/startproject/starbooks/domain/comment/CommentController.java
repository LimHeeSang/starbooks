package startproject.starbooks.domain.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import startproject.starbooks.domain.account.Account;
import startproject.starbooks.domain.account.AccountRepository;
import startproject.starbooks.dto.CommentRequestDto;
import startproject.starbooks.exception.ApiException;
import startproject.starbooks.exception.ExceptionEnum;
import startproject.starbooks.message.CommentMessage;
import startproject.starbooks.message.SuccessMessage;

import java.util.Map;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;
    private final AccountRepository accountRepository;

    /**
     * 댓글 조회
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/books/{book_id}/comments")
    public ResponseEntity<CommentMessage> readComment(@PathVariable("book_id") Long bookId){
        Map<String, Object> commentMap = commentService.readComment(bookId);

        CommentMessage commentMessage = CommentMessage.builder()
                .code("E0008")
                .message("정상 요청입니다")
                .commentMap(commentMap)
                .build();

        return ResponseEntity.ok(commentMessage);
    }

    /**
     * 댓글 작성
     */
    @CrossOrigin(origins = "*")
    @PostMapping("/books/{book_id}/comments")
    public ResponseEntity<SuccessMessage> createComment(@Validated @RequestBody CommentRequestDto requestDto,
                                                        BindingResult bindingResult,
                                                        @PathVariable Long book_id,
                                                        @AuthenticationPrincipal User user){

        if (bindingResult.hasErrors()) {
            throw new ApiException(ExceptionEnum.STAR_ARRANGE_ERROR);
        }

        Account findAccount = accountRepository.findByUserId(user.getUsername()).orElseThrow(
                () -> new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION)
        );

        commentService.createComment(requestDto, book_id, findAccount.getId());

        SuccessMessage successMessage = createSuccessMessage();

        return ResponseEntity.ok(successMessage);
    }

    /**
     * 댓글 수정
     */
    @CrossOrigin(origins = "*")
    @PutMapping("/books/{book_id}/comments/{comment_id}")
    public ResponseEntity<SuccessMessage> updateComment(@RequestBody CommentRequestDto requestDto,
                                                        @PathVariable("book_id") Long bookId,
                                                        @PathVariable("comment_id") Long commentId,
                                                        @AuthenticationPrincipal User user){

        Account findAccount = accountRepository.findByUserId(user.getUsername()).orElseThrow(
                () -> new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION)
        );

        commentService.updateComment(requestDto, commentId, findAccount.getId());


        SuccessMessage successMessage = createSuccessMessage();

        return ResponseEntity.ok(successMessage);
    }

    /**
     * 댓글 삭제
     */
    @CrossOrigin(origins = "*")
    @DeleteMapping("/books/{book_id}/comments/{comment_id}")
    public ResponseEntity<SuccessMessage> deleteComment(@PathVariable Long book_id, @PathVariable Long comment_id, @AuthenticationPrincipal User user){

        if (user == null) {
            throw new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION);

        }

        Account findAccount = accountRepository.findByUserId(user.getUsername()).orElseThrow(
                () -> new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION)
        );

        commentService.deleteComment(book_id, comment_id, findAccount.getId());

        SuccessMessage successMessage = createSuccessMessage();

        return ResponseEntity.ok(successMessage);
    }

    private SuccessMessage createSuccessMessage() {
        return SuccessMessage.builder()
                .code("E0008")
                .message("정상 요청입니다")
                .build();
    }
}
