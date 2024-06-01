package com.example.sns.mock.post;

import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.domain.service.port.TimelineWriteRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FakeTimelineWriteRepository implements TimelineWriteRepository {
    private final List<Timeline> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Timeline save(Timeline timeline) {
        data.add(timeline);
        return timeline;
    }

    @Override
    public void bulkInsert(List<Timeline> timelines) {
        data.addAll(timelines);

    }
    public List<Timeline> getTimelines() {
        return data;
    }
}
