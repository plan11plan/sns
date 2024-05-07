package com.example.sns.core.post.infrastructure;

import com.example.sns.common.exception.ResourceNotFoundException;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.service.port.PostRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final PostJpaRepository postJpaRepository;
    @Override
    public Optional<Post> findById(long id) {
        return postJpaRepository.findById(id).map(PostEntity::toModel);
    }

    @Override
    public Post save(Post post) {
        return postJpaRepository.save(PostEntity.from(post)).toModel();
    }

    @Override
    public Post getById(long id) {
        return postJpaRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("posts",id)).toModel();
    }
}
