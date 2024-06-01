package com.example.sns.application.usercaseImpl.follow.write;

import com.example.sns.application.command.follow.RejectFollowCommand;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.follow.domain.service.write.FollowRejectService;
import com.example.sns.core.follow.domain.service.input.FollowRejectInput;
import com.example.sns.core.user.domain.service.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejectFollowUsecase {
    private final UserReadService userReadService;
    private final FollowRejectService followRejectService;

    public void execute(RejectFollowCommand command) {
        //나 : 팔로잉
        // To : 팔로워
        Long userId = userReadService.getById(command.getFromUserId()).getId();
        Long followerId = userReadService.getById(command.getToFollowerId()).getId();

        Following fromUser = Following.fromUserId(userId);
        Follower toFollower = Follower.fromId(followerId);

        var followRejectDto = FollowRejectInput.builder()
                .fromUser(fromUser)
                .toFollower(toFollower)
                .build();

        followRejectService.reject(followRejectDto);
    }
}
