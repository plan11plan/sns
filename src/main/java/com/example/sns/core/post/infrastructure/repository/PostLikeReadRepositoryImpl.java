package com.example.sns.core.post.infrastructure.repository;

import com.example.sns.core.post.domain.entity.PostLike;
import com.example.sns.core.post.infrastructure.repository.jpa.PostLikeJpaRepository;
import com.example.sns.core.post.service.port.PostLikeReadRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostLikeReadRepositoryImpl implements PostLikeReadRepository {
    private final PostLikeJpaRepository postLikeJpaRepository;
    @Override
    public Long getCount(Long postId) {
        return postLikeJpaRepository.countAllByPostId(postId);
    }

    @Override
    public List<PostLike> findLikesByPostIds(List<Long> postIds) {
        return null;
    }
}
