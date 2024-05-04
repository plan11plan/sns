package com.example.sns.core.user.service.port;

import com.example.sns.core.user.domain.entity.UserStatus;
import com.example.sns.core.user.domain.entity.root.User;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByIdAndStatus(long id, UserStatus userStatus);
    Optional<User> findByEmailAndStatus(String email, UserStatus userStatus);
    User save(User user);

}
