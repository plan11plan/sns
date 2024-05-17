package com.example.sns.core.post.infrastructure.repository;

import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.infrastructure.repository.entity.TimelineEntity;
import com.example.sns.core.post.infrastructure.repository.queryDsl.TimelineQueryDslRepository;
import com.example.sns.core.post.service.port.TimelineReadRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimelineReadRepositoryImpl implements TimelineReadRepository {
    private final TimelineQueryDslRepository timelineQueryDslRepository;
    public List<Timeline> findLatestTimelinesByUserId(Long userId, int limit) {
        return timelineQueryDslRepository.findLatestTimelinesByWriter(userId,limit).stream()
                .map(TimelineEntity::toModel).toList();

    }

    public List<Timeline> findTimelinesByUseridBeforeId(Long userId, Long lastId, int limit) {
        return timelineQueryDslRepository.findTimelinesByWriterBeforeId(userId,lastId,limit)
                .stream().map(TimelineEntity::toModel).toList();
    }

}
