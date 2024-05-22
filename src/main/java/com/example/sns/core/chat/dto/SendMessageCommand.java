package com.example.sns.core.chat.dto;

public class SendMessageCommand {
    private Long chatRoomId; // Change type to Long
    private Long senderId; // Change type to Long
    private String content;

    // Getters and setters

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
