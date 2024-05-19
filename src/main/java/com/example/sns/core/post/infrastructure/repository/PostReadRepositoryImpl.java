package com.example.sns.core.post.infrastructure.repository;

import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.infrastructure.repository.entity.PostEntity;
import com.example.sns.core.post.infrastructure.repository.jpa.PostJpaRepository;
import com.example.sns.core.post.infrastructure.repository.queryDsl.PostQueryDslRepository;
import com.example.sns.core.post.service.port.PostReadRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostReadRepositoryImpl implements PostReadRepository {
    private final PostJpaRepository postJpaRepository;
    private final PostQueryDslRepository postQueryDslRepository;

    @Override
    public Optional<Post> findById(long id) {
        return postJpaRepository.findById(id).map(PostEntity::toModel);
    }


    public List<Post> findLatestPostsByWriterAndStatus(Long writerId, String status, int limit) {
        return postQueryDslRepository.findLatestPostsByWriterAndStatus(writerId,status,limit)
                .stream().map(PostEntity::toModel).toList();
    }

    public List<Post> findPostsByWriterAndStatusBeforeId(Long writerId, String  status, Long lastId, int limit) {
        return postQueryDslRepository.findPostsByWriterAndStatusBeforeId(writerId,status,lastId,limit)
                .stream().map(PostEntity::toModel).toList();
    }

    @Override
    public List<Post> findAllByInId(List<Long> postIds) {
        return postQueryDslRepository.findAllByInIdOrderByIdDesc(postIds).stream().map(PostEntity::toModel).toList();
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
