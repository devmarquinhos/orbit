package com.marquinhos.dev.orbit.repository;

import com.marquinhos.dev.orbit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
