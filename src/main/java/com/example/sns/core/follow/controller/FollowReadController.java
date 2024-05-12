package com.example.sns.core.follow.controller;

import com.example.sns.application.dto.GetFollowerUserCommand;
import com.example.sns.application.dto.GetFollowingUserCommand;
import com.example.sns.application.usercaseImpl.follow.GetFollowerUsecase;
import com.example.sns.application.usercaseImpl.follow.GetFollowingUsecase;
import com.example.sns.core.follow.controller.request.FollowerGetRequest;
import com.example.sns.core.follow.controller.request.FollowingGetRequest;
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
    public List<UserDto> getFollowing(@PathVariable("followerId") Follower followerId) {
        FollowingGetRequest followingGetRequest = new FollowingGetRequest(followerId);
        GetFollowingUserCommand getFollowingCommandUser = followingGetRequest.toCommand();
        return getFollowingUsecase.execute(getFollowingCommandUser);
    }

    @GetMapping("/follower/{followingId}")
    public List<UserDto> getFollower(@PathVariable("followingId") Following followingId) {
        FollowerGetRequest followerGetRequest = new FollowerGetRequest(followingId);
        GetFollowerUserCommand command = followerGetRequest.toCommand();
        return getFollowerUsecase.execute(command);
    }
}
