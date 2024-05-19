package com.example.sns.core.post.service.port;

import com.example.sns.core.post.domain.entity.PostLike;
import java.util.List;

public interface PostLikeReadRepository {
    Long getCount(Long postId);

    List<PostLike> findLikesByPostIds(List<Long> postIds);
}

