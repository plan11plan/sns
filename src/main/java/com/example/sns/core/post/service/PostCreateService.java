package com.example.sns.core.post.service;

import com.example.sns.common.service.port.ClockHolder;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.request.PostCreate;
import com.example.sns.core.post.service.port.PostRepository;
import com.example.sns.core.user.service.port.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class PostCreateService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ClockHolder clockHolder;

    public Post create(PostCreate postCreate){
        Post post = Post.from(postCreate,clockHolder);
        return postRepository.save(post);

    }
}
