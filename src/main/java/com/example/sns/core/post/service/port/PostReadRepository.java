package com.example.sns.core.post.service.port;

import com.example.sns.core.post.domain.entity.Post;
import java.util.List;
import java.util.Optional;

public interface PostReadRepository {
    Optional<Post> findById(long id);



    //첫 조회시, 기준 키 값이 없으므로   최신 게시글을 해당 사이즈만큼 가져옴
    Optional<List<Post>> findLatestPostsByWriterAndStatus(Long writerId, String status, int limit);
    Optional<List<Post>> findPostsByWriterAndStatusBeforeId(Long writerId, String  status, Long lastId, int limit);

    Optional<List<Post>> findAllByInId(List<Long> postIds);


}
