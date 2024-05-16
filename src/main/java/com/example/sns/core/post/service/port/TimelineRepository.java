package com.example.sns.core.post.service.port;

import com.example.sns.core.post.domain.entity.Timeline;

public interface TimelineRepository {
    Timeline save(Timeline timeline);
}
