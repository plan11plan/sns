package com.example.sns.core.chat.usecaseImpl;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarkMessageAsReadCommand {
    private Long messageId;
    private Long userId;
}
