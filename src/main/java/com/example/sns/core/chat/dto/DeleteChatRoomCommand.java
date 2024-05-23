package com.example.sns.core.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteChatRoomCommand {
    private Long chatRoomId;
}
