package com.example.sns.core.post.service;

import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.service.port.TimelineBulkRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimelineWriteService {
    private final TimelineBulkRepository timelineBulkRepository;
    private final TimeHolder timeHolder;

    public void deliveryToTimeline(Long postId, List<Long> toUserIds) {
        List<Timeline> timelines = toUserIds.stream()
                .map((userId) -> toTimeline(postId, userId))
                .toList();
        timelineBulkRepository.bulkInsert(timelines);
    }

    private Timeline toTimeline(Long postId, Long userId) {
        return Timeline.builder()
                .userId(userId)
                .postId(postId)
                .createdAt(timeHolder.nowDateTime())
                .build();
    }
}
