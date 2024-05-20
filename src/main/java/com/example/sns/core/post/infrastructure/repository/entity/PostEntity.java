package com.example.sns.core.post.infrastructure.repository.entity;


import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Post;
import com.example.sns.core.post.domain.entity.PostId;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
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
@Table(name = "posts")
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "writer_Id")
    private Long writerId;

    @Column(name ="status")
    private String status;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    private Long likeCount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    public static PostEntity from(Post post) {
        PostEntity postEntity = new PostEntity();
        postEntity.writerId = post.getWriterIdValue();
        postEntity.likeCount = post.getLikeCount();
        postEntity.status = post.getStatusValue();
        postEntity.title = post.getTitleValue();
        postEntity.content = post.getContentValue();
        postEntity.createdAt=post.getCreatedAt();
        postEntity.modifiedAt=post.getModifiedAt();
        return postEntity;
    }

    public Post toModel() {
        return Post.builder()
                .id(new PostId(id))
                .writerId(new WriterId(writerId))
                .title(new Title(title))
                .content(new Content(content))
                .status(PostStatus.valueOf(status))
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .build();

    }
}
