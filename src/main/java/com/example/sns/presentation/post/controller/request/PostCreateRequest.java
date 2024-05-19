package com.example.sns.presentation.post.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateRequest {
    @JsonProperty("writerId")
    Long writerId;
    @JsonProperty("title")
    String title;
    @JsonProperty("content")
    String content;

    @Builder
    public PostCreateRequest(Long writerId, String title, String content) {
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }
}

