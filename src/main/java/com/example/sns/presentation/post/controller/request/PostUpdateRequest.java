package com.example.sns.presentation.post.controller.request;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.request.PostUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateRequest {
    @JsonProperty("title")
    Title title;
    @JsonProperty("content")
    Content content;

    @Builder
    public PostUpdateRequest(Title title, Content content) {
        this.title = title;
        this.content = content;
    }

    public PostUpdate toDomainRequest() {
        return PostUpdate.builder()
                .title(title)
                .content(content)
                .build();
    }
}
