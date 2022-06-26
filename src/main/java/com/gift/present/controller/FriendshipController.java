package com.gift.present.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gift.present.dto.friendshipdto.FriendshipDto;
import com.gift.present.service.FriendshipService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;
    
    
    //친구추가
    @PostMapping("/user/friend/{friendId}")
    public void insertFriend(@RequestParam String friendId) {
    	 friendshipService.insertFriend(friendId);
    }
    
    //전체 친구목록 조회
    @GetMapping("/user/friend")
    public ResponseEntity<List<FriendshipDto>> searchAllFriends() {
    	return ResponseEntity.ok().body(friendshipService.searchAllFriends());
    }
    

    // 친구검색
    @GetMapping("/user/friend/{friendName}")
    public ResponseEntity<List<FriendshipDto>> searchFriend(@RequestParam String friendName) {
        return ResponseEntity.ok().body(friendshipService.searchFriend(friendName));
    }
    
    // 친구정보 조회 (친구 마이페이지 접속 시)
    @GetMapping("/user/friend/{friendId}/info")
    public ResponseEntity<List<FriendshipDto>> searchFriendInfo(@RequestParam String friendId) {
        return ResponseEntity.ok().body(friendshipService.searchFriendInfo(friendId));
    }

    // 친구 펀딩정보 조회 (친구 마이페이지 접속 시)
    @GetMapping("/user/frined/{friendId}/funding")
    public ResponseEntity<List<FriendshipDto>> searchFriendFundingInfo(@RequestParam String friendId) {
        return ResponseEntity.ok().body(friendshipService.searchFriendFundingInfo(friendId));
    }
    
    // 친구 즐겨찾기 등록/취소
    @PutMapping("/user/friend/{friendId}/favorite")
    public void updateFriendFavorite(@RequestParam String friendId) {
    	friendshipService.updateFriendFavorite(friendId);
    }
    
    
    
}
