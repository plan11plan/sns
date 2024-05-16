package com.example.sns.core.post.service;

import com.example.sns.core.common.exception.ResourceNotFoundException;
import com.example.sns.core.post.domain.entity.CursorRequest;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.infrastructure.repository.queryDsl.PostQueryDslRepository;
import com.example.sns.core.post.service.port.PostRepository;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class PostReadService {
    private final PostRepository postRepository;
    private final PostQueryDslRepository postQueryDslRepository;

    public Post getById(Long id) {
        return postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post",id));
    }

    //TODO
    public CursorResponse<Post> getPosts(Long writerId,PostStatus status, CursorRequest cursorRequest) {
        List<Post> posts = findAllBy(writerId, status,cursorRequest);
        Long nextKey = cursorRequest.getNextKey(posts);
        return new CursorResponse<>(cursorRequest.next(nextKey), posts);
    }

    private List<Post> findAllBy(Long writerId, PostStatus status, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
          return  postRepository.findPostsByWriterAndStatusBeforeId(writerId,status, cursorRequest.getKey(), cursorRequest.getSize());
//            return postRepository.findAllByWriterId(cursorRequest.getKey(), writerId, cursorRequest.getSize()
        }
        return postRepository.findLatestPostsByWriterAndStatus(writerId,status,cursorRequest.getSize());
    }

     //TODO : 타임라인을 위한 포스트 조회

//    public CursorResponse<PostDto> getPostDtos(List<Long> writerIds, CursorRequest cursorRequest) {
//        var posts = findAllBy(writerIds, cursorRequest);
//        long nextKey = cursorRequest.getNextKey(posts);
//        var postDtos = posts.stream().map(i -> PostDto.from(i)).toList();
//        return new CursorResponse(cursorRequest.next(nextKey), postDtos);
//    }
//
//    private List<Post> findAllBy(List<Long> writerIds, CursorRequest cursorRequest) {
//        if (cursorRequest.hasKey()) {
//            return postRepository.findAllByLessThanIdAndWriterIdInAndOrderByIdDesc(
//                    cursorRequest.getKey(),
//                    writerIds,
//                    cursorRequest.getSize()
//            );
//        }
//
//        return postRepository.findAllByWriterIdInAndOrderByIdDesc(writerIds, cursorRequest.getSize());
//    }


}
