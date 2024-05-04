package com.example.sns.core.post.domain.entity;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Contents {
    @Lob
    String contents;

    public Contents(String contents) {
        this.contents = Objects.requireNonNull(contents,"내용을 입력해주세요)");
    }

    public String getValue(){
        return this.contents;
    }
}
