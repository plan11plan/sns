package com.example.sns.core.follow.service;


import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.service.dto.request.FollowingCancel;
import com.example.sns.core.follow.service.port.FollowRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class FollowCancelService {
    private final FollowRepository followRepository;

    public void cancel(FollowingCancel request){
        Follow follow = followRepository.findFollowingByUserIdAndFollowingId(
                request.getFollowerId(),  request.getFollowingId(), request.getStatus()
        ).orElseThrow();
        followRepository.delete(follow);
    }
}
