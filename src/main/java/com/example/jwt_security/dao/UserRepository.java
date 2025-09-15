package com.example.jwt_security.dao;

import com.example.jwt_security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
  Optional<User> findByUsername(String username);
}