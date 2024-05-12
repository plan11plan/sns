package com.example.sns.core.user.controller;

import com.example.sns.core.user.controller.response.UserResponse;
import com.example.sns.core.user.service.AuthenticationService;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.core.user.service.UserUpdateService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//@Tag(name = "유저(users)")
@RestController
@Builder
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserReadController {

    private final UserReadService userReadService;
    private final UserUpdateService userUpdateService;
    private final AuthenticationService authenticationService;

    @ResponseStatus
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok()
                .body(UserResponse.from(userReadService.getById(id)));
    }




}