package com.example.sns.presentation.post.controller.request;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.request.PostUpdate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateRequest {
    String title;
    String content;

    public PostUpdateRequest() {
    }

    @Builder
    public PostUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostUpdate toDomainRequest() {
        return PostUpdate.builder()
                .title(new Title(title))
                .content(new Content(content))
                .build();
    }
}
