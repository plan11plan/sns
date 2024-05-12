package com.example.sns.core.follow.controller;

import com.example.sns.application.dto.CreateFollowUserCommand;
import com.example.sns.application.usercaseImpl.follow.CreateFollowUserUsecase;
import com.example.sns.core.follow.controller.request.FollowCreateRequest;
import com.example.sns.core.follow.service.dto.response.FollowDto;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Builder
@RequestMapping("/api/users/follows")
@RequiredArgsConstructor
public class FollowCreateController {
    private final CreateFollowUserUsecase createFollowUserUsecase;
    @PostMapping()
    public FollowDto create(@RequestBody FollowCreateRequest followCreateRequest) {
        CreateFollowUserCommand createFollowUserCommand = followCreateRequest.toCommand();
         return createFollowUserUsecase.execute(createFollowUserCommand);
    }
}
