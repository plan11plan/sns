package com.example.sns.core.post.infrastructure.repository;

import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.infrastructure.repository.entity.PostEntity;
import com.example.sns.core.post.infrastructure.repository.jpa.PostJpaRepository;
import com.example.sns.core.post.infrastructure.repository.queryDsl.PostQueryDslRepository;
import com.example.sns.core.post.service.port.PostReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostReadRepositoryImpl implements PostReadRepository {
    private final PostQueryDslRepository postQueryDslRepository;
    private final PostJpaRepository postJpaRepository;

    @Override
    public Optional<Post> findById(long id) {
        return postJpaRepository.findById(id).map(PostEntity::toModel);
    }

    @Override
    public List<Post> findLatestPostsByWriterAndStatus(Long writerId, String status, int limit) {
        return postQueryDslRepository.findLatestPostsByWriterAndStatus(writerId, status, limit)
                .stream().map(PostEntity::toModel).toList();
    }

    @Override
    public List<Post> findPostsByWriterAndStatusBeforeId(Long writerId, String status, Long lastId, int limit) {
        return postQueryDslRepository.findPostsByWriterAndStatusBeforeId(writerId, status, lastId, limit)
                .stream().map(PostEntity::toModel).toList();
    }

    @Override
    public List<Post> findAllByInId(List<Long> postIds) {
        return postQueryDslRepository.findAllByInIdOrderByIdDesc(postIds)
                .stream().map(PostEntity::toModel).toList();
    }
}
