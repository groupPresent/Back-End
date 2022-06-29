package com.gift.present.repository;

import com.gift.present.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByNameIsContaining(String userName);
    Optional<User> findByUserName(String username);
}
