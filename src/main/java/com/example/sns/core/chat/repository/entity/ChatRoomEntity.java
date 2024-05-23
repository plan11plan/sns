package com.example.sns.core.chat.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "chat_rooms")
@NoArgsConstructor
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId1;
    private Long userId2;

    @Builder
    public ChatRoomEntity(Long id, Long userId1, Long userId2) {
        this.id = id;
        this.userId1 = userId1;
        this.userId2 = userId2;
    }
}

