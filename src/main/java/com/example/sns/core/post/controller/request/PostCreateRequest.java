package com.example.sns.core.post.controller.request;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import com.example.sns.core.post.domain.entity.request.PostCreate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateRequest {
    @JsonProperty("writerId")
    WriterId writerId;
    @JsonProperty("title")
    Title title;
    @JsonProperty("content")
    Content content;

    @Builder
    public PostCreateRequest(WriterId writerId, Title title, Content content) {
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }
    public PostCreate toDomainRequest() {
        return PostCreate.builder()
                .writerId(writerId)
                .title(title)
                .content(content)
                .build();
    }
}
