package com.example.sns.application.usercaseImpl.follow.read;

import com.example.sns.application.dto.follow.GetFollowingUserCommand;
import com.example.sns.core.follow.service.read.FollowReadService;
import com.example.sns.core.follow.service.request.FollowGetDto;
import com.example.sns.core.follow.service.response.FollowDto;
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
        FollowGetDto followGetDto = FollowGetDto.fromId(command.getFollowerId());
        List<Long> followingIds = followReadService.getFollowings(followGetDto)
                .stream()
                .map(FollowDto::getFollowerId)
                .collect(Collectors.toList());

        return userReadService.getUsers(followingIds);
    }
}
