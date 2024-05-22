package com.example.sns.core.chat;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Message {
    private final MessageId id;
    private final ChatRoomId chatRoomId;
    private final ChatUserId senderId;
    private final String content;
    private final LocalDateTime sentAt;
    private final boolean isRead;

    @Builder
    public Message(MessageId id, ChatRoomId chatRoomId, ChatUserId senderId, String content, LocalDateTime sentAt, boolean isRead) {
        this.id = id;
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.content = content;
        this.sentAt = sentAt;
        this.isRead = isRead;
    }

    public static Message create(Long chatRoomId, Long senderId, String content, LocalDateTime sentAt) {
        return Message.builder()
                .chatRoomId(new ChatRoomId(chatRoomId))
                .senderId(new ChatUserId(senderId))
                .content(content)
                .sentAt(sentAt)
                .isRead(false)
                .build();
    }
    public Message updateContent(String newContent) {
        return Message.builder()
                .id(id)
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .content(newContent)
                .sentAt(sentAt)
                .isRead(isRead)
                .build();
    }
    public Message markAsRead() {
        return Message.builder()
                .id(id)
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .content(content)
                .sentAt(sentAt)
                .isRead(true)
                .build();
    }
    public Long getMessageIdValue(){
        return this.id != null ? this.id.getValue() : null;

    }
}