package com.example.sns.core.post.infrastructure.repository.jpa;

import com.example.sns.core.post.infrastructure.repository.entity.PostLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeJpaRepository extends JpaRepository<PostLikeEntity, Long> {

    Long countAllByPostId(Long postId);
}
