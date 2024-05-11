package com.example.sns.application.usercaseImpl.follow;

import com.example.sns.application.dto.AcceptFollowUserCommand;
import com.example.sns.core.follow.service.FollowAcceptService;
import com.example.sns.core.follow.service.dto.request.FollowAccept;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.core.user.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcceptFollowUserUsecase {
    private final UserReadService userReadService;
    private final FollowAcceptService followAcceptService;

    public void execute(AcceptFollowUserCommand command) {
        UserDto follower = userReadService.getById(command.getFollowerId());
        UserDto following = userReadService.getById(command.getFollowingId());
        FollowAccept followAccept = FollowAccept.builder()
                .follower(follower)
                .following(following)
                .build();
        followAcceptService.accept(followAccept);
    }

}
