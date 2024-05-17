package com.example.sns.core.post.service.port;

import com.example.sns.core.post.domain.entity.Timeline;
import java.util.List;

public interface TimelineReadRepository {
    List<Timeline> findLatestTimelinesByUserId(Long userId, int limit);

    List<Timeline> findTimelinesByUseridBeforeId(Long userId, Long lastId, int limit);

}
