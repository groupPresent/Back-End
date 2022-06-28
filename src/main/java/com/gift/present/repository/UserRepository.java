package com.gift.present.repository;

import com.gift.present.dto.userdto.UserDto;
import com.gift.present.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    List<UserDto> findAllByUserName(String userName);
}
