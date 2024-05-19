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
    private final PostStatus postStatus;
    private final Long likeCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    @Builder
    public Post(Long id, WriterId writerId, Title title, Long likeCount, Content content, PostStatus status, LocalDateTime createdAt,
                LocalDateTime modifiedAt) {
        this.id = id;
        this.likeCount =0L;
        this.writerId = writerId;
        this.title = title;
        this.postStatus = status;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public Post upLikeCount(Long count){
        Long updatedCount = this.likeCount + count;
        return Post.builder()
                .title(title)
                .content(content)
                .status(postStatus)
                .modifiedAt(modifiedAt)
                .id(id)
                .likeCount(updatedCount)
                .writerId(writerId)
                .createdAt(createdAt)
                .build();
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

}
