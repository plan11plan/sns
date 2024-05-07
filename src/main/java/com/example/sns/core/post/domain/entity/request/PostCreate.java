package com.example.sns.core.post.domain.entity.request;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PostCreate(
        @JsonProperty("writerId")
        WriterId writerId,
        @JsonProperty("title")
        Title title,
        @JsonProperty("content")
        Content content) {
}
