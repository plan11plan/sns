package com.example.sns.core.post.service;

import com.example.sns.core.post.infrastructure.repository.entity.outputVo.PostLikeCountDaoResponse;
import com.example.sns.core.post.infrastructure.repository.queryDsl.PostLikeQueryDslRepository;
import com.example.sns.core.post.service.output.PostLikeCountOutput;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostLikeReadService {
    private final PostLikeQueryDslRepository postLikeQueryDslRepository;

    public PostLikeCountOutput getPostLike(Long postId) {
        PostLikeCountDaoResponse daoResponse = postLikeQueryDslRepository.countByPostId(postId);
        return new  PostLikeCountOutput(daoResponse.getPostId(), daoResponse.getLikeCount());
    }

    public List<PostLikeCountOutput> getPostLikes(List<Long> postIds) {
        List<PostLikeCountDaoResponse> daoResponses = postLikeQueryDslRepository.findLikesByPostIds(postIds);
        return daoResponses.stream()
                .map(daoResponse -> new  PostLikeCountOutput(daoResponse.getPostId(), daoResponse.getLikeCount()))
                .collect(Collectors.toList());
    }
}
