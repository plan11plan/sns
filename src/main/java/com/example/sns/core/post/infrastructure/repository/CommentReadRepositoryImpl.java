package com.example.sns.core.post.infrastructure.repository;

import com.example.sns.core.post.domain.entity.Comment;
import com.example.sns.core.post.infrastructure.repository.entity.CommentEntity;
import com.example.sns.core.post.infrastructure.repository.jpa.CommentJpaRepository;
import com.example.sns.core.post.infrastructure.repository.queryDsl.CommentQueryDslRepository;
import com.example.sns.core.post.domain.service.port.CommentReadRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentReadRepositoryImpl implements CommentReadRepository {
    private final CommentJpaRepository commentJpaRepository;
    private final CommentQueryDslRepository commentQueryDslRepository;

    @Override
    public Optional<Comment> findById(Long id) {
        return commentJpaRepository.findById(id).map(CommentEntity::toModel);
    }

    @Override
    public Optional<List<Comment>> findByPostId(Long postId) {
        List<CommentEntity> commentEntities = commentQueryDslRepository.findByPostId(postId);
        if (commentEntities.isEmpty()) {
            return Optional.empty();
        }
        List<Comment> comments = commentEntities.stream()
                .map(CommentEntity::toModel)
                .collect(Collectors.toList());
        return Optional.of(comments);
    }

    @Override
    public Optional<List<Comment>> findByParentId(Long parentId) {
        List<CommentEntity> commentEntities = commentQueryDslRepository.findByParentId(parentId);
        if (commentEntities.isEmpty()) {
            return Optional.empty();
        }
        List<Comment> comments = commentEntities.stream()
                .map(CommentEntity::toModel)
                .collect(Collectors.toList());
        return Optional.of(comments);
    }
}
