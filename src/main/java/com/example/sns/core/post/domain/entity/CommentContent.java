package com.example.sns.core.post.domain.entity;

import jakarta.persistence.Lob;
import java.util.Objects;
import lombok.Getter;

@Getter
public class CommentContent {
    @Lob
    String value;

    public CommentContent(String value) {
        this.value = Objects.requireNonNull(value,"내용을 입력해주세요)");
    }

    public String getValue(){
        return this.value;
    }
}
