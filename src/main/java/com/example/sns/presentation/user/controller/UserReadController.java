package com.example.sns.presentation.user.controller;

import com.example.sns.presentation.user.controller.response.UserResponse;
import com.example.sns.core.user.domain.service.AuthenticationService;
import com.example.sns.core.user.domain.service.UserReadService;
import com.example.sns.core.user.domain.service.UserUpdateService;
import com.example.sns.core.user.domain.service.output.NicknameHistoryOutput;
import java.util.List;
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

    @ResponseStatus
    @GetMapping("/info/{id}")
    public ResponseEntity<List<NicknameHistoryOutput>> getNicknameHistories(@PathVariable("id") Long id) {
        return ResponseEntity
                .ok()
                .body(userReadService.getNicknameHistories(id));
    }


}