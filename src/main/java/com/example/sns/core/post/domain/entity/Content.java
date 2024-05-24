package com.example.sns.core.post.domain.entity;


import jakarta.persistence.Lob;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Content {
    @Lob
    String value;

    @Builder
    public Content(String value) {
        this.value = Objects.requireNonNull(value,"내용을 입력해주세요)");
    }
    public static Content of(String value){
        return Content.builder()
                .value(value)
                .build();
    }
    public String getValue(){
        return this.value;
    }
}
