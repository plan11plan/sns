package com.example.sns.core.post.service;

import com.example.sns.core.common.exception.ResourceNotFoundException;
import com.example.sns.core.common.service.port.TimeHolder;
import com.example.sns.core.post.domain.entity.Comment;
import com.example.sns.core.post.domain.entity.request.CommentCreate;
import com.example.sns.core.post.domain.entity.request.CommentUpdate;
import com.example.sns.core.post.service.output.CommentOutput;
import com.example.sns.core.post.service.port.CommentReadRepository;
import com.example.sns.core.post.service.port.CommentWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentWriteService {
    private final CommentWriteRepository commentWriteRepository;
    private final CommentReadRepository commentReadRepository;
    private final TimeHolder timeHolder;

    public CommentOutput create(CommentCreate input) {
        Comment comment = Comment.from(input, timeHolder.nowDateTime());
        Comment savedComment = commentWriteRepository.save(comment);
        return CommentOutput.from(savedComment);
    }
    @Transactional
    public CommentOutput update(Long id, CommentUpdate input) {
        Comment comment = commentReadRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment", id));
        comment = comment.update(input, timeHolder.nowDateTime());

        Comment savedComment = commentWriteRepository.save(comment);
        return CommentOutput.from(savedComment);
    }

    public void delete(Long id) {
        Comment comment = commentReadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentWriteRepository.delete(comment);
    }
}
