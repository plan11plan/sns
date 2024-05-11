package com.example.sns.core.follow.controller;


import com.example.sns.application.dto.AcceptFollowUserCommand;
import com.example.sns.application.dto.CreateFollowUserCommand;
import com.example.sns.application.dto.DeleteFollowerUserCommand;
import com.example.sns.application.dto.DeleteFollowingUserCommand;
import com.example.sns.application.dto.GetFollowerUserCommand;
import com.example.sns.application.dto.GetFollowingUserCommand;
import com.example.sns.application.dto.RejectFollowUserCommand;
import com.example.sns.application.usercaseImpl.follow.AcceptFollowUserUsecase;
import com.example.sns.application.usercaseImpl.follow.CreateFollowUserUsecase;
import com.example.sns.application.usercaseImpl.follow.DeleteMyFollowingUserUsecase;
import com.example.sns.application.usercaseImpl.follow.DeleteMyFollowerUserUsecase;
import com.example.sns.application.usercaseImpl.follow.GetFollowerUsecase;
import com.example.sns.application.usercaseImpl.follow.GetFollowingUsecase;
import com.example.sns.application.usercaseImpl.follow.RejectFollowUserUsecase;
import com.example.sns.core.follow.controller.request.FollowAcceptRequest;
import com.example.sns.core.follow.controller.request.FollowCreateRequest;
import com.example.sns.core.follow.controller.request.FollowRejectRequest;
import com.example.sns.core.follow.controller.request.FollowerGetRequest;
import com.example.sns.core.follow.controller.request.FollowingGetRequest;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.user.service.dto.UserDto;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Builder
@RequestMapping("/api/users/{userId}/follows")
@RequiredArgsConstructor
public class FollowController {
    private final CreateFollowUserUsecase createFollowUserUsecase;
    private final GetFollowingUsecase getFollowingUsecase;
    private final GetFollowerUsecase getFollowerUsecase;
    private final RejectFollowUserUsecase rejectFollowUserUsecase;
    private final AcceptFollowUserUsecase acceptFollowUserUsecase;
    private final DeleteMyFollowingUserUsecase deleteMyFollowingUserUsecase;
    private final DeleteMyFollowerUserUsecase deleteMyFollowerUserUsecase;

    @PostMapping()
    public void create(@RequestBody FollowCreateRequest followCreateRequest) {
        CreateFollowUserCommand createFollowUserCommand = followCreateRequest.toCommand();
        createFollowUserUsecase.execute(createFollowUserCommand);
    }

    @GetMapping("/following/{followerId}")
    public void getFollowing(@PathVariable("followerId") Follower followerId) {
        FollowingGetRequest followingGetRequest = new FollowingGetRequest(followerId);
        GetFollowingUserCommand getFollowingCommandUser = followingGetRequest.toCommand();
        List<UserDto> execute = getFollowingUsecase.execute(getFollowingCommandUser);
    }

    @GetMapping("/follower/{followingId}")
    public void getFollower(@PathVariable("followingId") Following followingId) {
        FollowerGetRequest followerGetRequest = new FollowerGetRequest(followingId);
        GetFollowerUserCommand command = followerGetRequest.toCommand();
        getFollowerUsecase.execute(command);
    }

    @PostMapping("/reject")
    public void reject(@RequestBody FollowRejectRequest followRejectRequest) {
        RejectFollowUserCommand command = followRejectRequest.toCommand();
        rejectFollowUserUsecase.execute(command);
    }

    @PostMapping("/accept")
    public void accept(@RequestBody FollowAcceptRequest followAcceptRequest) {
        AcceptFollowUserCommand command = followAcceptRequest.toCommand();
        acceptFollowUserUsecase.execute(command);
    }

    // 내가 너를 팔로잉한것을 끊을 수 있고, 너가 나를 팔로잉한것을 내가 끊을수 있음.
// 내가 너를 팔로잉한것을 너가 끊을 수 있고, 너가 나를 팔로잉한것을 너가 끊을 수 있음.
    // 사용자가 다른 사용자를 팔로잉하는 관계를 끊음
    // 내가 다른 사용자를 팔로잉한 것을 해제
//    @DeleteMapping("/following/{followingId}")
//    public void deleteFollowing(@PathVariable("userId") Follower userId, @PathVariable("followingId") Follower followingId) {
//        DeleteFollowingUserCommand command = new DeleteFollowingUserCommand(userId, followingId);
//        deleteFollowerToFollowingUserUsecase.execute(command);
//    }
//
//    // 다른 사용자가 나를 팔로우한 것을 해제
//    @DeleteMapping("/followers/{followerId}")
//    public void deleteFollower(@PathVariable("userId") Follower userId, @PathVariable("followerId") Follower followerId) {
//        DeleteFollowerUserCommand command = new DeleteFollowerUserCommand(userId, followerId);
//        deleteFollowingToFollowerUserUsecase.execute(command);
//    }
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
