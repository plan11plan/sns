package com.example.sns.core.post.infrastructure.repository.jpa;

import com.example.sns.core.post.infrastructure.repository.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity,Long> {

}
