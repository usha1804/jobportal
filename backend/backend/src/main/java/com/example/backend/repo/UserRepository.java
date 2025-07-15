package com.example.backend.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.backend.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}


