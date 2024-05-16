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
        public PostCreate(WriterId writerId, Title title, Content content, PostStatus status) {
                this.writerId = writerId;
                this.title = title;
                this.content = content;
                this.status = status;
        }
}