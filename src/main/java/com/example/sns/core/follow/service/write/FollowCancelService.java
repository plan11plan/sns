package com.example.sns.core.follow.service.write;


import com.example.sns.common.service.port.TimeHolder;
import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.domain.FollowStatus;
import com.example.sns.core.follow.domain.request.FollowCancelDetails;
import com.example.sns.core.follow.service.port.FollowRepository;
import com.example.sns.core.follow.service.input.FollowCancelInput;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class FollowCancelService {
    private final FollowRepository followRepository;
    private final TimeHolder timeHolder;

    public void cancel(FollowCancelInput dto) {
        Follow follow = followRepository.findFollowingByUserIdAndFollowingId(
                dto.getFromUserId(), dto.getToFollowingId(), dto.getStatus()
        ).orElseThrow(() -> new IllegalArgumentException("DB에서 못찾았어용"));
        var followCancelDetails = FollowCancelDetails.builder()
                .modifiedAt(timeHolder.nowDateTime())
                .status(FollowStatus.CANCELLED)
                .build();
        follow = follow.cancel(followCancelDetails);
        followRepository.delete(follow);
    }
}
