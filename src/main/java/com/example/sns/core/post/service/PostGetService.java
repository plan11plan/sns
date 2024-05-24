package com.example.sns.core.post.service;

import com.example.sns.core.common.exception.ResourceNotFoundException;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.service.output.PostOutput;
import com.example.sns.core.post.service.port.PostReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostGetService {
    private final PostReadRepository postReadRepository;


    public PostOutput getById(Long id) {
        Post post = postReadRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", id));
        return PostOutput.from(post);
    }


}
