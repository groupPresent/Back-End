package com.gift.present.service;

import com.gift.present.model.User;
import com.gift.present.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 임시유저생성
    public void createUser() {
        User user = new User("beomin", "NOIMG", "6/8", "1234", "123-123-123", "남");
        userRepository.save(user);
    }
}
