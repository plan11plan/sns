package com.example.sns.core.post.service;

import com.example.sns.core.post.domain.entity.Comment;
import com.example.sns.core.post.service.output.CommentOutput;
import com.example.sns.core.post.service.port.CommentReadRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentReadService {
    private final CommentReadRepository commentReadRepository;


    public CommentOutput getById(Long id) {
        Comment comment = commentReadRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
        return CommentOutput.from(comment);
    }

    public List<CommentOutput> getByPostId(Long postId) {
        List<Comment> comments = commentReadRepository.findByPostId(postId);
        return comments.stream().map(CommentOutput::from).toList();
    }

    public List<CommentOutput> getReplies(Long parentId) {
        List<Comment> comments = commentReadRepository.findByParentId(parentId);
        return comments.stream().map(CommentOutput::from).toList();
    }
}