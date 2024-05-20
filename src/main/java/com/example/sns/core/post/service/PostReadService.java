package com.example.sns.core.post.service;

import com.example.sns.core.common.exception.ResourceNotFoundException;
import com.example.sns.core.post.domain.entity.CursorRequest;
import com.example.sns.core.post.domain.entity.CursorResponse;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.infrastructure.repository.queryDsl.PostQueryDslRepository;
import com.example.sns.core.post.service.output.PostOutput;
import com.example.sns.core.post.service.port.PostReadRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostReadService {
    private final PostReadRepository postReadRepository;
    private final PostQueryDslRepository postQueryDslRepository;
    private final PostLikeReadService postLikeReadService;
    private final CursorPagingService cursorPagingService;

    public Post getById(Long id) {
        return postReadRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", id));
    }

    public List<PostOutput> getPosts(List<Long> postIds) {
        return postReadRepository.findAllByInId(postIds).stream().map(
                i -> PostOutput.from(i, postLikeReadService.getPostLike(i.getPostIdValue()).getLikeCount())).toList();
    }

    public CursorResponse<PostOutput> getPostsByCursor(Long writerId, String status, CursorRequest cursorRequest) {
        return cursorPagingService.getPostsByCursor(writerId, status, cursorRequest);
    }
}
