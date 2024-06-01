package com.example.sns.core.follow.domain.service.write;

import com.example.sns.common.service.port.TimeHolder;
import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.request.FollowAcceptionDetails;
import com.example.sns.core.follow.domain.service.input.FollowAcceptInput;
import com.example.sns.core.follow.domain.service.port.FollowRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Builder
public class FollowAcceptService {
    private final FollowRepository followRepository;
    private final TimeHolder timeHolder;


    public void accept(FollowAcceptInput dto){
        Follow follow = followRepository.findFollowByFollowerIdAndFollowingIdAndStatus(
                dto.getToFollowerId(),dto.getFromUserId(), dto.getStatus())

        .orElseThrow(() -> new IllegalArgumentException("DB에서 못찾았어용"));
        var followAcceptionDetails = FollowAcceptionDetails.builder()
                .status(FollowStatus.ACCEPTED)
                .modifiedAt(timeHolder.nowDateTime())
                .build();
        follow = follow.accept(followAcceptionDetails);
        followRepository.save(follow);
    }
}

// TODO : 아님 따로 만나요 형 ㅣ