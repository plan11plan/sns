package com.example.sns.application.usercaseImpl.follow.write;

import com.example.sns.application.command.follow.DeleteFollowerUserCommand;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.follow.domain.service.input.FollowerDeleteInput;
import com.example.sns.core.follow.domain.service.write.FollowDeleteService;
import com.example.sns.core.user.domain.service.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMyFollowerUserUsecase {
    private final UserReadService userReadService;
    private final FollowDeleteService followDeleteService;

    public void execute(DeleteFollowerUserCommand command) {
        Long userId = userReadService.getById(command.getUserId()).getId();
        Long followerId = userReadService.getById(command.getFollowerId()).getId();

        Following fromUser = Following.fromUserId(userId);
        Follower toFollower = Follower.fromId(followerId);

        var followerDeleteDto = FollowerDeleteInput.builder()
                .fromUser(fromUser)
                .toFollower(toFollower)
                .build();

        followDeleteService.deleteFollower(followerDeleteDto);
    }

}
