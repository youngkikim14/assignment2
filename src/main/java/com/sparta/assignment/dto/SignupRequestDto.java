package com.sparta.assignment.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @NotEmpty(message = "ID는 필수값 입니다")
    @Pattern(regexp = "^([a-z0-9]*){4,10}$", message = "아이디는 4~10이내에 알파벳 소문자와 숫자로 구성되어야합니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수값 입니다")
    @Pattern(regexp = "^([A-Za-z0-9]+){8,15}$", message = "비밀번호는 8~15자 이내 알파벳 대소문자와 숫자로 구성되어야 합니다.")
    private String password;
}
