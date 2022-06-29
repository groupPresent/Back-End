package com.gift.present.controller;


import java.util.List;


import com.gift.present.dto.friendshipdto.FriendDto;
import com.gift.present.dto.friendshipdto.FriendSearchDto;
import com.gift.present.dto.fundingdto.FundingResponseDto;
import com.gift.present.dto.userdto.UserDto;
import com.gift.present.model.User;
import com.gift.present.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public void insertFriend(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long friendId) {
        User user = userDetails.getUser();
        friendshipService.insertFriend(friendId, user);
    }
    
    //전체 친구목록 조회
    @GetMapping("/user/friend")
    public ResponseEntity<List<FriendDto>> getAllFriendship(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
    	return ResponseEntity.ok().body(friendshipService.getFriendships(user));
    }


    // 친구 검색 - 친구목록
    @GetMapping("/user/search/friend")
    public ResponseEntity<List<FriendDto>> searchFriend(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody FriendSearchDto friendSearchDto) {
        User user = userDetails.getUser();
        return ResponseEntity.ok().body(friendshipService.searchFriend(friendSearchDto, user));
    }


    // 친구 검색 - 친구 추가용
    @GetMapping("/user/search/friendship")
    public ResponseEntity<List<FriendshipDto>> searchNewFriend(@RequestBody FriendSearchDto friendSearchDto) {
        return ResponseEntity.ok().body(friendshipService.searchNewFriend(friendSearchDto));
    }
    
    // 친구정보 조회 (친구 마이페이지 접속 시)
    @GetMapping("/user/friend/{friendId}/info")
    public ResponseEntity<UserDto> getFriendInfo(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long friendId) {
        User user = userDetails.getUser();
        return ResponseEntity.ok().body(friendshipService.getFriendInfo(friendId, user));
    }

    // 친구 펀딩정보 조회 (친구 마이페이지 접속 시)
    @GetMapping("/user/friend/{friendId}/funding")
    public ResponseEntity<List<FundingResponseDto>> searchFriendFundingInfo(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long friendId) {
        User user = userDetails.getUser();
        return ResponseEntity.ok().body(friendshipService.searchFriendFundingInfo(friendId, user));
    }
    
    // 친구 즐겨찾기 등록/취소
    @PutMapping("/user/friend/{friendId}/favorite")
    public void updateFriendFavorite(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long friendId) {
        User user = userDetails.getUser();
    	friendshipService.updateFriendFavorite(friendId, user);
    }
}
