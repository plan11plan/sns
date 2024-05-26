package com.example.sns.unit.post.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.example.sns.common.service.port.TimeHolder;
import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.service.TimelineWriteService;
import com.example.sns.mock.post.FakeTimelineWriteRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TimelineWriteServiceTest {

    private FakeTimelineWriteRepository fakeTimelineWriteRepository;
    private TimeHolder timeHolder;
    private TimelineWriteService timelineWriteService;

    @BeforeEach
    void setUp() {
        fakeTimelineWriteRepository = new FakeTimelineWriteRepository();
        timeHolder = Mockito.mock(TimeHolder.class);
        timelineWriteService = new TimelineWriteService(fakeTimelineWriteRepository, timeHolder);
    }

    @DisplayName("[전달] 타임라인을 사용자들에게 전달할 수 있다.")
    @Test
    void deliveryToTimeline() {
        // given
        Long postId = 1L;
        List<Long> userIds = List.of(1L, 2L);
        LocalDateTime now = LocalDateTime.now();

        when(timeHolder.nowDateTime()).thenReturn(now);

        // when
        timelineWriteService.deliveryToTimeline(postId, userIds);

        // then
        List<Timeline> timelines = fakeTimelineWriteRepository.getTimelines();
        assertThat(timelines).hasSize(2);
        assertThat(timelines).allMatch(timeline -> timeline.getPostId().getValue().equals(postId));
        assertThat(timelines).extracting(timeline -> timeline.getUserId().getValue()).containsExactlyInAnyOrderElementsOf(userIds);
        assertThat(timelines).allMatch(timeline -> timeline.getCreatedAt().equals(now));
    }
}
