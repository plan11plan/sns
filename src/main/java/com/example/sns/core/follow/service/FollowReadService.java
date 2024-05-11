package com.example.sns.core.follow.service;


import com.example.sns.core.common.exception.ResourceNotFoundException;
import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.service.dto.request.FollowGet;
import com.example.sns.core.follow.service.dto.response.FollowDto;
import com.example.sns.core.follow.service.port.FollowRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FollowReadService {

    private final FollowRepository followRepository;

    public List<FollowDto> getFollowings(FollowGet request) {
        List<Follow> follows = followRepository.findAllByFollowerIdAndStatus(request.getUserId(),request.getStatus())
                .orElseThrow(() -> new ResourceNotFoundException("follow", request.getUserId()));
        return FollowDto.from(follows);
    }
    public List<FollowDto> getFollowers(FollowGet request){
        List<Follow> follows = followRepository.findAllByFollowingIdAndStatus(request.getUserId(),request.getStatus())
                .orElseThrow(() -> new ResourceNotFoundException("follow", request.getUserId()));
        return FollowDto.from(follows);
    }
}
