package com.example.sns.core.post.infrastructure.repository;

import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.infrastructure.repository.entity.TimelineEntity;
import com.example.sns.core.post.infrastructure.repository.queryDsl.TimelineQueryDslRepository;
import com.example.sns.core.post.domain.service.port.TimelineReadRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimelineReadRepositoryImpl implements TimelineReadRepository {
    private final TimelineQueryDslRepository timelineQueryDslRepository;
    public Optional<List<Timeline>> findLatestTimelinesByUserId(Long userId, int limit) {
        List<TimelineEntity> timelineEntities = timelineQueryDslRepository.findLatestTimelinesByWriter(userId,
                limit).orElse(Collections.emptyList());
        return Optional.of(timelineEntities.stream().map(TimelineEntity::toModel).toList());

    }

    public Optional<List<Timeline>> findTimelinesByUseridBeforeId(Long userId, Long lastId, int limit) {
        List<TimelineEntity> timelineEntities = timelineQueryDslRepository.findTimelinesByWriterBeforeId(
                userId, lastId, limit).orElse(Collections.emptyList());
        return Optional.of(timelineEntities.stream().map(TimelineEntity::toModel).toList());
    }

}
