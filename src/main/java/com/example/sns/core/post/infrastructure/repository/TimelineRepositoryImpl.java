package com.example.sns.core.post.infrastructure.repository;


import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.infrastructure.repository.entity.TimelineEntity;
import com.example.sns.core.post.infrastructure.repository.jpa.TimelineJpaRepository;
import com.example.sns.core.post.infrastructure.repository.queryDsl.TimelineBulkNamedParameterJdbcRepository;
import com.example.sns.core.post.service.port.TimelineRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimelineRepositoryImpl implements TimelineRepository {
    private final TimelineJpaRepository timelineJpaRepository;
    private final TimelineBulkNamedParameterJdbcRepository timelineBulkNamedParameterJdbcRepository;


    public Timeline save(Timeline timeline){
        return timelineJpaRepository.save(TimelineEntity.from(timeline)).toModel();
    }
    public void bulkInsert(List<TimelineEntity> timelineEntities){
        timelineBulkNamedParameterJdbcRepository.bulkInsert(timelineEntities);
    }
}
