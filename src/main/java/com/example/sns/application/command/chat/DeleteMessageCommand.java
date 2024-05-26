package com.example.sns.application.command.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteMessageCommand {
    private Long messageId;
    private Long userId;
}
