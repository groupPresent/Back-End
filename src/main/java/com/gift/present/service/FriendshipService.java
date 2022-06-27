package com.gift.present.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import com.gift.present.dto.friendshipdto.FriendshipDetailDto;
import com.gift.present.dto.fundingdto.FundingResponseDto;
import com.gift.present.dto.userdto.UserDto;
import com.gift.present.model.Funding;
import com.gift.present.model.User;
import com.gift.present.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gift.present.dto.friendshipdto.FriendshipDto;
import com.gift.present.dto.friendshipdto.FriendshipFundingDto;
import com.gift.present.dto.friendshipdto.FriendshipInfoDto;
import com.gift.present.model.Anniversary;
import com.gift.present.model.Friendship;
import com.gift.present.model.Funding;
import com.gift.present.repository.AnniversaryRepository;
import com.gift.present.repository.FriendshipRepository;
import com.gift.present.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
	private final UserService userService;
	private final FundingService fundingService;
    


    // friendship 객체 friendshipDTO로변환
    public FriendshipDto generateFriendshipDto(Friendship friendship) {
        return FriendshipDto.builder()
                .friendId(friendship.getFriendId())
                .photoUrl(userService.getUser(friendship.getFriendId()).getProfileImg())
				.friendName(userService.getUser(friendship.getFriendId()).getUserName())
				.favorites(friendship.getFavorites())
                .build();
    }
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
		UserDto userDto = userService.getCurrentUser();
		User user = new User(userDto.getId(),userDto.getUserName(), userDto.getProfileImg(), userDto.getBirthDay(), userDto.getPassword(), userDto.getAccountNum(),userDto.getGender());
		Friendship friendship = new Friendship(user,friendId);
		friendshipRepository.save(friendship);
	}


	//친구 목록 조회
	public List<FriendshipDto> getFriendships() {
		List<FriendshipDto> friendshipDtoList = new ArrayList<>();
		UserDto userDto = userService.getCurrentUser();
		User user = new User(userDto.getId(),userDto.getUserName(), userDto.getProfileImg(), userDto.getBirthDay(), userDto.getPassword(), userDto.getAccountNum(),userDto.getGender());

        List<Friendship> friendshipList = friendshipRepository.findAllByUser_Id(user.getId());
        for(Friendship friendship : friendshipList) {
            friendshipDtoList.add(generateFriendshipDto(friendship));
        }
        return friendshipDtoList;

	}
	
	

	//친구 검색
	public List<FriendshipDto> searchFriend(String friendName) {
		List<FriendshipDto> friendshipDtoList = new ArrayList<>();
		UserDto userDto = userService.getCurrentUser();
		User user = new User(userDto.getId(),userDto.getUserName(), userDto.getProfileImg(), userDto.getBirthDay(), userDto.getPassword(), userDto.getAccountNum(),userDto.getGender());

		//friendName 과 동일한 이름을 가진 모든 사람 조회
		List<UserDto> userList = userService.searchUserByName(friendName);
		for(UserDto tempUser : userList) {
			List<Friendship> friendshipList = friendshipRepository.findAllByUser_IdAndFriendId(user.getId(),tempUser.getId());
			for(Friendship friendship : friendshipList){
				if(friendship != null){
					friendshipDtoList.add(generateFriendshipDto(friendship));
				}
				break;
			}

		}

        return friendshipDtoList;
	}


	//친구 마이페이지 - 친구정보 조회
	public UserDto getFriendInfo(Long friendId) {
		return userService.getUser(friendId);
	}


	//친구 마이페이지 - 받은 편딩 조회
	public List<FundingResponseDto> searchFriendFundingInfo(Long friendId) {
		return fundingService.getUserFundingList(friendId);
	}



	//친구 즐겨찾기 등록/취소
	public void updateFriendFavorite(Long friendId) {
		UserDto userDto = userService.getCurrentUser();
		User user = new User(userDto.getId(),userDto.getUserName(), userDto.getProfileImg(), userDto.getBirthDay(), userDto.getPassword(), userDto.getAccountNum(),userDto.getGender());
		List<Friendship> friendshipList = friendshipRepository.findAllByUser_IdAndFriendId(user.getId(),friendId);
		for(Friendship friendship : friendshipList){
			friendship.setFavorites(!friendship.getFavorites());
			friendshipRepository.save(friendship);
			break;
		}


		
	}
	
	



	

}
