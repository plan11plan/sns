package com.example.sns.mock.post;

import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.service.port.PostReadRepository;
import java.util.*;
import java.util.stream.Collectors;

public class FakePostReadRepository implements PostReadRepository {
    private final List<Post> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Optional<Post> findById(long id) {
        return data.stream().filter(item -> item.getId().getValue() == id).findAny();
    }

    @Override
    public Optional<List<Post>> findLatestPostsByWriterAndStatus(Long writerId, String status, int limit) {
        List<Post> posts = data.stream()
                .filter(post -> Objects.equals(post.getWriterId().getValue(), writerId) &&
                        Objects.equals(post.getStatus().name(), status))
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .limit(limit)
                .collect(Collectors.toList());
        return posts.isEmpty() ? Optional.empty() : Optional.of(posts);

    }

    @Override
    public Optional<List<Post>> findPostsByWriterAndStatusBeforeId(Long writerId, String status, Long lastId, int limit) {
        List<Post> posts = data.stream()
                .filter(post -> Objects.equals(post.getWriterId().getValue(), writerId) &&
                        Objects.equals(post.getStatus().name(), status) &&
                        post.getId().getValue() < lastId)
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .limit(limit)
                .collect(Collectors.toList());
      return posts.isEmpty() ? Optional.empty() : Optional.of(posts);

    }

    @Override
    public Optional<List<Post>> findAllByInId(List<Long> postIds) {
        List<Post> posts = postIds.stream()
                .map(this::findById)
                .flatMap(Optional::stream)
                .sorted(Comparator.comparing(post -> post.getId().getValue(), Comparator.reverseOrder()))
                .collect(Collectors.toList());
        return posts.isEmpty() ? Optional.empty() : Optional.of(posts);

    }

    public void save(Post post) {
        data.add(post);
    }
}
