package com.example.sns.application.command.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarkMessageAsReadCommand {
    private Long messageId;
    private Long userId;
}
