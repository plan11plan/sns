package com.example.sns.core.user.controller;


import com.example.sns.core.user.controller.port.UserService;
import com.example.sns.core.user.domain.entity.Name;
import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.entity.root.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class userController {

    private final UserService userService;

    @PostMapping
    public User create() {
        return userService.create(
                User.builder().name(new Name("11111"))
                        .userStatus(UserStatus.ACTIVE)
                        .build());
    }

    @GetMapping("/{userId}")
    public User get(@PathVariable("userId") Long userId) {
        return userService.get(userId);
    }

}
