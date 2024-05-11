package com.example.sns.application.usercaseImpl.follow;

import com.example.sns.application.dto.GetFollowerUserCommand;
import com.example.sns.core.follow.service.FollowReadService;
import com.example.sns.core.follow.service.dto.request.FollowGet;
import com.example.sns.core.follow.service.dto.response.FollowDto;
import com.example.sns.core.user.service.UserReadService;
import com.example.sns.core.user.service.dto.UserDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetFollowerUsecase {
    private final UserReadService userReadService;
    private final FollowReadService followReadService;

    public List<UserDto> execute(GetFollowerUserCommand command) {
        FollowGet followGet = FollowGet.fromId(command.getFollowingId());
        List<Long> followingIds = followReadService.getFollowers(followGet)
                .stream()
                .map(FollowDto::getFollowingId)
                .collect(Collectors.toList());

        return userReadService.getUsers(followingIds);
    }

}
