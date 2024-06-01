package com.example.sns.core.user.domain.service.port;

import com.example.sns.core.user.domain.entity.NicknameHistory;
import java.util.List;
import java.util.Optional;

public interface NicknameHistoryRepository {
    NicknameHistory save(NicknameHistory nicknameHistory);
    Optional<List<NicknameHistory>> findAllByUserId(Long userId);
}
