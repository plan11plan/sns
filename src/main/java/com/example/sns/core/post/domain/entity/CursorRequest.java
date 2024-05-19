package com.example.sns.core.post.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CursorRequest {
     // 전부 스크롤 했을떄. 없다 라는 것을 표현하기 위해.
    public static final Long NONE_KEY = -1L;

    private final int size;
    private final Long key;

    @Builder
    public CursorRequest(
            @JsonProperty("size") int size,
            @JsonProperty("key") Long key) {
        this.size = size;
        this.key = key;
    }

    // 클라이언트가 처음 요청할 때는 키가 없는게 편하니까.null
    public Boolean hasKey(){
        return key != null && !key.equals(NONE_KEY);
    }
    public CursorRequest next(Long key){
        return new CursorRequest(size,key);
    }


    // 아무 포스트도 없을시, NONE KEY
    public  Long getNextKey(List<Post> posts) {
        return posts.stream()
                .mapToLong(Post::getPostIdValue)
                .min()
                .orElse(CursorRequest.NONE_KEY);
    }

}
