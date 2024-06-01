package com.example.sns.core.user.infrastructure.persistence;


import com.example.sns.core.user.infrastructure.persistence.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByIdAndStatus(Long id, String status);

    Optional<UserEntity> findByEmailAndStatus(String email, String status);

    Optional<UserEntity> findByEmail(String email);

    Optional<List<UserEntity>> findAllByIdIn(List<Long> ids);

    Optional<List<UserEntity>>  findAllByOnlineStatus(String status);
}
