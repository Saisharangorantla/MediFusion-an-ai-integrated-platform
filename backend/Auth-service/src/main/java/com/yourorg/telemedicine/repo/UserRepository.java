package com.yourorg.telemedicine.repo;

import java.util.Optional;


import org.springframework.data.repository.CrudRepository;

import com.yourorg.telemedicine.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
    boolean existsByUsername(String username);

    // 🔍 used while registering
    boolean existsByEmail(String email);
}

