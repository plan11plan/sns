package com.example.sns.core.post.domain.service.port;

import com.example.sns.core.post.domain.entity.Timeline;
import java.util.List;
import java.util.Optional;

public interface TimelineReadRepository {
    Optional<List<Timeline>> findLatestTimelinesByUserId(Long userId, int limit);

    Optional<List<Timeline>> findTimelinesByUseridBeforeId(Long userId, Long lastId, int limit);

}
