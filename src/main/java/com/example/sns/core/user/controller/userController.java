package com.example.sns.core.user.controller;


import com.example.sns.core.user.domain.entity.Nickname;
import com.example.sns.core.user.domain.entity.root.User;
import com.example.sns.core.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class userController {

    private final UserRepository userRepository;

    @PostMapping
    public Long create(){
        User user = User.builder()
                .nickname(new Nickname("22")).build();

        User save = userRepository.save(user);
        return save.getId();

    }
}
