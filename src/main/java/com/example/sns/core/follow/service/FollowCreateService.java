package com.example.sns.core.follow.service;

import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.follow.domain.Follow;
import com.example.sns.core.follow.domain.request.FollowCreate;
import com.example.sns.core.follow.service.dto.response.FollowDto;
import com.example.sns.core.follow.service.port.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowCreateService {
    private final FollowRepository followRepository;
    private final TimeHolder timeHolder;

    /*
     <고민>
      sender, receiver 멤버를 어떻게 받으면 좋을지?
      - 방법1. 식별자 Follower
      - 방법2. DTO
      그래서 식별자를 그대로 받아야할지 고민. ex) (Follower senderId, Follower receiverId)
      이러면 User 도메인 검증을 create 안에서 해야한다.
      너무 강결합인 문제가 있다.
      그래서 방법2 DTO로 가야겠다.
     */

    public FollowDto create(FollowCreate followCreate) {
        Follow follow = Follow.from(followCreate);
        follow = followRepository.save(follow);
        return FollowDto.from(follow);
    }

}
