package com.example.sns.core.post.service;

import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.domain.entity.UserId;
import com.example.sns.core.post.domain.entity.request.TimelineCreate;
import com.example.sns.core.post.service.port.TimelineWriteRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimelineWriteService {
    private final TimelineWriteRepository timelineWriteRepository;
    private final TimeHolder timeHolder;

    public void deliveryToTimeline(Long postId, List<Long> toUserIds) {
        List<Timeline> timelines = toUserIds.stream()
                .map((userId) -> Timeline.of(
                        new TimelineCreate(UserId.of(userId), PostId.of(postId)),
                        timeHolder.nowDateTime()))
                .toList();
        timelineWriteRepository.bulkInsert(timelines);
    }

}
