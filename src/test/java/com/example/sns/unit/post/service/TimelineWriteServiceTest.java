package com.example.sns.unit.post.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.post.service.TimelineWriteService;
import com.example.sns.core.post.service.port.TimelineWriteRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TimelineWriteServiceTest {

    @Mock
    private TimelineWriteRepository timelineWriteRepository;

    @Mock
    private TimeHolder timeHolder;

    @InjectMocks
    private TimelineWriteService timelineWriteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
        verify(timelineWriteRepository, times(1)).bulkInsert(any());
    }
}
