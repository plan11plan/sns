package com.example.sns.core.user.controller;

import com.example.sns.core.user.controller.response.MyProfileResponse;
import com.example.sns.core.user.domain.request.UserUpdate;
import com.example.sns.core.user.service.AuthenticationService;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.core.user.service.UserUpdateService;
import com.example.sns.core.user.service.dto.UserDto;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저(users)")
@RestController
@RequestMapping("/api/users")
@Builder
@RequiredArgsConstructor
public class MyInfoController {

    private final AuthenticationService authenticationService;
    private final UserUpdateService userUpdateService;
    private final UserReadService userReadService;


    @GetMapping("/me")
    public ResponseEntity<MyProfileResponse> get(
//            @Parameter(name = "EMAIL", in = ParameterIn.HEADER)
//            @RequestHeader("EMAIL") String email // 일반적으로 스프링 시큐리티를 사용한다면 UserPrincipal 에서 가져옵니다.
            @RequestParam String email
    ) {
        UserDto user = userReadService.getByEmail(email);
        authenticationService.login(user.getId());
        user = userReadService.getByEmail(email);
        return ResponseEntity
                .ok()
                .body(MyProfileResponse.from(user));
    }

    @PutMapping("/me")
    @Parameter(in = ParameterIn.HEADER, name = "EMAIL")
    public ResponseEntity<MyProfileResponse> update(
//            @Parameter(name = "EMAIL", in = ParameterIn.HEADER)
//            @RequestHeader("EMAIL") String email, // 일반적으로 스프링 시큐리티를 사용한다면 UserPrincipal 에서 가져옵니다.
            @RequestParam String email,
            @RequestBody UserUpdate userUpdate
    ) {
        UserDto user = userReadService.getByEmail(email);
        user = userUpdateService.update(user.getId(), userUpdate);
        return ResponseEntity
                .ok()
                .body(MyProfileResponse.from(user));
    }
}