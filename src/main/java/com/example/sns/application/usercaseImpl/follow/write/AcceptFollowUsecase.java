package com.example.sns.application.usercaseImpl.follow.write;

import com.example.sns.application.dto.AcceptFollowCommand;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.follow.service.request.FollowAcceptDto;
import com.example.sns.core.follow.service.write.FollowAcceptService;
import com.example.sns.core.user.service.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcceptFollowUsecase {
    private final UserReadService userReadService;
    private final FollowAcceptService followAcceptService;

    public void execute(AcceptFollowCommand command) {
        Long userId = userReadService.getById(command.getFromUserId()).getId();
        Long followerId = userReadService.getById(command.getToFollowerId()).getId();

        Following fromUser = Following.fromUserId(userId);
        Follower toFollower = Follower.fromId(followerId);

        FollowAcceptDto followAcceptDto = FollowAcceptDto.builder()
                .fromUser(fromUser)
                .toFollower(toFollower)
                .build();

        followAcceptService.accept(followAcceptDto);
    }

}
