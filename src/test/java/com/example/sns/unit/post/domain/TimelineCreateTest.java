package com.example.sns.unit.post.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.domain.entity.UserId;
import com.example.sns.core.post.domain.entity.request.TimelineCreate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TimelineCreateTest {

    @DisplayName("[생성] TimelineCreate 객체로 Timeline 객체를 생성할 수 있다.")
    @Test
    void createTimelineFromTimelineCreate() {
        // given
        UserId userId = UserId.of(1L);
        PostId postId = PostId.of(1L);
        LocalDateTime createdAt = LocalDateTime.now();

        TimelineCreate timelineCreate = TimelineCreate.builder()
                .userId(userId)
                .postId(postId)
                .build();

        // when
        Timeline timeline = Timeline.of(timelineCreate, createdAt);

        // then
        assertAll(
                () -> assertThat(timeline.getId()).isNull(),
                () -> assertThat(timeline.getUserId()).isEqualTo(userId),
                () -> assertThat(timeline.getPostId()).isEqualTo(postId),
                () -> assertThat(timeline.getCreatedAt()).isEqualTo(createdAt)
        );
    }
}
