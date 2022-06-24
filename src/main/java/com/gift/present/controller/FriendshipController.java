package com.gift.present.controller;

import com.gift.present.dto.friendshipdto.FriendshipDto;
import com.gift.present.dto.qnAdto.QnADetailDto;
import com.gift.present.dto.qnAdto.QnADto;
import com.gift.present.service.FriendshipService;
import com.gift.present.service.QnAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;

    // 친구검색
    @GetMapping("/user/friend/search")
    public ResponseEntity<List<FriendshipDto>> getAllFriendship(@RequestBody FriendshipDetailDto friendshipDetailDto) {
        //내 정보 = 현재 로그인한 사람의 user_id 와 name

        return ResponseEntity.ok().body(friendshipService.getAllFriendship());
    }

    // 질문 작성 API (운영진용)
    @PostMapping("/makequestion")
    public void createQnA(@RequestBody FriendshipDetailDto friendshipDetailDto) {
        friendshipService.getAllFriendship(friendshipDetailDto)
    }
}
