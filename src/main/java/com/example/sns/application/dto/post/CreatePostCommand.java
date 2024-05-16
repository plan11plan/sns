package com.example.sns.application.dto.post;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreatePostCommand {
    private final WriterId writerId;
    private final Title title;
    private final Content content;


    @Builder
    public CreatePostCommand(WriterId writerId, Title title, Content content) {
        this.writerId = writerId;
        this.title = title;
        this.content = content;
    }
    public Long getWriterId(){
        return writerId.getValue();
    }
    public WriterId getWriter(){
        return writerId;
    }

}
