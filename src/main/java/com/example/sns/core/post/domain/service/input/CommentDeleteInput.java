package com.example.sns.core.post.domain.service.input;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentDeleteInput {
    private final Long id;

    @Builder
    public CommentDeleteInput(Long id) {
        this.id = id;
    }
}
