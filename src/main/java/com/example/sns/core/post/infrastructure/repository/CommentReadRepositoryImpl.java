package com.example.sns.core.post.infrastructure.repository;

import com.example.sns.core.post.domain.entity.Comment;
import com.example.sns.core.post.infrastructure.repository.entity.CommentEntity;
import com.example.sns.core.post.infrastructure.repository.jpa.CommentJpaRepository;
import com.example.sns.core.post.infrastructure.repository.queryDsl.CommentQueryDslRepository;
import com.example.sns.core.post.service.port.CommentReadRepository;
import java.util.List;
import java.util.Optional;
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
    public List<Comment> findByPostId(Long postId) {
        return commentQueryDslRepository.findByPostId(postId).stream().map(CommentEntity::toModel).toList();
    }

    @Override
    public List<Comment> findByParentId(Long parentId) {
        return commentQueryDslRepository.findByParentId(parentId).stream().map(CommentEntity::toModel).toList();
    }
}
