package com.example.sns.core.chat.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatMessage {
    private final MessageId id;
    private final ChatRoomId chatRoomId;
    private final ChatUserId senderId;
    private final String content;
    private final LocalDateTime sentAt;
    private final boolean isRead;
    private final MessageStatus status;

    public Long getChatRoomIdValue(){
        return chatRoomId.getValue();
    }
    public Long getSenderIdValue(){
        return senderId.getValue();
    }
    public String getContentValue(){
        return content;
    }
    public String getStatusValue(){
        return status.name();
    }
    @Builder
    public ChatMessage(MessageId id, ChatRoomId chatRoomId, ChatUserId senderId, String content, LocalDateTime sentAt, boolean isRead, MessageStatus status) {
        this.id = id;
        this.chatRoomId = chatRoomId;
        this.senderId = senderId;
        this.content = content;
        this.sentAt = sentAt;
        this.isRead = isRead;
        this.status = status;
    }

    public static ChatMessage create(Long chatRoomId, Long senderId, String content, LocalDateTime sentAt) {
        return ChatMessage.builder()
                .chatRoomId(new ChatRoomId(chatRoomId))
                .senderId(new ChatUserId(senderId))
                .content(content)
                .sentAt(sentAt)
                .isRead(false)
                .status(MessageStatus.SENT)
                .build();
    }

    public ChatMessage markAsRead() {
        return ChatMessage.builder()
                .id(id)
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .content(content)
                .sentAt(sentAt)
                .isRead(true)
                .status(status)
                .build();
    }

    public ChatMessage updateContent(String newContent) {
        return ChatMessage.builder()
                .id(id)
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .content(newContent)
                .sentAt(sentAt)
                .isRead(isRead)
                .status(status)
                .build();
    }

    public ChatMessage delete() {
        return ChatMessage.builder()
                .id(id)
                .chatRoomId(chatRoomId)
                .senderId(senderId)
                .content(content)
                .sentAt(sentAt)
                .isRead(isRead)
                .status(MessageStatus.DELETED)
                .build();
    }

    public Long getChatMessageIdValue() {
        return this.id != null ? this.id.getValue() : null;
    }
}
