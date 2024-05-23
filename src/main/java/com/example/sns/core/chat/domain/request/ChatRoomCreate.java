package com.example.sns.core.chat.domain.request;

import com.example.sns.core.chat.domain.ChatUserId;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoomCreate {
   private final ChatUserId userId1;
   private final ChatUserId userId2;

   @Builder
    public ChatRoomCreate(ChatUserId userId1, ChatUserId userId2) {
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

}
