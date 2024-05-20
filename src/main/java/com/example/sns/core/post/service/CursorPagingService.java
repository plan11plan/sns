package com.example.sns.core.post.service;

import com.example.sns.core.post.domain.entity.CursorRequest;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.Timeline;
import com.example.sns.core.post.service.output.PostLikeCountsOutput;
import com.example.sns.core.post.service.output.PostOutput;
import com.example.sns.core.post.service.port.PostReadRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CursorPagingService {
    private final PostReadRepository postReadRepository;
    private final PostLikeReadService postLikeReadService;
    private final TimelineReadService timelineReadService;

    public CursorResponse<PostOutput> getPostsByCursor(Long writerId, String status, CursorRequest cursorRequest) {
        List<Post> posts = findAllBy(writerId, status, cursorRequest);
        List<Long> postIds = posts.stream().map(Post::getPostIdValue).toList();
        PostLikeCountsOutput postLikes = postLikeReadService.getPostLikes(postIds);

        List<PostOutput> postOutputs = convertToPostDto(posts, postLikes);
        Long nextKey = cursorRequest.getNextKey(posts);
        return new CursorResponse<>(cursorRequest.next(nextKey), postOutputs);
    }

    public CursorResponse<PostOutput> getPostsByIds(List<Long> postIds, CursorRequest cursorRequest) {
        List<Post> posts = postReadRepository.findAllByInId(postIds);
        PostLikeCountsOutput postLikes = postLikeReadService.getPostLikes(postIds);

        List<PostOutput> postOutputs = convertToPostDto(posts, postLikes);
        Long nextKey = cursorRequest.getNextKey(posts);
        return new CursorResponse<>(cursorRequest.next(nextKey), postOutputs);
    }

    public CursorResponse<PostOutput> getPostsByTimeline(Long userId, CursorRequest cursorRequest) {
        List<Timeline> timelines = timelineReadService.getTimelines(userId, cursorRequest);
        List<Long> postIds = timelines.stream().map(Timeline::getPostId).toList();
        return getPostsByIds(postIds, cursorRequest);
    }

    private List<Post> findAllBy(Long writerId, String status, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return postReadRepository.findPostsByWriterAndStatusBeforeId(writerId, status, cursorRequest.getKey(),
                    cursorRequest.getSize());
        }
        return postReadRepository.findLatestPostsByWriterAndStatus(writerId, status, cursorRequest.getSize());
    }

    private List<PostOutput> convertToPostDto(List<Post> posts, PostLikeCountsOutput postLikes) {
        return posts.stream()
                .map(post -> PostOutput.toPostDto(post, postLikes))
                .collect(Collectors.toList());
    }
}
