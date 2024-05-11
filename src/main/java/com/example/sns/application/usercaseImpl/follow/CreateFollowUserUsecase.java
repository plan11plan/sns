package com.example.sns.application.usercaseImpl.follow;

import com.example.sns.application.dto.CreateFollowUserCommand;
import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.follow.domain.Following;
import com.example.sns.core.follow.domain.Follower;
import com.example.sns.core.follow.domain.request.FollowCreate;
import com.example.sns.core.follow.service.FollowCreateService;
import com.example.sns.core.follow.service.dto.response.FollowDto;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.core.user.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFollowUserUsecase {
    private final UserReadService userReadService;
    private final FollowCreateService followCreateService;
    private final TimeHolder timeHolder;

    public FollowDto execute(CreateFollowUserCommand createFollowUserCommand) {
        UserDto follower = userReadService.getById(createFollowUserCommand.getFollower().getId());
        UserDto following = userReadService.getById(createFollowUserCommand.getFollowing().getId());
        FollowCreate followCreate = FollowCreate.builder()
                .follower(Follower.fromId(follower.getId()))
                .following(Following.fromId(following.getId()))
                .createdAt(timeHolder.nowDateTime())
                .build();
        return followCreateService.create(followCreate);

    }
}
