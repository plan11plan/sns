package com.example.sns.core.post.infrastructure.repository.jdbc;

import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.infrastructure.repository.queryDsl.TimelineBulkNamedParameterJdbcRepository;
import com.example.sns.core.post.infrastructure.repository.entity.TimelineEntity;
import com.example.sns.core.post.service.port.TimelineBulkRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TimelineBulkRepositoryImpl implements TimelineBulkRepository {

    private final TimelineBulkNamedParameterJdbcRepository timelineBulkNamedParameterJdbcRepository;


    public void bulkInsert(List<Timeline> timelines) {
        timelineBulkNamedParameterJdbcRepository.bulkInsert(timelines.stream().map(TimelineEntity::from).collect(Collectors.toList()));
    }


}
