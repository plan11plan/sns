package com.example.sns.presentation.follow.controller;

import com.example.sns.application.dto.follow.SendFollowCommand;
import com.example.sns.application.usercaseImpl.follow.write.SendFollowUsecase;
import com.example.sns.presentation.follow.controller.request.FollowCreateRequest;
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
    private final SendFollowUsecase sendFollowUsecase;

    @PostMapping()
    public void create(@RequestBody FollowCreateRequest followCreateRequest) {
        SendFollowCommand sendFollowCommand = followCreateRequest.toCommand();
        sendFollowUsecase.execute(sendFollowCommand);
    }
}
