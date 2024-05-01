package com.example.sns.user.domain.repository;

import com.example.sns.user.domain.entity.root.User;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);

}
