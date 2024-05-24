package com.example.sns.mock.post;

import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.service.port.PostReadRepository;
import java.util.*;
import java.util.stream.Collectors;

public class FakePostReadRepository implements PostReadRepository {
    private final List<Post> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Optional<Post> findById(long id) {
        return data.stream().filter(item -> item.getId().getValue() == id).findAny();
    }

    @Override
    public List<Post> findLatestPostsByWriterAndStatus(Long writerId, String status, int limit) {
        return data.stream()
                .filter(post -> Objects.equals(post.getWriterId().getValue(), writerId) &&
                        Objects.equals(post.getStatus().name(), status))
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findPostsByWriterAndStatusBeforeId(Long writerId, String status, Long lastId, int limit) {
        return data.stream()
                .filter(post -> Objects.equals(post.getWriterId().getValue(), writerId) &&
                        Objects.equals(post.getStatus().name(), status) &&
                        post.getId().getValue() < lastId)
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findAllByInId(List<Long> postIds) {
        return postIds.stream()
                .map(this::findById)
                .flatMap(Optional::stream)
                .sorted(Comparator.comparing(post -> post.getId().getValue(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public void save(Post post) {
        data.add(post);
    }
}
