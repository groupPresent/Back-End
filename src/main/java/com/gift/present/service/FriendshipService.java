package com.gift.present.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gift.present.dto.friendshipdto.FriendDto;
import com.gift.present.dto.friendshipdto.FriendSearchDto;
import com.gift.present.dto.fundingdto.FundingResponseDto;
import com.gift.present.dto.userdto.UserDto;
import com.gift.present.model.Funding;
import com.gift.present.model.Fundraising;
import com.gift.present.model.User;
import com.gift.present.repository.FundingRepository;
import com.gift.present.repository.FundraisingRepository;
import com.gift.present.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.gift.present.dto.friendshipdto.FriendshipDto;
import com.gift.present.model.Friendship;
import com.gift.present.repository.FriendshipRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
	private final UserRepository userRepository;
	private final FundingRepository fundingRepository;
	private final FundraisingRepository fundraisingRepository;




	//친구 추가
	@Transactional
	public void insertFriend(Long friendId, User user) {
		User findUser = userRepository.findById(user.getId()).orElseThrow(
				() -> new IllegalArgumentException("해당하는 유저가 없습니다.")
		);

		Friendship friendship = new Friendship(findUser,friendId);
		friendshipRepository.save(friendship);
	}


	//친구 목록 조회
	public List<FriendDto> getFriendships(User user) {
		List<FriendDto> friendDtoList = new ArrayList<>();
        List<Friendship> friendshipList = friendshipRepository.findAllByUser_Id(user.getId());

        for(Friendship friendship : friendshipList) {
			friendDtoList.add(generateFriendDto(friendship));
        }

        return friendDtoList;
	}
	
	

	// 친구 검색 - 친구목록
	public List<FriendDto> searchFriend(FriendSearchDto friendSearchDto, User user) {
		List<FriendDto> friendDtoList = new ArrayList<>();

		List<User> friendList = userRepository.findAllByNameIsContaining(friendSearchDto.getFriendName());

		for(User friend : friendList) {
			Optional<Friendship> friendship = friendshipRepository.findByUser_IdAndFriendId(user.getId(), friend.getId());
			if(friendship.isPresent()) {
				friendDtoList.add(generateFriendDto(friendship.get()));
			}

		}

		return friendDtoList;
	}

	// 친구 검색 - 친구 추가용
	public List<FriendshipDto> searchNewFriend(FriendSearchDto friendSearchDto) {
		List<FriendshipDto> friendshipDtoList = new ArrayList<>();

		List<User> userList = userRepository.findAllByNameIsContaining(friendSearchDto.getFriendName());

		for(User user : userList) {
			friendshipDtoList.add(generateFriendshipDto(user));
		}
		return friendshipDtoList;
	}



	//친구 마이페이지 - 친구정보 조회
	public UserDto getFriendInfo(Long friendId, User user) {
		Friendship friendship = friendshipRepository.findByUser_IdAndFriendId(user.getId(), friendId).orElseThrow(
				() -> new IllegalArgumentException("해당 유저는 친구가 아닙니다.")
		);
		User friend = userRepository.findById(friendship.getFriendId()).orElseThrow(
				() -> new IllegalArgumentException("해당하는 유저가 없습니다.")
		);
		return generateFriendUserDto(friend);
	}


	//친구 마이페이지 - 받은 편딩 조회
	public List<FundingResponseDto> searchFriendFundingInfo(Long friendId, User user) {
		friendshipRepository.findByUser_IdAndFriendId(user.getId(), friendId).orElseThrow(
				() -> new IllegalArgumentException("해당 유저와 친구관계가 아닙니다.")
		);
		List<FundingResponseDto> userFundingDtoList = new ArrayList<>();
		List<Funding> userFundingList = fundingRepository.findAllByUserId(friendId);
		for (Funding userFunding : userFundingList) {
			List<Fundraising> fundraisingList = fundraisingRepository.findAllByFunding_Id(userFunding.getId());
			int giftFundingPrice = 0;
			for (Fundraising fundraising : fundraisingList) {
				giftFundingPrice += fundraising.getMoney();
			}
			userFundingDtoList.add(generateFundingResponseDto(userFunding, giftFundingPrice));
		}
		return userFundingDtoList;
	}



	//친구 즐겨찾기 등록/취소
	@Transactional
	public void updateFriendFavorite(Long friendId, User user) {
		Long userId = user.getId();
		userRepository.findById(userId).orElseThrow(
				() -> new IllegalArgumentException("해당하는 유저가 존재하지 않습니다.")
		);
	    Friendship friendship = friendshipRepository.findByUser_IdAndFriendId(userId,friendId).orElseThrow(
				() -> new IllegalArgumentException("해당하는 유저가 존재하지 않습니다.")
		);
		friendship.setFavorites(!friendship.getFavorites());
	}

	// generateFriendDto 생성하기 메소드
	private FriendDto generateFriendDto(Friendship friendship) {
		User user = userRepository.findById(friendship.getFriendId()).orElseThrow(
				() -> new IllegalArgumentException("해당하는 유저가 없습니다")
		);
		return FriendDto.builder()
				.friendId(friendship.getFriendId())
				.friendName(user.getName())
				.photoUrl(user.getProfileImg())
				.build();
	}

	// generateFriendshipDto 생성하기 메소드
	private FriendshipDto generateFriendshipDto(User user) {
		return FriendshipDto.builder()
				.friendId(user.getId())
				.friendName(user.getName())
				.photoUrl(user.getProfileImg())
				.build();
	}

	public FundingResponseDto generateFundingResponseDto(Funding funding, int giftFundingPrice) {
		return FundingResponseDto.builder()
				.giftPhoto(funding.getGiftPhoto())
				.giftName(funding.getGiftName())
				.giftPrice(funding.getGiftPrice())
				.giftFundingRate(giftFundingPrice / funding.getGiftPrice() * 100 + "%")
				.giftFundingPrice(giftFundingPrice)
				.anniversaryRemains("D-5")
				.build();
	}

	//User 객체 friendshipInfoDto로 변환
	public UserDto generateFriendUserDto(User friend) {
		return UserDto.builder()
				.id(friend.getId())
				.userName(friend.getName())
				.profileImg(friend.getProfileImg())
				.birthDay(friend.getBirthDay())
				.accountNum(friend.getAccountNum())
				.gender(friend.getGender())
				.build();
	}
}
