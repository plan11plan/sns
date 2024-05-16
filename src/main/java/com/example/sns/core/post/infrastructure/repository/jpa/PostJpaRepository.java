package com.example.sns.core.post.infrastructure.repository.jpa;

import com.example.sns.core.post.infrastructure.repository.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {



}
