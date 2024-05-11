package com.example.sns.application.usercaseImpl.follow;

import com.example.sns.application.dto.GetFollowingUserCommand;
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
public class GetFollowingUsecase {
    private final UserReadService userReadService;
    private final FollowReadService followReadService;

    public List<UserDto> execute(GetFollowingUserCommand command) {
        FollowGet followGet = FollowGet.fromId(command.getFollowerId());
        List<Long> followingIds = followReadService.getFollowings(followGet)
                .stream()
                .map(FollowDto::getFollowerId)
                .collect(Collectors.toList());

        return userReadService.getUsers(followingIds);
    }
}
