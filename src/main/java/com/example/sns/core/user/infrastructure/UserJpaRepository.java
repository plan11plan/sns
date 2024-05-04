package com.example.sns.core.user.infrastructure;


import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.entity.root.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User,Long> {

    Optional<User> findByIdAndUserStatus(Long id, UserStatus userStatus);

}
