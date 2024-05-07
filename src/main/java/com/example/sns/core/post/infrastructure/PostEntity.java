package com.example.sns.core.post.infrastructure;


import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.Post;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_Id")
    private Long writerId;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    public static PostEntity from(Post post) {
        PostEntity postEntity = new PostEntity();
        postEntity.id = post.getId();
        postEntity.writerId = post.getWriterId().getValue();
        postEntity.title = post.getTitle().getValue();
        postEntity.content = post.getContent().getValue();
        return postEntity;
    }

    public Post toModel() {
        return Post.builder()
                .id(id)
                .writerId(new WriterId(writerId))
                .title(new Title(title))
                .content(new Content(content))
                .build();

    }
}
