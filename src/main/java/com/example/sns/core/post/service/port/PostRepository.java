package com.example.sns.core.post.service.port;

import com.example.sns.core.post.domain.entity.Post;
import java.util.Optional;

public interface PostRepository {
    Optional<Post> findById(long id);
    Post save(Post post);

    Post getById(long id);
}
