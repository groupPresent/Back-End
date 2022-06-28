package com.gift.present.service;


import java.util.ArrayList;
import java.util.List;

import com.gift.present.dto.fundingdto.FundingResponseDto;
import com.gift.present.dto.userdto.UserDto;
import com.gift.present.model.User;
import com.gift.present.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.gift.present.dto.friendshipdto.FriendshipDto;
import com.gift.present.model.Friendship;
import com.gift.present.repository.FriendshipRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
	private final UserRepository userRepository;
	private final UserService userService;
	private final FundingService fundingService;

	//User 객체 friendshipInfoDto로 변환
	public UserDto generateFriendUserDto(User friend) {
		return UserDto.builder()
				.id(friend.getId())
				.userName(friend.getUserName())
				.profileImg(friend.getProfileImg())
				.birthDay(friend.getBirthDay())
				.accountNum(friend.getAccountNum())
				.gender(friend.getGender())
				.build();

	}

	//친구 추가
	public void insertFriend(Long friendId) {
		User user = userRepository.findById(friendId).orElseThrow(
				() -> new IllegalArgumentException("해당하는 유저가 없습니다.")
		);

		Friendship friendship = new Friendship(user,friendId);
		friendshipRepository.save(friendship);
	}


	//친구 목록 조회
//	public List<FriendshipDto> getFriendships() {
//		List<FriendshipDto> friendshipDtoList = new ArrayList<>();
//        List<Friendship> friendshipList = friendshipRepository.findAllByUser_Id(1L);
//
//        for(Friendship friendship : friendshipList) {
//            friendshipDtoList.add(generateFriendshipDto(friendship));
//        }
//
//        return friendshipDtoList;
//	}
	
	

	// 친구 검색 - 친구목록
	public List<FriendshipDto> searchFriend(String friendName) {
		List<FriendshipDto> friendshipDtoList = new ArrayList<>();

		List<User> userList = userRepository.findAllByUserName(friendName);

		for(User user : userList) {
			friendshipDtoList.add(generateFriendshipDto(user));
		}
		return friendshipDtoList;
	}

	// 친구 검색 - 친구 추가용
//	public List<>



	//친구 마이페이지 - 친구정보 조회
//	public UserDto getFriendInfo(Long friendId) {
//		return userService.getUser(friendId);
//	}


	//친구 마이페이지 - 받은 편딩 조회
	public List<FundingResponseDto> searchFriendFundingInfo(Long friendId) {
		return fundingService.getUserFundingList(friendId);
	}



	//친구 즐겨찾기 등록/취소
//	public void updateFriendFavorite(Long friendId) {
//		UserDto userDto = userService.getCurrentUser();
//		User user = new User(userDto.getId(),userDto.getUserName(), userDto.getProfileImg(), userDto.getBirthDay(), userDto.getPassword(), userDto.getAccountNum(),userDto.getGender());
//		List<Friendship> friendshipList = friendshipRepository.findAllByUser_IdAndFriendId(user.getId(),friendId);
//		for(Friendship friendship : friendshipList){
//			friendship.setFavorites(!friendship.getFavorites());
//			friendshipRepository.save(friendship);
//			break;
//		}
		
//	}


	// generateFriendshipDto 생성하기 메소드
	private FriendshipDto generateFriendshipDto(User user) {
		return FriendshipDto.builder()
				.friendId(user.getId())
				.friendName(user.getUserName())
				.photoUrl(user.getProfileImg())
				.build();
	}

}
