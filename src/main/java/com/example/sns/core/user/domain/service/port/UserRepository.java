package com.example.sns.core.user.domain.service.port;

import com.example.sns.core.user.domain.entity.root.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByIdAndStatus(Long id, String status);
    Optional<User> findByEmailAndStatus(String email, String status);

    User save(User user);


    Optional<List<User>> findAllByIdIn(List<Long> ids);

    Optional<List<User>> findAllByOnlineStatus(String onlineStatus);
}
