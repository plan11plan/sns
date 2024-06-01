package com.example.sns.core.post.infrastructure.repository;

import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.exception.PostNotFoundException;
import com.example.sns.core.post.infrastructure.repository.entity.PostEntity;
import com.example.sns.core.post.infrastructure.repository.jpa.PostJpaRepository;
import com.example.sns.core.post.domain.service.port.PostWriteRepository;
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
    public Post getById(Long id) {
        return postJpaRepository.findById(id).orElseThrow(PostNotFoundException::new).toModel();
    }



}
