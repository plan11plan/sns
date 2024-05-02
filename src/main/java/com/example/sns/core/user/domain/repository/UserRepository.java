package com.example.sns.core.user.domain.repository;

import com.example.sns.core.user.domain.entity.root.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
