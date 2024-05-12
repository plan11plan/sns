package com.example.sns.core.follow.controller;

import com.example.sns.application.dto.DeleteFollowerUserCommand;
import com.example.sns.application.dto.DeleteFollowingUserCommand;
import com.example.sns.application.usercaseImpl.follow.DeleteMyFollowerUserUsecase;
import com.example.sns.application.usercaseImpl.follow.DeleteMyFollowingUserUsecase;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Builder
@RequestMapping("/api/users/{userId}/follows")
@RequiredArgsConstructor
public class FollowDeleteController {
    private final DeleteMyFollowingUserUsecase deleteMyFollowingUserUsecase;
    private final DeleteMyFollowerUserUsecase deleteMyFollowerUserUsecase;
    @DeleteMapping("/following/{followingId}")
    public void deleteFollowing(@PathVariable("userId") Long userId, @PathVariable("followingId") Long followingId) {
        DeleteFollowingUserCommand command = new DeleteFollowingUserCommand(userId, Following.fromId(followingId));
        deleteMyFollowingUserUsecase.execute(command);
    }

    // 다른 사용자가 나를 팔로우한 것을 해제
    @DeleteMapping("/follower/{followerId}")
    public void deleteFollower(@PathVariable("userId") Long userId, @PathVariable("followerId") Long followerId) {
        DeleteFollowerUserCommand command = new DeleteFollowerUserCommand(userId, Follower.fromId(followerId));
        deleteMyFollowerUserUsecase.execute(command);

    }

}
