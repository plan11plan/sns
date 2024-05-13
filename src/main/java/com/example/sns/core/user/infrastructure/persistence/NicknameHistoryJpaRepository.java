package com.example.sns.core.user.infrastructure.persistence;

import com.example.sns.core.user.infrastructure.persistence.entity.NicknameHistoryEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NicknameHistoryJpaRepository extends JpaRepository<NicknameHistoryEntity,Long> {
    Optional<List<NicknameHistoryEntity>> findAllByUserId(Long userId);
}
