package com.example.sns.mock.post;

import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.service.port.TimelineReadRepository;

import java.util.*;
import java.util.stream.Collectors;

public class FakeTimelineReadRepository implements TimelineReadRepository {
    private final List<Timeline> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public List<Timeline> findLatestTimelinesByUserId(Long userId, int limit) {
        return data.stream()
                .filter(timeline -> Objects.equals(timeline.getUserId().getValue(), userId))
                .sorted(Comparator.comparing(Timeline::getCreatedAt).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Timeline> findTimelinesByUseridBeforeId(Long userId, Long lastId, int limit) {
        return data.stream()
                .filter(timeline -> Objects.equals(timeline.getUserId().getValue(), userId) && timeline.getId().getValue() < lastId)
                .sorted(Comparator.comparing(Timeline::getCreatedAt).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public void save(Timeline timeline) {
        data.add(timeline);
    }
}
