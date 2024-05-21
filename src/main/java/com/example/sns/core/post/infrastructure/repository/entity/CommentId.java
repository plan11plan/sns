package com.example.sns.core.post.infrastructure.repository.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class CommentId {
    Long id;

}
