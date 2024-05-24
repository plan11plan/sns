package com.example.sns.core.post.infrastructure.repository.entity;

import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.PostLike;
import com.example.sns.core.post.domain.entity.PostLikeId;
import com.example.sns.core.post.domain.entity.UserId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "postlikes")
public class PostLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public static PostLikeEntity from(PostLike postLike){
        PostLikeEntity postLikeEntity = new PostLikeEntity();
        postLikeEntity.userId  = postLike.getUserIdValue();
        postLikeEntity.postId = postLike.getPostIdValue();
        postLikeEntity.createdAt = postLike.getCreatedAt();
        return postLikeEntity;
    }
    public PostLike toModel(){
        return PostLike.builder()
                .id(PostLikeId.of(id))
                .userId(UserId.of(userId))
                .postId(PostId.of(postId))
                .createdAt(createdAt)
                .build();
    }
}
