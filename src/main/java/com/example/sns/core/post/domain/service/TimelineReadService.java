
package com.example.sns.core.post.domain.service;

import com.example.sns.core.post.domain.entity.CursorRequest;
import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.exception.TimelineNotFoundException;
import com.example.sns.core.post.domain.service.port.TimelineReadRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimelineReadService {
    private final TimelineReadRepository timelineReadRepository;

    public List<Timeline> getTimelines(Long userId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return timelineReadRepository.findTimelinesByUseridBeforeId(cursorRequest.getKey(), userId,
                    cursorRequest.getSize()).orElseThrow(TimelineNotFoundException::new);
        }
        return timelineReadRepository.findLatestTimelinesByUserId(userId, cursorRequest.getSize())
                .orElseThrow(TimelineNotFoundException::new);
    }
}
