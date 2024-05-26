package com.example.sns.core.post.service;

import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.request.PostUpdate;
import com.example.sns.core.post.exception.PostNotFoundException;
import com.example.sns.core.post.service.port.PostReadRepository;
import com.example.sns.core.post.service.port.PostWriteRepository;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class PostUpdateService {
    private final PostWriteRepository postWriteRepository;
    private final PostReadRepository postReadRepository;

    public Post update(Long id, PostUpdate postUpdate){
        Post post = postReadRepository.findById(id).orElseThrow(PostNotFoundException::new);
        post = post.update(postUpdate, LocalDateTime.now());
        return postWriteRepository.save(post);
    }
}
