package com.example.sns.core.post.infrastructure.repository.jpa;

import com.example.sns.core.post.infrastructure.repository.entity.TimelineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimelineJpaRepository extends JpaRepository<TimelineEntity,Long> {

}
