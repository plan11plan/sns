package com.example.sns.core.post.service;

import com.example.sns.core.post.infrastructure.repository.queryDsl.PostLikeQueryDslRepository;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostLikeReadService {
    private final PostLikeQueryDslRepository postLikeQueryDslRepository;

    public Long getPostLike(Long postId) {
        return postLikeQueryDslRepository.countByPostId(postId);
    }

    public Map<Long, Long> getPostLikes(List<Long> postIds) {
        return postLikeQueryDslRepository.findLikesByPostIds(postIds);
    }
}

