package com.example.sns.core.post.infrastructure.repository;

import com.example.sns.core.common.exception.ResourceNotFoundException;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.infrastructure.repository.entity.PostEntity;
import com.example.sns.core.post.infrastructure.repository.jpa.PostJpaRepository;
import com.example.sns.core.post.infrastructure.repository.queryDsl.PostQueryDslRepository;
import com.example.sns.core.post.service.port.PostRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final PostJpaRepository postJpaRepository;
    private final PostQueryDslRepository postQueryDslRepository;

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
        return postJpaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("posts", id)).toModel();
    }

    public List<Post> findLatestPostsByWriterAndStatus(Long writerId, PostStatus status, int limit) {
        return postQueryDslRepository.findLatestPostsByWriterAndStatus(writerId,status,limit)
                .stream().map(PostEntity::toModel).toList();
    }

    public List<Post> findPostsByWriterAndStatusBeforeId(Long writerId, PostStatus status, Long lastId, int limit) {
        return postQueryDslRepository.findPostsByWriterAndStatusBeforeId(writerId,status,lastId,limit)
                .stream().map(PostEntity::toModel).toList();
    }


//    @Override
//    public List<Post> findAllByLessThanIdAndMemberIdInAndOrderByIdDesc(Long key, List<Long> userIds, int size) {
//        return postJpaRepository.findAllByIdLessThanAndWriterIdInOrderByIdDesc(key,userIds,size);
//    }
//
//    @Override
//    public List<Post> findAllByWriterIdInAndOrderByIdDesc(List<Long> userIds, int size) {
//        return postJpaRepository.findAllByWriterIdInOrderByIdDesc(userIds,size);
//    }


}
