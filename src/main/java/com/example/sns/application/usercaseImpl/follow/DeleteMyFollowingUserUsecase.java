package com.example.sns.application.usercaseImpl.follow;

import com.example.sns.application.dto.DeleteFollowingUserCommand;
import com.example.sns.core.follow.service.FollowDeleteService;
import com.example.sns.core.follow.service.dto.request.FollowingDelete;
import com.example.sns.core.user.service.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteMyFollowingUserUsecase {
    private final UserReadService userReadService;
    private final FollowDeleteService followDeleteService;
    // 팔로워아이디 = 유저아이디

    public void execute(DeleteFollowingUserCommand command) {
        Long userId = userReadService.getById(command.getUserId()).getId();
        Long followingId = userReadService.getById(command.getFollowingId()).getId();

        followDeleteService.deleteFollowing(FollowingDelete.fromIds(userId, followingId));

    }
}
