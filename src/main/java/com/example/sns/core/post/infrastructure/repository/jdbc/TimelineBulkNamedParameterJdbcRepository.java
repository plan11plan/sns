package com.example.sns.core.post.infrastructure.repository.jdbc;

import com.example.sns.core.post.infrastructure.repository.entity.TimelineEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TimelineBulkNamedParameterJdbcRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;



//    public void batchInsert(int batchSize, List<TimelineEntity> timelines) {
//        String sql = "INSERT INTO timelines (id, user_id, post_id, created_at) VALUES (?, ?, ?, ?)";
//        jdbcTemplate.batchUpdate(
//                sql,
//                new BatchPreparedStatementSetter() {
//                    @Override
//                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//                        TimelineEntity timeline = timelines.get(i);
//                        ps.setLong(1, timeline.getId());
//                        ps.setLong(2, timeline.getUserId());
//                        ps.setLong(3, timeline.getPostId());
//                        ps.setTimestamp(4, java.sql.Timestamp.valueOf(timeline.getCreatedAt()));
//                    }
//                    @Override
//                    public int getBatchSize() {
//                        return timelines.size();
//                    }
//                }
//        );
//    }
public void bulkInsert(List<TimelineEntity> timelineEntities) {
    String sql = "INSERT INTO timelines (user_id, post_id, created_at) VALUES (:userId, :postId, :createdAt)";
    SqlParameterSource[] params = timelineEntities.stream()
            .map(timeline -> new BeanPropertySqlParameterSource(timeline))
            .toArray(SqlParameterSource[]::new);
    namedParameterJdbcTemplate.batchUpdate(sql, params);
}
}

