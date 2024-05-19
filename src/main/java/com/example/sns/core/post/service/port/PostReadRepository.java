package com.example.sns.core.post.service.port;

import com.example.sns.core.post.domain.entity.Post;
import java.util.List;
import java.util.Optional;

public interface PostReadRepository {
    Optional<Post> findById(long id);



    //첫 조회시, 기준 키 값이 없으므로   최신 게시글을 해당 사이즈만큼 가져옴
    List<Post> findLatestPostsByWriterAndStatus(Long writerId, String status, int limit);
    List<Post> findPostsByWriterAndStatusBeforeId(Long writerId, String  status, Long lastId, int limit);

    List<Post> findAllByInId(List<Long> postIds);

//    // Id내림차순(최신순)을 시작으로 내려가며  최신 게시글 가져옴
//    List<Post> findAllByLessThanIdAndWriterIdInAndOrderByIdDesc(Long key, List<Long> memberIds, int size);
//
//    //첫 조회시, 기준 키 값이 없으므로   최신 게시글을 해당 사이즈만큼 가져옴
//
//    List<Post> findAllByWriterIdInAndOrderByIdDesc(List<Long> memberIds, int size);
}
