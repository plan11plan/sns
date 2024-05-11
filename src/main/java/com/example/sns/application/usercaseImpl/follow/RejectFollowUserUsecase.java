package com.example.sns.application.usercaseImpl.follow;

import com.example.sns.application.dto.RejectFollowUserCommand;
import com.example.sns.core.follow.service.FollowRejectService;
import com.example.sns.core.follow.service.dto.request.FollowReject;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.core.user.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejectFollowUserUsecase {
    private final UserReadService userReadService;
    private final FollowRejectService followRejectService;
    public void execute(RejectFollowUserCommand command) {
        UserDto follower = userReadService.getById(command.getFollower().getId());
        UserDto following = userReadService.getById(command.getFollowing().getId());
        FollowReject followReject = FollowReject.builder()
                .follower(follower)
                .following(following)
                .build();
        followRejectService.reject(followReject);
    }
}
