package com.example.sns.core.chat.service.port;

import com.example.sns.core.chat.domain.ChatRoom;

public interface ChatRoomWriteRepository {
    ChatRoom save(ChatRoom chatRoom);
    void deleteById(Long chatRoomId);

}
