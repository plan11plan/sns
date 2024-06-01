package com.example.sns.unit.post.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.sns.core.post.domain.entity.CursorRequest;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.domain.entity.TimelineId;
import com.example.sns.core.post.domain.entity.UserId;
import com.example.sns.core.post.domain.service.TimelineReadService;
import com.example.sns.mock.post.FakeTimelineReadRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TimelineReadServiceTest {

    private TimelineReadService timelineReadService;
    private FakeTimelineReadRepository fakeTimelineReadRepository;

    @BeforeEach
    void setUp() {
        fakeTimelineReadRepository = new FakeTimelineReadRepository();
        timelineReadService = new TimelineReadService(fakeTimelineReadRepository);
    }

    @DisplayName("[조회] 사용자 ID와 커서 요청으로 타임라인을 조회할 수 있다.")
    @Test
    void getTimelines() {
        // given
        Long userId = 1L;
        CursorRequest cursorRequest = new CursorRequest(2, null);

        Timeline timeline1 = createTimeline(1L, userId, 1L);
        Timeline timeline2 = createTimeline(2L, userId, 2L);

        fakeTimelineReadRepository.save(timeline1);
        fakeTimelineReadRepository.save(timeline2);

        // when
        List<Timeline> result = timelineReadService.getTimelines(userId, cursorRequest);

        // then
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(timeline2, timeline1);
    }

    private Timeline createTimeline(Long timelineId, Long userId, Long postId) {
        return Timeline.builder()
                .id(TimelineId.of(timelineId))
                .userId(UserId.of(userId))
                .postId(PostId.of(postId))
                .createdAt(LocalDateTime.now())
                .build();
    }
}
