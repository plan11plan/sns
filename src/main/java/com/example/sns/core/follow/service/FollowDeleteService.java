package com.example.sns.core.follow.service;

import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.service.dto.request.FollowerDelete;
import com.example.sns.core.follow.service.dto.request.FollowingDelete;
import com.example.sns.core.follow.service.port.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowDeleteService {
    private final FollowRepository followRepository;
    public void deleteFollower(FollowerDelete request) {
        Follow follow = followRepository.findFollowerByUserIdAndFollowerId(
                request.getFollowerId(), request.getUserId(), request.getStatus()
        ).orElseThrow();
        followRepository.delete(follow);
    }
    public void deleteFollowing(FollowingDelete request){
        Follow follow = followRepository.findFollowingByUserIdAndFollowingId(
                 request.getFollowingId(),request.getUserId(), request.getStatus()
        ).orElseThrow();
        followRepository.delete(follow);
    }
}
