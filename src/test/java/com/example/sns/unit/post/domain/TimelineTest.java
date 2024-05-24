package com.example.sns.unit.post.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.domain.entity.TimelineId;
import com.example.sns.core.post.domain.entity.UserId;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TimelineTest {

    @DisplayName("[생성] Timeline 객체를 올바르게 생성할 수 있다.")
    @Test
    void createTimeline() {
        // given
        TimelineId timelineId = TimelineId.of(1L);
        UserId userId = UserId.of(1L);
        PostId postId = PostId.of(1L);
        LocalDateTime createdAt = LocalDateTime.now();

        // when
        Timeline timeline = Timeline.builder()
                .id(timelineId)
                .userId(userId)
                .postId(postId)
                .createdAt(createdAt)
                .build();

        // then
        assertAll(
                () -> assertThat(timeline.getId()).isEqualTo(timelineId),
                () -> assertThat(timeline.getUserId()).isEqualTo(userId),
                () -> assertThat(timeline.getPostId()).isEqualTo(postId),
                () -> assertThat(timeline.getCreatedAt()).isEqualTo(createdAt)
        );
    }

    @DisplayName("[생성] Timeline 객체를 id가 null일 때 현재 시간으로 생성할 수 있다.")
    @Test
    void createTimelineWithNullId() {
        // given
        UserId userId = UserId.of(1L);
        PostId postId = PostId.of(1L);

        // when
        Timeline timeline = Timeline.builder()
                .userId(userId)
                .postId(postId)
                .createdAt(null)
                .build();

        // then
        assertAll(
                () -> assertThat(timeline.getId()).isNull(),
                () -> assertThat(timeline.getUserId()).isEqualTo(userId),
                () -> assertThat(timeline.getPostId()).isEqualTo(postId),
                () -> assertThat(timeline.getCreatedAt()).isNotNull()
        );
    }


    @DisplayName("[메서드] getTimeLineIdValue 메서드가 올바르게 동작한다.")
    @Test
    void getTimeLineIdValue() {
        // given
        TimelineId timelineId = TimelineId.of(1L);
        UserId userId = UserId.of(1L);
        PostId postId = PostId.of(1L);
        LocalDateTime createdAt = LocalDateTime.now();

        Timeline timeline = Timeline.builder()
                .id(timelineId)
                .userId(userId)
                .postId(postId)
                .createdAt(createdAt)
                .build();

        // when
        Long result = timeline.getTimeLineIdValue();

        // then
        assertThat(result).isEqualTo(1L);
    }

    @DisplayName("[메서드] getUserIdValue 메서드가 올바르게 동작한다.")
    @Test
    void getUserIdValue() {
        // given
        UserId userId = UserId.of(1L);
        PostId postId = PostId.of(1L);
        LocalDateTime createdAt = LocalDateTime.now();

        Timeline timeline = Timeline.builder()
                .userId(userId)
                .postId(postId)
                .createdAt(createdAt)
                .build();

        // when
        Long result = timeline.getUserIdValue();

        // then
        assertThat(result).isEqualTo(1L);
    }

    @DisplayName("[메서드] getPostIdValue 메서드가 올바르게 동작한다.")
    @Test
    void getPostIdValue() {
        // given
        UserId userId = UserId.of(1L);
        PostId postId = PostId.of(1L);
        LocalDateTime createdAt = LocalDateTime.now();

        Timeline timeline = Timeline.builder()
                .userId(userId)
                .postId(postId)
                .createdAt(createdAt)
                .build();

        // when
        Long result = timeline.getPostIdValue();

        // then
        assertThat(result).isEqualTo(1L);
    }
}
