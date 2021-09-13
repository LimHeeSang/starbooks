package startproject.starbooks.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import startproject.starbooks.domain.Account;
import startproject.starbooks.dto.AccountRequestDto;
import startproject.starbooks.repository.AccountRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService{

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Account registerAccount(AccountRequestDto requestDto) {

        log.info("[registerAccountService 실행]");
        Account ac = Account.builder()
                .userName(requestDto.getUserName())
                .userId(requestDto.getUserId())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .birthDate(requestDto.getBirthDate())
                .phoneNumber(requestDto.getPhoneNumber())
                .email(requestDto.getEmail())
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
        accountRepository.save(ac);
        return ac;
    }


}
