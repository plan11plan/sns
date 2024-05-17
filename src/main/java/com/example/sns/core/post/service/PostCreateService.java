package com.example.sns.core.post.service;

import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.request.PostCreate;
import com.example.sns.core.post.service.dto.PostDto;
import com.example.sns.core.post.service.port.PostWriteRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class PostCreateService {
    private final PostWriteRepository postWriteRepository;
    private final TimeHolder timeHolder;
    public PostDto create(PostCreate postCreate){
        Post post = Post.from(postCreate, timeHolder.nowDateTime());
        return PostDto.from(postWriteRepository.save(post));

    }
}
