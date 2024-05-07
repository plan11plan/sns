package com.example.sns.core.post.domain.entity;

import com.example.sns.common.service.port.ClockHolder;
import com.example.sns.core.post.domain.entity.request.PostCreate;
import com.example.sns.core.post.domain.entity.request.PostUpdate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {
    private final Long id;
    private final WriterId writerId;
    private final Title title;
    private final Content content;
    private final Long createdAt;
    private final Long modifiedAt;


    @Builder
    public Post(Long id, WriterId writerId, Title title, Content content, Long createdAt, Long modifiedAt) {
        this.id =id;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public static Post from(PostCreate postCreate, ClockHolder clockHolder){
        return Post.builder()
                .writerId(postCreate.writerId())
                .title(postCreate.title())
                .content(postCreate.content())
                .createdAt(clockHolder.millis())
                .build();
    }
    public Post update(PostUpdate postUpdate,ClockHolder clockHolder){
        return Post.builder()
                .title(postUpdate.title())
                .content(postUpdate.content())
                .modifiedAt(clockHolder.millis())
                ///
                .id(id)
                .writerId(writerId)
                .createdAt(createdAt)
                .build();
    }
}
