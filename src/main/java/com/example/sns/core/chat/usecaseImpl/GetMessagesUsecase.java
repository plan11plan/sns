package com.example.sns.core.chat.usecaseImpl;

import com.example.sns.core.chat.dto.MessageResponse;
import com.example.sns.core.chat.service.MessageReadService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMessagesUsecase {
    private final MessageReadService messageReadService;

    public List<MessageResponse> execute(Long  chatRoomId) {
        var outputs = messageReadService.getMessages(chatRoomId);
        return outputs.stream().map(output -> new MessageResponse(output.getId(), output.getChatRoomId(), output.getSenderId(), output.getContent(), output.getSentAt(), output.isRead())).collect(
                Collectors.toList());
    }
}