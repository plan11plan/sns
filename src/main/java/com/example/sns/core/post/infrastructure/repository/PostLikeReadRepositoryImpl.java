package com.example.sns.core.post.infrastructure.repository;

import com.example.sns.core.post.infrastructure.repository.entity.outputVo.PostLikeCountDaoResponse;
import com.example.sns.core.post.infrastructure.repository.jpa.PostLikeJpaRepository;
import com.example.sns.core.post.infrastructure.repository.queryDsl.PostLikeQueryDslRepository;
import com.example.sns.core.post.service.port.PostLikeReadRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostLikeReadRepositoryImpl implements PostLikeReadRepository {
    private final PostLikeJpaRepository postLikeJpaRepository;
    private final PostLikeQueryDslRepository postLikeQueryDslRepository;
    @Override
    public Long getCount(Long postId) {
        return postLikeJpaRepository.countAllByPostId(postId);
    }

    @Override
    public PostLikeCountDaoResponse countByPostId(Long postId) {
        return postLikeQueryDslRepository.countByPostId(postId);
    }

    @Override
    public List<PostLikeCountDaoResponse> findLikesByPostIds(List<Long> postIds) {
        return postLikeQueryDslRepository.findLikesByPostIds(postIds);
    }

}
