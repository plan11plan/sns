package com.example.sns.core.follow.service.write;

import com.example.sns.common.service.port.TimeHolder;
import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.request.FollowDeleteDetails;
import com.example.sns.core.follow.service.port.FollowRepository;
import com.example.sns.core.follow.service.input.FollowerDeleteInput;
import com.example.sns.core.follow.service.input.FollowingDeleteInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowDeleteService {
    private final FollowRepository followRepository;
    private final TimeHolder timeHolder;

    public void deleteFollower(FollowerDeleteInput request) {
        Follow follow = followRepository.findFollowerByUserIdAndFollowerId(
                request.getFromUserId(), request.getToFollowerId(), request.getStatus()
        ).orElseThrow();
        var followDeleteDetails = FollowDeleteDetails.builder()
                .status(FollowStatus.DELETED)
                .modifiedAt(timeHolder.nowDateTime())
                .build();
        follow = follow.delete(followDeleteDetails);
        followRepository.delete(follow);
    }

    public void deleteFollowing(FollowingDeleteInput request) {

        Follow follow = followRepository.findFollowingByUserIdAndFollowingId(
                request.getFromUser(), request.getFollowingId(), request.getStatus()
        ).orElseThrow();
        var followDeleteDetails = FollowDeleteDetails.builder()
                .status(FollowStatus.DELETED)
                .modifiedAt(timeHolder.nowDateTime())
                .build();
        follow = follow.delete(followDeleteDetails);
        followRepository.delete(follow);
    }
}
