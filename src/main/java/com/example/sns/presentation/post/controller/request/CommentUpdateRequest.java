package com.example.sns.presentation.post.controller.request;


import com.example.sns.core.post.domain.entity.CommentContent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpdateRequest {
    private  String content; // final이 없어짐.

    /*
    Builder 패턴 사용 제거:
    Jackson은 Lombok의 @Builder를 사용한 객체 생성을 기본적으로 지원하지 않기 때문에,
     이를 제거하거나 @AllArgsConstructor와 @NoArgsConstructor를 사용해 기본 생성자를 추가해야 합니다.
     */

    public CommentContent toContent() {
        return new CommentContent(content);
    }
}
