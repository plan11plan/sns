package com.example.sns.core.follow.domain.service.write;

import com.example.sns.common.service.port.TimeHolder;
import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.request.FollowRejectionDetails;
import com.example.sns.core.follow.exception.FollowNotFoundException;
import com.example.sns.core.follow.domain.service.input.FollowRejectInput;
import com.example.sns.core.follow.domain.service.port.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowRejectService {
    private final FollowRepository followRepository;
    private final TimeHolder timeHolder;

    public void reject(FollowRejectInput dto){
        Follow follow = followRepository.findFollowByFollowerIdAndFollowingIdAndStatus(
                dto.getToFollowerId(), dto.getFromUserId(), dto.getStatus()
                ).orElseThrow(FollowNotFoundException::new);

        var followRejectionDetails = FollowRejectionDetails.builder()
                .status(FollowStatus.REJECTED)
                .modifiedAt(timeHolder.nowDateTime())
                .build();

        follow = follow.reject(followRejectionDetails);

        followRepository.delete(follow);
    }
}

