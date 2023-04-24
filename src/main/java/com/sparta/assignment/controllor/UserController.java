package com.sparta.assignment.controllor;

import com.sparta.assignment.dto.SigninRequestDto;
import com.sparta.assignment.dto.SignupRequestDto;
import com.sparta.assignment.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;   // userservice랑 연결, 생성자 주입을 통한 DI

    @PostMapping("/signup")
    public String signup(@Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return "회원가입 완료!";
    }

    @PostMapping("/signin") // signin에 필요한 dto값과 jwt 토큰을 함께 서비스로 전달
    public String signin(SigninRequestDto signinRequestDto, HttpServletResponse response){
        userService.signin(signinRequestDto, response);
        return "로그인 완료!";
    }
}
