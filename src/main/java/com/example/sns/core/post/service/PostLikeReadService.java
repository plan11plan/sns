package com.example.sns.core.post.service;

import com.example.sns.core.post.infrastructure.repository.entity.outputVo.PostLikeCountDaoResponse;
import com.example.sns.core.post.service.output.PostLikeCountOutput;
import com.example.sns.core.post.service.output.PostLikeCountsOutput;
import com.example.sns.core.post.service.port.PostLikeReadRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostLikeReadService {
    private final PostLikeReadRepository postLikeReadRepository;

    public PostLikeCountOutput getPostLike(Long postId) {
        PostLikeCountDaoResponse daoResponse = postLikeReadRepository.countByPostId(postId);
        return PostLikeCountOutput.builder()
                .postId(daoResponse.getPostId())
                .likeCount(daoResponse.getLikeCount())
                .build();
    }

    public PostLikeCountsOutput getPostLikes(List<Long> postIds) {
        List<PostLikeCountDaoResponse> daoResponses = postLikeReadRepository.findLikesByPostIds(postIds);
        Map<Long, PostLikeCountOutput> postLikeCounts = daoResponses.stream()
                .collect(Collectors.toMap(
                        PostLikeCountDaoResponse::getPostId,
                        daoResponse -> PostLikeCountOutput.builder()
                                .postId(daoResponse.getPostId())
                                .likeCount(daoResponse.getLikeCount())
                                .build()
                ));
        return new PostLikeCountsOutput(postLikeCounts);
    }
}
