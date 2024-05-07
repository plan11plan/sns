package com.example.sns.core.post.service;

import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.service.port.PostRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class PostReadService{
    private final PostRepository postRepository;

    public Post getById(long id){
        return postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
