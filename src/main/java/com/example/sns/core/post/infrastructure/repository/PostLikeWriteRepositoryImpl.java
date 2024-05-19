package com.example.sns.core.post.infrastructure.repository;

import com.example.sns.core.post.domain.entity.PostLike;
import com.example.sns.core.post.infrastructure.repository.entity.PostLikeEntity;
import com.example.sns.core.post.infrastructure.repository.jpa.PostLikeJpaRepository;
import com.example.sns.core.post.service.port.PostLikeWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostLikeWriteRepositoryImpl implements PostLikeWriteRepository {
    private final PostLikeJpaRepository postLikeJpaRepository;

    public void save(PostLike postLike){
        postLikeJpaRepository.save(PostLikeEntity.from(postLike));
    }
}
