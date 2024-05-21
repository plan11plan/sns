package com.example.sns.application.command.post;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreatePostCommand {
    private final Long writerId;
    private final String title;
    private final String content;

    @Builder
    public CreatePostCommand(Long writerId, String title, String content) {
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }
}