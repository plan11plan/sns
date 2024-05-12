package com.example.sns.application.usercaseImpl.follow;


import com.example.sns.application.dto.CancelFollowingUserCommand;
import com.example.sns.core.follow.service.FollowCancelService;
import com.example.sns.core.follow.service.dto.request.FollowingCancel;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.core.user.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CancelFollowingUserUsecase {
    private final UserReadService userReadService;
    private final FollowCancelService followCancelService;

    public void execute(CancelFollowingUserCommand command){
        UserDto follower = userReadService.getById(command.getUserId());
        UserDto following = userReadService.getById(command.getFollowingId());
        FollowingCancel followingCancel = FollowingCancel.builder()
                .follower(follower)
                .following(following)
                .build();
        followCancelService.cancel(followingCancel);
    }
}
