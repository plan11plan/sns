package com.example.sns.application.command.comment;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentUpdateCommand {
    private String content;
}