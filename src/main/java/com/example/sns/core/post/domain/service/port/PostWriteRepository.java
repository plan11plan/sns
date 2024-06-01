package com.example.sns.core.post.domain.service.port;

import com.example.sns.core.post.domain.entity.Post;

public interface PostWriteRepository {
    Post save(Post post);

    Post getById(Long id);

}
