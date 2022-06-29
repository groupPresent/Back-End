package com.gift.present.controller;

import com.gift.present.dto.userdto.UserInfoResponseDto;
import com.gift.present.dto.userdto.UserLoginRequestDto;
import com.gift.present.dto.userdto.UserRequestDto;
import com.gift.present.model.User;
import com.gift.present.security.UserDetailsImpl;
import com.gift.present.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 임시 회원가입
    @PostMapping("/user/signup")
    public void createUser(@RequestPart UserRequestDto userRequestDto, @RequestPart MultipartFile profileImg) {
        userService.createUser(userRequestDto, profileImg);
    }

    // 마이페이지 - 내정보조회
    @GetMapping("/user/info")
    public ResponseEntity<UserInfoResponseDto> getMyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok().body(userService.getMyInfo(user));
    }

    // 마이페이지 - 탈퇴하기
    @DeleteMapping("/user/delete")
    public void deleteUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        userService.deleteUser(user);
    }
}
