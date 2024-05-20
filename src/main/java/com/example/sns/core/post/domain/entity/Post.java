package com.example.sns.core.post.domain.entity;

import com.example.sns.core.post.domain.entity.request.PostCreate;
import com.example.sns.core.post.domain.entity.request.PostUpdate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {
    private final PostId id;
    private final WriterId writerId;
    private final Title title;
    private final Content content;
    private final PostStatus status;
    private final Long likeCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    @Builder
    public Post(PostId id, WriterId writerId, Title title, Content content, PostStatus status,
                Long likeCount,
                LocalDateTime createdAt,
                LocalDateTime modifiedAt) {
        this.id = id;
        this.writerId = writerId;
        this.title = title;
        this.status = status;
        this.content = content;
        this.likeCount = 0L;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }



    public static Post from(PostCreate postCreate, LocalDateTime localDateTime) {
        return Post.builder()
                .writerId(postCreate.getWriterId())
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .status(postCreate.getStatus())
                .createdAt(localDateTime)
                .build();
    }

    public Post update(PostUpdate postUpdate, LocalDateTime localDateTime) {
        return Post.builder()
                .title(postUpdate.getTitle())
                .content(postUpdate.getContent())
                .status(postUpdate.getStatus())
                .modifiedAt(localDateTime)
                .id(id)
                .writerId(writerId)
                .createdAt(createdAt)
                .build();
    }
    public Long getPostIdValue(){
        return id.getId();
    }
    public Long getWriterIdValue(){
        return writerId.getValue();
    }
    public String getTitleValue(){
        return title.getValue();
    }
    public String getContentValue(){
        return content.getValue();
    }
    public String getStatusValue(){
        return status.name();
    }
}
