package com.example.sns.presentation.user.controller;

import com.example.sns.core.user.domain.service.AuthenticationService;
import com.example.sns.core.user.domain.service.UserSignupService;
import com.example.sns.core.user.domain.service.UserUpdateService;
import com.example.sns.core.user.domain.service.input.UserCreateInput;
import com.example.sns.core.user.domain.service.output.UserOutput;
import com.example.sns.presentation.user.controller.request.SignupRequest;
import com.example.sns.presentation.user.controller.response.UserResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Tag(name = "유저(users)")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class SignupController {


    private final UserSignupService userSigninService;
    private final AuthenticationService authenticationService;
    private final UserUpdateService userUpdateService;



    /*
    요청시 - 이메일 발송(상태 펜딩) - 응답 , ( 이메일 발송 안에 새로운 요청 응답시(상태 액티브))
     */
    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signup(@RequestBody SignupRequest command) {
        UserCreateInput userCreateInput = UserCreateInput.builder()
                .email(command.getEmail())
                .nickname(command.getNickname())
                .sex(command.getSex())
                .birthDay(command.getBirthday())
                .password(command.getPassword())
                .build();
        UserOutput userOutput = userSigninService.signup(userCreateInput);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(UserResponse.from(userOutput));
    }
    @GetMapping("/{id}/verify")
    public ResponseEntity<Void> verifyEmail(@PathVariable("id") Long id, @RequestParam String certificationCode) {
        authenticationService.verifyEmail(id, certificationCode);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("http://localhost:8080/hi"))
                .build();
    }

    @PutMapping("/ad")
    public String roleToAdmin(@RequestBody UpdateRoleRequest request){
       return userUpdateService.roleToAdmin(request.getId());
    }

}