package com.example.sns.core.post.domain.entity.request;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.domain.entity.Title;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdate {
    private final Title title;
    private final Content content;
    private final PostStatus status;

    @Builder
    public PostUpdate(Title title, Content content, PostStatus status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }
}