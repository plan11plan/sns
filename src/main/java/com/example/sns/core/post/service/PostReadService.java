package com.example.sns.core.post.service;

import com.example.sns.core.common.exception.ResourceNotFoundException;
import com.example.sns.core.post.domain.entity.CursorRequest;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.infrastructure.repository.queryDsl.PostQueryDslRepository;
import com.example.sns.core.post.service.input.PostGeyByCursorInput;
import com.example.sns.core.post.service.output.PostLikeCountOutput;
import com.example.sns.core.post.service.output.PostOutput;
import com.example.sns.core.post.service.port.PostReadRepository;
import java.util.List;
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

    public List<PostOutput> getPosts(List<Long> postIds) {
        return postReadRepository.findAllByInId(postIds).stream().map(
                i -> PostOutput.from(i, postLikeReadService.getPostLike(i.getPostIdValue()).getLikeCount())).toList();
    }

    //TODO
    public CursorResponse<PostOutput> getPostsByCursor(PostGeyByCursorInput request) {
        List<Post> posts = findAllBy(request.getWriterId(), request.getStatus(), request.getCursorRequest());
        System.out.println("Posts: " + posts); // 로그
        List<Long> postIds = posts.stream().map(Post::getPostIdValue).toList();
        List<PostLikeCountOutput> postLikes = postLikeReadService.getPostLikes(postIds);
        List<PostOutput> postOutputs = convertToPostDto(posts, postLikes);

        Long nextKey = request.getCursorRequest().getNextKey(posts);
        System.out.println("Post Outputs: " + postOutputs); // 로그 추가

        return new CursorResponse<>(request.getCursorRequest().next(nextKey), postOutputs);
    }

    private List<Post> findAllBy(Long writerId, String status, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postReadRepository.findPostsByWriterAndStatusBeforeId(writerId, status, cursorRequest.getKey(),
                    cursorRequest.getSize());
        }
        return postReadRepository.findLatestPostsByWriterAndStatus(writerId, status, cursorRequest.getSize());
    }

    private List<PostOutput> convertToPostDto(List<Post> posts, List<PostLikeCountOutput> postLikes) {
        return posts.stream()
                .map(post -> PostOutput.toPostDto(post, postLikes))
                .collect(Collectors.toList());
    }


}
