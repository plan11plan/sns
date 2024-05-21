package com.example.sns.core.post.service;

public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(Long id) {
        super("Comment not found with id: " + id);
    }

    public CommentNotFoundException(String message) {
        super(message);
    }
}