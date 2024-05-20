package com.example.sns.core.follow.service.write;

import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.request.FollowRejectionDetails;
import com.example.sns.core.follow.service.port.FollowRepository;
import com.example.sns.core.follow.service.input.FollowRejectInput;
import java.util.NoSuchElementException;
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
                ).orElseThrow(() -> new NoSuchElementException("Follow with ID " + dto.getStatus() + " not found."));

        System.out.println("ddd" + dto.getStatus());
        var followRejectionDetails = FollowRejectionDetails.builder()
                .status(FollowStatus.REJECTED)
                .modifiedAt(timeHolder.nowDateTime())
                .build();
        System.out.println("ddd" + followRejectionDetails.getStatus());

        follow = follow.reject(followRejectionDetails);
        System.out.println("ddd" + follow.getStatus());

        followRepository.delete(follow);
    }
}
