package startproject.starbooks.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class AccountRequestDto {

    @NotBlank(message = "userName이 빈값입니다.")
    private String userName;

    @NotBlank(message = "userId가 빈값 입니다.")
    private String userId;

    @NotBlank(message = "password가 빈값 입니다.")
    private String password;

    @NotBlank(message = "passwordConfirm가 빈값 입니다.")
    private String passwordConfirm;

    @NotBlank(message = "전화번호가 빈값입니다.")
    @NumberFormat
    private String phoneNumber;

    @NotBlank
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotNull(message = "birthDate는 null일 수 없습니다.")
    private int birthDate;

}
