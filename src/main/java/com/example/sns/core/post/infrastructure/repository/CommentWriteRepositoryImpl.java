package com.example.sns.core.post.infrastructure.repository;


import com.example.sns.core.post.domain.entity.Comment;
import com.example.sns.core.post.infrastructure.repository.entity.CommentEntity;
import com.example.sns.core.post.infrastructure.repository.jpa.CommentJpaRepository;
import com.example.sns.core.post.domain.service.port.CommentWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentWriteRepositoryImpl implements CommentWriteRepository {
    private final CommentJpaRepository commentJpaRepository;


    @Override
    public Comment save(Comment comment) {
        return commentJpaRepository.save(CommentEntity.from(comment)).toModel();
    }


    @Override
    public void delete(Comment comment) {
        commentJpaRepository.delete(CommentEntity.from(comment));
    }
}
