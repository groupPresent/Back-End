package com.gift.present.controller;


import java.util.List;


import com.gift.present.dto.fundingdto.FundingResponseDto;
import com.gift.present.dto.userdto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gift.present.dto.friendshipdto.FriendshipDto;
import com.gift.present.service.FriendshipService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FriendshipController {
    private final FriendshipService friendshipService;

    //친구추가
    @PostMapping("/user/friend/{friendId}")
    public void insertFriend(@PathVariable Long friendId) {
    	 friendshipService.insertFriend(friendId);
    }
    
//    //전체 친구목록 조회
//    @GetMapping("/user/friend")
//    public ResponseEntity<List<FriendshipDto>> getAllFriendship() {
//    	return ResponseEntity.ok().body(friendshipService.getFriendships());
//    }
    

    // 친구검색 - 오류
    @GetMapping("/user/friend/{friendName}")
    public ResponseEntity<List<FriendshipDto>> searchFriend(@PathVariable String friendName) {
        return ResponseEntity.ok().body(friendshipService.searchFriend(friendName));
    }
    
    // 친구정보 조회 (친구 마이페이지 접속 시)
    @GetMapping("/user/friend/{friendId}/info")
    public ResponseEntity<UserDto> getFriendInfo(@PathVariable Long friendId) {
        return ResponseEntity.ok().body(friendshipService.getFriendInfo(friendId));
    }

    // 친구 펀딩정보 조회 (친구 마이페이지 접속 시)
    @GetMapping("/user/frined/{friendId}/funding")
    public ResponseEntity<List<FundingResponseDto>> searchFriendFundingInfo(@PathVariable Long friendId) {
        return ResponseEntity.ok().body(friendshipService.searchFriendFundingInfo(friendId));
    }
    
    // 친구 즐겨찾기 등록/취소
    @PutMapping("/user/friend/{friendId}/favorite")
    public void updateFriendFavorite(@PathVariable Long friendId) {
    	friendshipService.updateFriendFavorite(friendId);
    }
}
