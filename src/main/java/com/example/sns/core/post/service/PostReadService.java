package com.example.sns.core.post.service;

import com.example.sns.core.common.exception.ResourceNotFoundException;
import com.example.sns.core.post.domain.entity.CursorRequest;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.infrastructure.repository.queryDsl.PostQueryDslRepository;
import com.example.sns.core.post.service.dto.PostDto;
import com.example.sns.core.post.service.dto.PostGeyByCursor;
import com.example.sns.core.post.service.port.PostReadRepository;
import java.util.List;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Builder
@RequiredArgsConstructor
public class PostReadService {
    private final PostReadRepository postReadRepository;
    private final PostQueryDslRepository postQueryDslRepository;
    private final PostLikeReadService postLikeReadService;

    public Post getById(Long id) {
        return postReadRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post",id));
    }
    public List<PostDto> getPosts(List<Long> postIds) {
        return postReadRepository.findAllByInId(postIds).stream().map(
               i->PostDto.from(i,postLikeReadService.getPostLike(i.getId()))).toList();
    }

    //TODO
    public CursorResponse<Post> getPostsByCursor(PostGeyByCursor request) {
        List<Post> posts = findAllBy(request.getWriterId(), request.getStatus(),request.getCursorRequest());

        Long nextKey = request.getCursorRequest().getNextKey(posts);

        return new CursorResponse<>(request.getCursorRequest().next(nextKey), posts);
    }

    private List<Post> findAllBy(Long writerId, PostStatus status, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
          return  postReadRepository.findPostsByWriterAndStatusBeforeId(writerId,status, cursorRequest.getKey(), cursorRequest.getSize());
//            return postWriteRepository.findAllByWriterId(cursorRequest.getKey(), writerId, cursorRequest.getSize()
        }
        return postReadRepository.findLatestPostsByWriterAndStatus(writerId,status,cursorRequest.getSize());
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
//            return postWriteRepository.findAllByLessThanIdAndWriterIdInAndOrderByIdDesc(
//                    cursorRequest.getKey(),
//                    writerIds,
//                    cursorRequest.getSize()
//            );
//        }
//
//        return postWriteRepository.findAllByWriterIdInAndOrderByIdDesc(writerIds, cursorRequest.getSize());
//    }


}
