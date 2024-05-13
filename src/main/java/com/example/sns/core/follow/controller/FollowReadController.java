package com.example.sns.core.follow.controller;

import com.example.sns.application.dto.GetFollowerUserCommand;
import com.example.sns.application.dto.GetFollowingUserCommand;
import com.example.sns.application.usercaseImpl.follow.read.GetFollowerUsecase;
import com.example.sns.application.usercaseImpl.follow.read.GetFollowingUsecase;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.user.service.dto.UserDto;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Builder
@RequestMapping("/api/users/{userId}/follows")
@RequiredArgsConstructor
public class FollowReadController {
    private final GetFollowingUsecase getFollowingUsecase;
    private final GetFollowerUsecase getFollowerUsecase;

    @GetMapping("/following/{followerId}")
    public List<UserDto> getFollowing(@PathVariable("followerId") Long followerId) {
        GetFollowingUserCommand command = new GetFollowingUserCommand(Follower.fromId(followerId));
        return getFollowingUsecase.execute(command);
    }

    @GetMapping("/follower/{followingId}")
    public List<UserDto> getFollower(@PathVariable("followingId") Long followingId) {
        GetFollowerUserCommand command = new GetFollowerUserCommand(Following.fromId(followingId));
        return getFollowerUsecase.execute(command);
    }
}
