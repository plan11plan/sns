package com.example.sns.core.post.domain.entity.request;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Title;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PostUpdate(
        @JsonProperty("title")
        Title title,
        @JsonProperty("content")
        Content content
) {
}
