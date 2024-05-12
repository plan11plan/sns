package com.example.sns.core.follow.controller;


import com.example.sns.application.dto.AcceptFollowUserCommand;
import com.example.sns.application.dto.CancelFollowingUserCommand;
import com.example.sns.application.dto.RejectFollowUserCommand;
import com.example.sns.application.usercaseImpl.follow.CancelFollowingUserUsecase;
import com.example.sns.application.usercaseImpl.follow.AcceptFollowUserUsecase;
import com.example.sns.application.usercaseImpl.follow.RejectFollowUserUsecase;
import com.example.sns.core.follow.controller.request.FollowAcceptRequest;
import com.example.sns.core.follow.controller.request.FollowRejectRequest;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Builder
@RequestMapping("/api/users/{userId}/follows")
@RequiredArgsConstructor
public class FollowStatusController {

    private final RejectFollowUserUsecase rejectFollowUserUsecase;
    private final AcceptFollowUserUsecase acceptFollowUserUsecase;
    private final CancelFollowingUserUsecase cancelFollowingUserUsecase;





    @PostMapping("/reject/{followerId}")
    public void reject(@PathVariable("userId")Long userId,@PathVariable("followerId")Long followerId) {
        FollowRejectRequest followRejectRequest = new FollowRejectRequest(Follower.fromId(followerId),
                Following.fromId(userId));
        RejectFollowUserCommand command = followRejectRequest.toCommand();
        rejectFollowUserUsecase.execute(command);
    }

    @PostMapping("/accept/{followerId}")
    public void accept(@PathVariable("userId")Long userId,@PathVariable("followerId")Long followerId) {
        FollowAcceptRequest followAcceptRequest = new FollowAcceptRequest(Follower.fromId(followerId),
                Following.fromId(userId));
        AcceptFollowUserCommand command = followAcceptRequest.toCommand();
        acceptFollowUserUsecase.execute(command);
    }
    @PostMapping("/cancel/{followingId}")
    public void cancel(@PathVariable("userId")Long userId,@PathVariable("followingId")Long followingId){
        CancelFollowingUserCommand command = new CancelFollowingUserCommand(userId,Following.fromId(followingId));
        cancelFollowingUserUsecase.execute(command);
    }

}
