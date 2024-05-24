package com.example.sns.core.post.domain.entity;

import com.example.sns.core.post.domain.entity.request.TimelineCreate;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Timeline {
    private final TimelineId id;
    private final UserId userId;
    private final PostId postId;
    private final LocalDateTime createdAt;


    @Builder
    public Timeline(TimelineId id, UserId userId, PostId postId, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        //Id null일때만 createdAt 생성 허용.
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    public static Timeline of(TimelineCreate create, LocalDateTime createdAt){
        return Timeline.builder()
                .userId(create.getUserId())
                .postId(create.getPostId())
                .createdAt(createdAt)
                .build();
    }
    public Long getTimeLineIdValue(){
        return this.id != null ? this.id.getValue() : null;
    }
    public Long getUserIdValue(){
        return userId.getValue();
    }
    public Long getPostIdValue(){
        return postId.getValue();
    }
}
