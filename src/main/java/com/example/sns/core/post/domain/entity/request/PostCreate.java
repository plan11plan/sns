package com.example.sns.core.post.domain.entity.request;

import com.example.sns.core.post.domain.entity.Content;
import com.example.sns.core.post.domain.entity.PostStatus;
import com.example.sns.core.post.domain.entity.Title;
import com.example.sns.core.post.domain.entity.WriterId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreate {
        private final WriterId writerId;

        private final Title title;
        private final Content content;
        private final PostStatus status;

        @Builder
        public PostCreate(Long writerId, String title, String content, String status) {
                this.writerId = new WriterId(writerId);
                this.title = new Title(title);
                this.content = new Content(content);
                this.status = PostStatus.valueOf(status);
        }
}