package com.example.sns.core.chat.service.output;

import com.example.sns.core.chat.domain.ChatMessage;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class ChatMessagesOutput {
    private final List<ChatMessageOutput> chatMessages;

    public ChatMessagesOutput(List<ChatMessageOutput> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public static ChatMessagesOutput from(List<ChatMessage> chatMessages) {
        return new ChatMessagesOutput(chatMessages.stream()
                .map(ChatMessageOutput::from)
                .collect(Collectors.toList()));
    }

    public List<ChatMessageOutput> getChatMessages() {
        return chatMessages;
    }
}
