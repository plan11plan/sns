package com.example.sns.core.post.service;


import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostLike;
import com.example.sns.core.post.service.port.PostLikeWriteRepository;
import com.example.sns.core.user.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostLikeWriteService {
    private final PostLikeWriteRepository postLikeWriteRepository;
    private final TimeHolder timeHolder;

    public void create(Post post, UserDto dto){
        PostLike postLike = PostLike.builder()
                .writerId(dto.getId())
                .postId(post.getId())
                .createdAt(timeHolder.nowDateTime())
                .build();
        postLikeWriteRepository.save(postLike);
    }
}
