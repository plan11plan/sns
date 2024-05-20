package com.example.sns.core.post.service;

import com.example.sns.core.post.infrastructure.repository.entity.outputVo.PostLikeCountDaoResponse;
import com.example.sns.core.post.infrastructure.repository.queryDsl.PostLikeQueryDslRepository;
import com.example.sns.core.post.service.output.PostLikeCountOutput;
import com.example.sns.core.post.service.output.PostLikeCountsOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostLikeReadService {
    private final PostLikeQueryDslRepository postLikeQueryDslRepository;

    public PostLikeCountOutput getPostLike(Long postId) {
        PostLikeCountDaoResponse daoResponse = postLikeQueryDslRepository.countByPostId(postId);
        return PostLikeCountOutput.builder()
                .postId(daoResponse.getPostId())
                .likeCount(daoResponse.getLikeCount())
                .build();
    }

    public PostLikeCountsOutput getPostLikes(List<Long> postIds) {
        List<PostLikeCountDaoResponse> daoResponses = postLikeQueryDslRepository.findLikesByPostIds(postIds);
        List<PostLikeCountOutput> postLikeCounts = daoResponses.stream()
                .map(daoResponse -> PostLikeCountOutput.builder()
                        .postId(daoResponse.getPostId())
                        .likeCount(daoResponse.getLikeCount())
                        .build())
                .collect(Collectors.toList());
        return new PostLikeCountsOutput(postLikeCounts);
    }
}
