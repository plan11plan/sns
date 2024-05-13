package com.example.sns.core.follow.controller;


import com.example.sns.application.dto.AcceptFollowCommand;
import com.example.sns.application.dto.CancelFollowCommand;
import com.example.sns.application.dto.RejectFollowCommand;
import com.example.sns.application.usercaseImpl.follow.write.CancelFollowUsecase;
import com.example.sns.application.usercaseImpl.follow.write.AcceptFollowUsecase;
import com.example.sns.application.usercaseImpl.follow.write.RejectFollowUsecase;
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

    private final RejectFollowUsecase rejectFollowUsecase;
    private final AcceptFollowUsecase acceptFollowUsecase;
    private final CancelFollowUsecase cancelFollowUsecase;

    @PostMapping("/reject/{followerId}")
    public void reject(@PathVariable("userId")Long userId,@PathVariable("followerId")Long followerId) {
        FollowRejectRequest followRejectRequest = new FollowRejectRequest(Follower.fromUserId(followerId),
                Following.fromUserId(userId));
        RejectFollowCommand command = followRejectRequest.toCommand();
        rejectFollowUsecase.execute(command);
    }

    @PostMapping("/accept/{followerId}")
    public void accept(@PathVariable("userId")Long userId,@PathVariable("followerId")Long followerId) {
        FollowAcceptRequest followAcceptRequest = new FollowAcceptRequest(Follower.fromUserId(followerId),
                Following.fromUserId(userId));
        AcceptFollowCommand command = followAcceptRequest.toCommand();
        acceptFollowUsecase.execute(command);
    }
    @PostMapping("/cancel/{followingId}")
    public void cancel(@PathVariable("userId")Long userId,@PathVariable("followingId")Long followingId){
        CancelFollowCommand command = new CancelFollowCommand(userId,Following.fromUserId(followingId));
        cancelFollowUsecase.execute(command);
    }

}
