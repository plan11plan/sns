package com.example.sns.application.usercaseImpl.follow.write;

import com.example.sns.application.command.follow.SendFollowCommand;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.follow.domain.service.input.FollowCreateInput;
import com.example.sns.core.follow.domain.service.write.FollowCreateService;
import com.example.sns.core.user.domain.service.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendFollowUsecase {
    private final UserReadService userReadService;
    private final FollowCreateService followCreateService;

    public void execute(SendFollowCommand sendFollowCommand) {

        Long userId = userReadService.getById(sendFollowCommand.getUserId()).getId();
        Long followingId = userReadService.getById(sendFollowCommand.getFollowingId()).getId();

        Follower fromUser = Follower.fromUserId(userId);
        Following toFollowing = Following.fromId(followingId);

        var followCreateDto = FollowCreateInput.builder()
                .fromUser(fromUser)
                .toFollowing(toFollowing)
                .build();

        followCreateService.create(followCreateDto);

    }
}
