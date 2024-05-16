package com.example.sns.application.usercaseImpl.follow.write;

import com.example.sns.application.dto.follow.DeleteFollowingUserCommand;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.follow.service.request.FollowingDeleteDto;
import com.example.sns.core.follow.service.write.FollowDeleteService;
import com.example.sns.core.user.service.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMyFollowingUserUsecase {
    private final UserReadService userReadService;
    private final FollowDeleteService followDeleteService;

    public void execute(DeleteFollowingUserCommand command) {
        Long userId = userReadService.getById(command.getFromUserId()).getId();
        Long followingId = userReadService.getById(command.getFollowingId()).getId();

        Follower fromUser = Follower.fromUserId(userId);
        Following toFollowing = Following.fromId(followingId);

        var followingDeleteDto = FollowingDeleteDto.builder()
                .fromUser(fromUser)
                .toFollowing(toFollowing)
                .build();

        followDeleteService.deleteFollowing(followingDeleteDto);

    }
}
