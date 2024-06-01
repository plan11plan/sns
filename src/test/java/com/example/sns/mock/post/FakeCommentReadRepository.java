package com.example.sns.mock.post;

import com.example.sns.core.post.domain.entity.Comment;
import com.example.sns.core.post.domain.service.port.CommentReadRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FakeCommentReadRepository implements CommentReadRepository {
    private final List<Comment> data = Collections.synchronizedList(new ArrayList<>());

    @Override
    public Optional<Comment> findById(Long id) {
        return data.stream().filter(comment -> comment.getCommentIdValue().equals(id)).findAny();
    }

    @Override
    public Optional<List<Comment>> findByPostId(Long postId) {
        List<Comment> comments = new ArrayList<>();
        for (Comment comment : data) {
            if (comment.getPostIdValue().equals(postId)) {
                comments.add(comment);
            }
        }
        return comments.isEmpty() ? Optional.empty() : Optional.of(comments);
    }

    @Override
    public Optional<List<Comment>> findByParentId(Long parentId) {
        List<Comment> comments = new ArrayList<>();
        for (Comment comment : data) {
            if (comment.getCommentParentIdValue().equals(parentId)) {
                comments.add(comment);
            }
        }
        return comments.isEmpty() ? Optional.empty() : Optional.of(comments);
    }

    public void save(Comment comment) {
        data.add(comment);
    }
}
