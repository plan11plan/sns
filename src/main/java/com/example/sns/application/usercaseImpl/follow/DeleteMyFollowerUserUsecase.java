package com.example.sns.application.usercaseImpl.follow;

import com.example.sns.application.dto.DeleteFollowerUserCommand;
import com.example.sns.core.follow.service.FollowDeleteService;
import com.example.sns.core.follow.service.dto.request.FollowerDelete;
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
        Long followingId = userReadService.getById(command.getFollowerId()).getId();

        followDeleteService.deleteFollower(FollowerDelete.fromIds(userId, followingId));
    }

}
