package com.gift.present.controller;

import com.gift.present.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 임시유저 생성 API
    @PostMapping("/user")
    public void createUser() {
        userService.createUser();
    }




    @GetMapping("/user/{userId}/received")
    public void getProfile(@PathVariable Long userId) {
    }
}
