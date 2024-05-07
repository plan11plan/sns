package com.example.sns.core.post.domain.entity;


import jakarta.persistence.Lob;
import java.util.Objects;
import lombok.Getter;

@Getter
public class Content {
    @Lob
    String contents;

    public Content(String contents) {
        this.contents = Objects.requireNonNull(contents,"내용을 입력해주세요)");
    }

    public String getValue(){
        return this.contents;
    }
}
