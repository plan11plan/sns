package com.example.sns.core.post.service;

import com.example.sns.core.common.exception.ResourceNotFoundException;
import com.example.sns.core.post.domain.entity.CursorRequest;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.infrastructure.repository.queryDsl.PostQueryDslRepository;
import com.example.sns.core.post.service.dto.PostDto;
import com.example.sns.core.post.service.dto.PostGeyByCursor;
import com.example.sns.core.post.service.port.PostReadRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
        return postReadRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", id));
    }

    public List<PostDto> getPosts(List<Long> postIds) {
        return postReadRepository.findAllByInId(postIds).stream().map(
                i -> PostDto.from(i, postLikeReadService.getPostLike(i.getPostIdValue()))).toList();
    }

    //TODO
    public CursorResponse<PostDto> getPostsByCursor(PostGeyByCursor request) {
        List<Post> posts = findAllBy(request.getWriterId(), request.getStatus(), request.getCursorRequest());
        List<Long> postIds = posts.stream().map(Post::getPostIdValue).toList();
        Map<Long, Long> postLikes = postLikeReadService.getPostLikes(postIds);
        List<PostDto> postDtos = convertToPostDto(posts, postLikes);

        Long nextKey = request.getCursorRequest().getNextKey(posts);

        return new CursorResponse<>(request.getCursorRequest().next(nextKey), postDtos);
    }

    private List<Post> findAllBy(Long writerId, String status, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postReadRepository.findPostsByWriterAndStatusBeforeId(writerId, status, cursorRequest.getKey(),
                    cursorRequest.getSize());
//            return postWriteRepository.findAllByWriterId(cursorRequest.getKey(), writerId, cursorRequest.getSize()
        }
        return postReadRepository.findLatestPostsByWriterAndStatus(writerId, status, cursorRequest.getSize());
    }

    private List<PostDto> convertToPostDto(List<Post> posts, Map<Long, Long> postLikes) {
        return posts.stream()
                .map(post -> PostDto.toPostDto(post, postLikes))
                .collect(Collectors.toList());
    }


}
