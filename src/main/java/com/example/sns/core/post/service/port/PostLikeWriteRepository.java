package com.example.sns.core.post.service.port;

import com.example.sns.core.post.domain.entity.PostLike;

public interface PostLikeWriteRepository {

    void save(PostLike postLike);
}
