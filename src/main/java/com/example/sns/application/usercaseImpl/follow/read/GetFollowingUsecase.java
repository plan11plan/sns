package com.example.sns.application.usercaseImpl.follow.read;

import com.example.sns.application.command.follow.GetFollowingUserCommand;
import com.example.sns.core.follow.domain.service.read.FollowReadService;
import com.example.sns.core.follow.domain.service.input.FollowGetInput;
import com.example.sns.core.follow.domain.service.output.FollowOutput;
import com.example.sns.core.user.domain.service.UserReadService;
import com.example.sns.core.user.domain.service.output.UserOutput;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetFollowingUsecase {
    private final UserReadService userReadService;
    private final FollowReadService followReadService;

    public List<UserOutput> execute(GetFollowingUserCommand command) {
        FollowGetInput followGetInput = FollowGetInput.fromId(command.getFollowerId());
        List<Long> followingIds = followReadService.getFollowings(followGetInput)
                .stream()
                .map(FollowOutput::getFollowingId)
                .collect(Collectors.toList());
        return userReadService.getUsers(followingIds);
    }
}
