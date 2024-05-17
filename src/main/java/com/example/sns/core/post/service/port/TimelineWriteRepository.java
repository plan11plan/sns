package com.example.sns.core.post.service.port;

import com.example.sns.core.post.domain.entity.Timeline;
import java.util.List;

public interface TimelineWriteRepository {
    Timeline save(Timeline timeline);
    void bulkInsert(List<Timeline> timelines);

}
