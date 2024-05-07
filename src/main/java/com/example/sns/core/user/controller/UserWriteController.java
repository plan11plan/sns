package com.example.sns.core.user.controller;

import com.example.sns.core.user.controller.request.UserCreateRequest;
import com.example.sns.core.user.controller.response.UserResponse;
import com.example.sns.core.user.domain.request.UserCreate;
import com.example.sns.core.user.service.UserCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Tag(name = "유저(users)")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserWriteController {


    private final UserCreateService userCreateService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserCreateRequest request) {
        UserCreate userCreate = request.toDomainRequest();
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(UserResponse.from(userCreateService.create(userCreate)));
    }

}