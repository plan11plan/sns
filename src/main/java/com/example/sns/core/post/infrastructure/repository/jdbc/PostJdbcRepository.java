package com.example.sns.core.post.infrastructure.repository.jdbc;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostJdbcRepository {
    static final String TABLE = "Post";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final RowMapper<Post> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        try {
            Long id = resultSet.getLong("id");
            WriterId writerId = new WriterId(resultSet.getLong("memberId"));
            Title title = new Title(resultSet.getString("title"));
            Content content = new Content(resultSet.getString("contents"));
            LocalDateTime createdAt = resultSet.getObject("createdAt", LocalDateTime.class);
            LocalDateTime modifiedAt = resultSet.getObject("modifiedAt", LocalDateTime.class);

            return Post.builder()
                    .id(new PostId(id))
                    .writerId(writerId)
                    .title(title)
                    .content(content)
                    .createdAt(createdAt)
                    .modifiedAt(modifiedAt)
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to map row to Post", e);
        }
    };

    public static RowMapper<Post> getRowMapper() {
        return ROW_MAPPER;
    }


    public List<Post> findAllByUserId(Long userId, int size) {
        var sql = String.format("""
                SELECT *
                FROM %s
                WHERE userId =:userId
                LIMIT :size
                """, TABLE);
        var params = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("size", size);
        return namedParameterJdbcTemplate.query(sql, params, ROW_MAPPER);
    }
    public List<Post> findAllByLessThanIdAndMemberIdInAndOrderByIdDesc(Long id, List<Long> memberIds, int size) {
        if (memberIds.isEmpty()) {
            return List.of();
        }

        var params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("memberIds", memberIds)
                .addValue("size", size);

        String query = String.format("""
                SELECT *
                FROM %s
                WHERE memberId in (:memberIds) and id < :id
                ORDER BY id DESC
                LIMIT :size
                """, TABLE);

        return namedParameterJdbcTemplate.query(query, params, ROW_MAPPER);

    }
}
