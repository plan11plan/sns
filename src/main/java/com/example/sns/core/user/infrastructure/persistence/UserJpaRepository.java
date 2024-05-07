package com.example.sns.core.user.infrastructure.persistence;


import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.infrastructure.persistence.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByIdAndStatus(Long id, UserStatus status);

    Optional<UserEntity> findByEmailAndStatus(String email, UserStatus status);

    Optional<UserEntity> findByEmail(String email);
}
