package com.example.sns.core.post.domain.service;

import com.example.sns.common.service.port.TimeHolder;
import com.example.sns.core.post.domain.entity.Comment;
import com.example.sns.core.post.domain.entity.CommentContent;
import com.example.sns.core.post.domain.entity.CommentId;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.WriterId;
import com.example.sns.core.post.domain.entity.request.CommentCreate;
import com.example.sns.core.post.domain.entity.request.CommentUpdate;
import com.example.sns.core.post.domain.service.output.CommentOutput;
import com.example.sns.core.post.domain.service.port.CommentReadRepository;
import com.example.sns.core.post.exception.CommentNotFoundException;
import com.example.sns.core.post.domain.service.input.CommentCreateInput;
import com.example.sns.core.post.domain.service.input.CommentDeleteInput;
import com.example.sns.core.post.domain.service.input.CommentUpdateInput;
import com.example.sns.core.post.domain.service.port.CommentWriteRepository;
import com.example.sns.core.user.domain.service.output.UserOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentWriteService {
    private final CommentWriteRepository commentWriteRepository;
    private final CommentReadRepository commentReadRepository;
    private final TimeHolder timeHolder;

    public CommentOutput create(CommentCreateInput input) {
        CommentCreate commentCreate = CommentCreate.builder()
                .postId(PostId.of(input.getPostId()))
                .writerId(WriterId.of(input.getWriterId()))
                .parentId(CommentId.of(input.getParentId()))
                .content(CommentContent.of(input.getContent()))
                .build();
        Comment comment = Comment.from(commentCreate, timeHolder.nowDateTime());
        Comment savedComment = commentWriteRepository.save(comment);
        return CommentOutput.from(savedComment);
    }
    @Transactional
    public CommentOutput update(CommentUpdateInput input, UserOutput userOutput) {
        Comment comment = commentReadRepository.findById(userOutput.getId())
                .orElseThrow(CommentNotFoundException::new);
        CommentUpdate commentUpdate = CommentUpdate.builder()
                .content(input.getContent())
                .build();
        comment = comment.update(commentUpdate, timeHolder.nowDateTime());

        Comment savedComment = commentWriteRepository.save(comment);
        return CommentOutput.from(savedComment);
    }

    public void delete(CommentDeleteInput input) {
        Comment comment = commentReadRepository.findById(input.getId())
                .orElseThrow(CommentNotFoundException::new);
        commentWriteRepository.delete(comment);
    }
}
