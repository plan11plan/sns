package com.example.sns.core.post.service;

import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.request.PostUpdate;
import com.example.sns.core.post.service.port.PostRepository;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class PostUpdateService {
    private final PostRepository postRepository;

    public Post update(long id, PostUpdate postUpdate){
        Post post = postRepository.getById(id);
        post = post.update(postUpdate, LocalDateTime.now());
        return postRepository.save(post);
    }
}
