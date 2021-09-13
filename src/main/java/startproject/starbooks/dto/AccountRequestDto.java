package startproject.starbooks.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class AccountRequestDto {

    @NotEmpty
    @NotBlank
    private String userName;

    @NotEmpty
    @NotBlank
    private String userId;

    @NotEmpty
    @NotBlank
    private String password;

    @NotEmpty
    @NotBlank
    private String passwordConfirm;

    @NotBlank
    @NotEmpty
    private String phoneNumber;

    @NotEmpty
    @NotBlank
    private String email;

    @NotNull
    private int birthDate;

}
