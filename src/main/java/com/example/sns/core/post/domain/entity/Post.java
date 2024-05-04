package com.example.sns.core.post.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long memberId;

    private Title title;

    private Contents contents;

    @Builder
    public Post(Long memberId, Title title, Contents contents) {
        this.memberId = memberId;
        this.title = title;
        this.contents = contents;
    }
}
