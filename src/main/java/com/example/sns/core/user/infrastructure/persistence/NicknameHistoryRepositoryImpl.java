package com.example.sns.core.user.infrastructure.persistence;

import com.example.sns.core.user.domain.entity.NicknameHistory;
import com.example.sns.core.user.infrastructure.persistence.entity.NicknameHistoryEntity;
import com.example.sns.core.user.service.port.NicknameHistoryRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NicknameHistoryRepositoryImpl implements NicknameHistoryRepository {
    private final NicknameHistoryJpaRepository nicknameHistoryJpaRepository;

    public NicknameHistory save(NicknameHistory nicknameHistory) {
        return nicknameHistoryJpaRepository.save(NicknameHistoryEntity.from(nicknameHistory)).toModel();
    }

    public Optional<List<NicknameHistory>> findAllByUserId(Long userId) {
        List<NicknameHistoryEntity> nicknameHistoryEntities = nicknameHistoryJpaRepository.findAllByUserId(userId)
                .orElse(Collections.emptyList());
        return Optional.of(
                nicknameHistoryEntities.stream().map(NicknameHistoryEntity::toModel).collect(Collectors.toList()));
    }
}
