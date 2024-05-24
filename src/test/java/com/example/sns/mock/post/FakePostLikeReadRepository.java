package com.example.sns.mock.post;

import com.example.sns.core.post.infrastructure.repository.entity.outputVo.PostLikeCountDaoResponse;
import com.example.sns.core.post.service.port.PostLikeReadRepository;
import java.util.*;

public class FakePostLikeReadRepository implements PostLikeReadRepository {
    private final Map<Long, Long> likeCounts = Collections.synchronizedMap(new HashMap<>());

    @Override
    public Long getCount(Long postId) {
        return likeCounts.getOrDefault(postId, 0L);
    }

    @Override
    public PostLikeCountDaoResponse countByPostId(Long postId) {
        return new PostLikeCountDaoResponse(postId, getCount(postId));
    }

    @Override
    public List<PostLikeCountDaoResponse> findLikesByPostIds(List<Long> postIds) {
        List<PostLikeCountDaoResponse> responses = new ArrayList<>();
        for (Long postId : postIds) {
            responses.add(new PostLikeCountDaoResponse(postId, getCount(postId)));
        }
        return responses;
    }

    public void saveLikeCount(Long postId, Long count) {
        likeCounts.put(postId, count);
    }
}
