package com.example.sns.core.post.service;


import com.example.sns.common.service.port.TimeHolder;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.PostLike;
import com.example.sns.core.post.domain.entity.UserId;
import com.example.sns.core.post.domain.entity.request.PostLikeCreate;
import com.example.sns.core.post.service.output.PostOutput;
import com.example.sns.core.post.service.port.PostLikeWriteRepository;
import com.example.sns.core.user.service.output.UserOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostLikeWriteService {
    private final PostLikeWriteRepository postLikeWriteRepository;
    private final TimeHolder timeHolder;

    public void create(PostOutput postOutput, UserOutput userOutput){
        PostLikeCreate postLikeCreate = PostLikeCreate.builder()
                .postId(PostId.of(postOutput.getId()))
                .userId(UserId.of(userOutput.getId()))
                .build();
        PostLike postLike = PostLike.from(postLikeCreate,timeHolder.nowDateTime());
        postLikeWriteRepository.save(postLike);
    }
}
