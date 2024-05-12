package com.example.sns.core.follow.service;

import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.service.dto.request.FollowReject;
import com.example.sns.core.follow.service.port.FollowRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowRejectService {
    private final FollowRepository followRepository;
    private final TimeHolder timeHolder;

    public void reject(FollowReject request){
        Follow follow = followRepository.findFollowByFollowerIdAndFollowingIdAndStatus(
                request.getFollowerId(), request.getFollowingId(), request.getStatus()
                ).orElseThrow(() -> new NoSuchElementException("Follow with ID " + request.getStatus() + " not found."));
        Follow reject = follow.reject(timeHolder);
        followRepository.save(reject);
    }
}
