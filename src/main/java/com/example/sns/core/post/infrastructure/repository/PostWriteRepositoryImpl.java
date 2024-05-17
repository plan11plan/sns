package com.example.sns.core.post.infrastructure.repository;

import com.example.sns.core.common.exception.ResourceNotFoundException;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.infrastructure.repository.entity.PostEntity;
import com.example.sns.core.post.infrastructure.repository.jpa.PostJpaRepository;
import com.example.sns.core.post.service.port.PostWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class PostWriteRepositoryImpl implements PostWriteRepository {
    private final PostJpaRepository postJpaRepository;

    @Override
    public Post save(Post post) {
        return postJpaRepository.save(PostEntity.from(post)).toModel();
    }

    @Override
    public Post getById(long id) {
        return postJpaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("posts", id)).toModel();
    }



}
