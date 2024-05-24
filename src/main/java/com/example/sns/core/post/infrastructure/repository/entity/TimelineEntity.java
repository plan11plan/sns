package com.example.sns.core.post.infrastructure.repository.entity;

import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.domain.entity.TimelineId;
import com.example.sns.core.post.domain.entity.UserId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "timelines")
public class TimelineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public static TimelineEntity from(Timeline timeline) {
        TimelineEntity timelineEntity = new TimelineEntity();
        timelineEntity.userId = timeline.getUserIdValue();
        timelineEntity.postId = timeline.getPostIdValue();
        timelineEntity.createdAt = timeline.getCreatedAt();
        return timelineEntity;
    }

    public Timeline toModel() {
        return Timeline.builder()
                .id(TimelineId.of(id))
                .userId(UserId.of(userId))
                .postId(PostId.of(postId))
                .createdAt(createdAt)
                .build();
    }
}