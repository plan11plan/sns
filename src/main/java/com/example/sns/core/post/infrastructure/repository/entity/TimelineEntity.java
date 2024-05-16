package com.example.sns.core.post.infrastructure.repository.entity;

import com.example.sns.core.post.domain.entity.Timeline;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long postId;
    private LocalDateTime createdAt;

    public static TimelineEntity from(Timeline timeline){
        TimelineEntity timelineEntity = new TimelineEntity();
        timelineEntity.id = timeline.getId();
        timelineEntity.userId = timeline.getUserId();
        timelineEntity.postId = timeline.getPostId();
        timelineEntity.createdAt = timeline.getCreatedAt();
        return timelineEntity;
    }

    public Timeline toModel(){
        return  Timeline.builder()
                .id(id)
                .userId(userId)
                .postId(postId)
                .createdAt(createdAt)
                .build();
    }

}
