package com.gift.present.controller;

import com.gift.present.dto.userdto.UserInfoResponseDto;
import com.gift.present.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 임시유저 생성 API
    @PostMapping("/user")
    public void createUser() {
        userService.createUser();
    }

    // 마이페이지 - 내정보조회
    @GetMapping("/user/info")
    public ResponseEntity<UserInfoResponseDto> getMyInfo() {
        // User
        return ResponseEntity.ok().body(userService.getMyInfo());
    }

    // 마이페이지 - 탈퇴하기
    @DeleteMapping("/user/delete")
    public void deleteUser() {
        // User
        userService.deleteUser();
    }
}
