package com.example.sns.core.post.infrastructure.repository;

import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.infrastructure.repository.entity.PostEntity;
import com.example.sns.core.post.infrastructure.repository.jpa.PostJpaRepository;
import com.example.sns.core.post.infrastructure.repository.queryDsl.PostQueryDslRepository;
import com.example.sns.core.post.service.port.PostReadRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public Optional<List<Post>> findLatestPostsByWriterAndStatus(Long writerId, String status, int limit) {
        List<PostEntity> postEntities = postQueryDslRepository.findLatestPostsByWriterAndStatus(
                writerId, status, limit).orElse(Collections.emptyList());
        return Optional.of(postEntities.stream().map(PostEntity::toModel).toList());
    }

    @Override
    public Optional<List<Post>> findPostsByWriterAndStatusBeforeId(Long writerId, String status, Long lastId,
                                                                   int limit) {
        List<PostEntity> postEntities = postQueryDslRepository.findPostsByWriterAndStatusBeforeId(writerId, status,
                lastId, limit).orElse(Collections.emptyList());
        return Optional.of(postEntities.stream().map(PostEntity::toModel).toList());
    }

    @Override
    public Optional<List<Post>> findAllByInId(List<Long> postIds) {
        List<PostEntity> postEntities = postQueryDslRepository.findAllByInIdOrderByIdDesc(postIds)
                .orElse(Collections.emptyList());
        return Optional.of(postEntities.stream().map(PostEntity::toModel).toList());
    }
}
