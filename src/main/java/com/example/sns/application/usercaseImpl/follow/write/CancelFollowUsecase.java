package com.example.sns.application.usercaseImpl.follow.write;


import com.example.sns.application.command.follow.CancelFollowCommand;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.follow.service.write.FollowCancelService;
import com.example.sns.core.follow.service.input.FollowCancelInput;
import com.example.sns.core.user.service.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelFollowUsecase {
    private final UserReadService userReadService;
    private final FollowCancelService followCancelService;

    public void execute(CancelFollowCommand command){
        Long userId = userReadService.getById(command.getUserId()).getId();
        Long followingId = userReadService.getById(command.getFollowingId()).getId();

        Follower fromUser = Follower.fromUserId(userId);
        Following toFollowing = Following.fromId(followingId);

        FollowCancelInput followCancelInput = FollowCancelInput.builder()
                .fromUser(fromUser)
                .toFollowing(toFollowing)
                .build();

        followCancelService.cancel(followCancelInput);
    }
}
