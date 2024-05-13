package com.example.sns.application.usercaseImpl.follow.write;

import com.example.sns.application.dto.DeleteFollowerUserCommand;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.follow.service.write.FollowDeleteService;
import com.example.sns.core.follow.service.request.FollowerDeleteDto;
import com.example.sns.core.user.service.UserReadService;
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

        var followerDeleteDto = FollowerDeleteDto.builder()
                .fromUser(fromUser)
                .toFollower(toFollower)
                .build();

        followDeleteService.deleteFollower(followerDeleteDto);
    }

}
