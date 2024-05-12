package com.example.sns.core.follow.service;

import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.service.dto.request.FollowAccept;
import com.example.sns.core.follow.service.port.FollowRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Builder
public class FollowAcceptService {
    private final FollowRepository followRepository;
    private final TimeHolder timeHolder;


    public void accept(FollowAccept request){
        Follow follow = followRepository.findFollowByFollowerIdAndFollowingIdAndStatus(
                request.getFollowerId(),request.getFollowingId(),request.getStatus())
                .orElseThrow();
        Follow accept = follow.accept(timeHolder);
        followRepository.save(accept);
    }
}
