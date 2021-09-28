package startproject.starbooks.domain.heart;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import startproject.starbooks.domain.account.Account;
import startproject.starbooks.domain.account.AccountRepository;
import startproject.starbooks.exception.ApiException;
import startproject.starbooks.exception.ExceptionEnum;
import startproject.starbooks.message.HeartMessage;
import startproject.starbooks.message.SuccessMessage;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HeartController {

    private final HeartService heartService;
    private final AccountRepository accountRepository;

    /*@GetMapping("/books/{book_id}/heart")
    public ResponseEntity<HeartMessage> readHeart(@PathVariable("book_id") Long bookId) {

        HashMap<String, Object> heartMap = heartService.readHeart(bookId);

        HeartMessage heartMessage = createHeartMessage(heartMap);

        return ResponseEntity.ok(heartMessage);
    }*/

    @CrossOrigin(origins = "*")
    @GetMapping("/books/{book_id}/heart")
    public ResponseEntity<HeartMessage> readLoginHeart(@PathVariable("book_id") Long bookId, @AuthenticationPrincipal User user){

        if (user == null) {
            HashMap<String, Object> heartMap = heartService.readHeart(bookId);

            HeartMessage heartMessage = createHeartMessage(heartMap);

            return ResponseEntity.ok(heartMessage);

        }

        Account findAccount = accountRepository.findByUserId(user.getUsername()).orElseThrow(
                () -> new ApiException(ExceptionEnum.NOT_REGISTER_ID)
        );

        HashMap<String, Object> heartMap = heartService.readLoginHeart(bookId, findAccount.getId());

        HeartMessage heartMessage = createHeartMessage(heartMap);

        return ResponseEntity.ok(heartMessage);
    }

    private HeartMessage createHeartMessage(HashMap<String, Object> heartMap) {
        return HeartMessage.builder()
                .code("E0008")
                .message("정상 요청입니다")
                .map(heartMap)
                .build();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/books/{book_id}/heart")
    public ResponseEntity<SuccessMessage> createHeart(@PathVariable("book_id") Long bookId, @AuthenticationPrincipal User user){

        Account findAccount = accountRepository.findByUserId(user.getUsername()).orElseThrow(
                () -> new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION)
        );

        heartService.createHeart(bookId, findAccount.getId());

        SuccessMessage loginMessage = createSuccessMessage();

        return ResponseEntity.ok(loginMessage);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/books/{book_id}/heart")
    public ResponseEntity<SuccessMessage> deleteHeart(@PathVariable Long book_id, @AuthenticationPrincipal User user){

        Account findAccount = accountRepository.findByUserId(user.getUsername()).orElseThrow(
                () -> new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION)
        );

        heartService.deleteHeart(book_id, findAccount.getId());

        SuccessMessage loginMessage = createSuccessMessage();

        return ResponseEntity.ok(loginMessage);
    }

    private SuccessMessage createSuccessMessage() {
        return SuccessMessage.builder()
                .code("E0008")
                .message("정상 요청입니다")
                .build();
    }
}
