package com.example.sns.core.post.domain.entity;

import com.example.sns.core.post.domain.entity.request.PostCreate;
import com.example.sns.core.post.domain.entity.request.PostUpdate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {
    private final Long id;
    private final WriterId writerId;
    private final Title title;
    private final Content content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    @Builder
    public Post(Long id, WriterId writerId, Title title, Content content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id =id;
        this.writerId = writerId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public static Post from(PostCreate postCreate,LocalDateTime localDateTime){
        return Post.builder()
                .writerId(postCreate.writerId())
                .title(postCreate.title())
                .content(postCreate.content())
                .createdAt(localDateTime)
                .build();
    }
    public Post update(PostUpdate postUpdate,LocalDateTime localDateTime){
        return Post.builder()
                .title(postUpdate.title())
                .content(postUpdate.content())
                .modifiedAt(localDateTime)
                ///
                .id(id)
                .writerId(writerId)
                .createdAt(createdAt)
                .build();
    }
}
