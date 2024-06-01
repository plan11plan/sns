package com.example.sns.core.post.domain.service.port;

import com.example.sns.core.post.infrastructure.repository.entity.outputVo.PostLikeCountDaoResponse;
import java.util.List;

public interface PostLikeReadRepository {
    Long getCount(Long postId);

    PostLikeCountDaoResponse countByPostId(Long postId);

    List<PostLikeCountDaoResponse> findLikesByPostIds(List<Long> postIds);

}

