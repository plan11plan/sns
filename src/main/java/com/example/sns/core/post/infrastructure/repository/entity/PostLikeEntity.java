package com.example.sns.core.post.infrastructure.repository.entity;

import com.example.sns.core.post.domain.entity.PostLike;
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

    @Column(name = "writer_id")
    private Long writerId;

    @Column(name = "post_id")
    private Long postId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public static PostLikeEntity from(PostLike postLike){
        PostLikeEntity postLikeEntity = new PostLikeEntity();
        postLikeEntity.writerId  = postLike.getWriterId();
        postLikeEntity.postId = postLike.getPostId();
        postLikeEntity.createdAt = postLike.getCreatedAt();
        return postLikeEntity;
    }
    public PostLike toModel(){
        return PostLike.builder()
                .id(id)
                .writerId(writerId)
                .postId(postId)
                .createdAt(createdAt)
                .build();
    }
}
