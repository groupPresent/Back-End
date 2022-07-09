package com.gift.present.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gift.present.dto.friendshipdto.*;
import com.gift.present.dto.fundingdto.FundingResponseDto;
import com.gift.present.dto.fundingdto.RecommendedGoods;
import com.gift.present.dto.userdto.UserDto;
import com.gift.present.model.*;
import com.gift.present.repository.*;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
	private final UserRepository userRepository;
	private final FundingRepository fundingRepository;
	private final FundraisingRepository fundraisingRepository;
	private final AnniversaryRepository anniversaryRepository;



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

	// 메인페이지 조회
	public MainDto getMain(User user) {
		List<Friendship> friendshipList = friendshipRepository.findAllByUser_Id(user.getId());
		List<ImminentAnniversaryFriend> imminentAnniversaryFriendList = new ArrayList<>();
		List<RecommendedGoods> recommendedGoodsList = new ArrayList<>();
		for(Friendship friendship : friendshipList) {
			List<Anniversary> anniversaryList = anniversaryRepository.findAllByUser_Id(friendship.getFriendId());
			for(Anniversary anniversary : anniversaryList) {
				String[] anniversaryYearMonthDay = anniversary.getAnniversaryDate().split("/");
				int anniversaryYear = Integer.parseInt(anniversaryYearMonthDay[0]);
				int anniversaryMonth = Integer.parseInt(anniversaryYearMonthDay[1]);
				int anniversaryDay = Integer.parseInt(anniversaryYearMonthDay[2]);
				if(LocalDate.now().isAfter(LocalDate.of(anniversaryYear, anniversaryMonth, anniversaryDay)) && LocalDate.now().plusDays(10).isBefore(LocalDate.of(anniversaryYear, anniversaryMonth, anniversaryDay))){
					imminentAnniversaryFriendList.add(generateImminentAnniversaryFriend(friendship, anniversary));
				}
			}
		}

		// 추천 알고리즘 로적 추가하면 됨

		return generateMainDto(imminentAnniversaryFriendList, recommendedGoodsList);
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
		int anniversaryDate = Integer.parseInt(funding.getAnniversary().getAnniversaryDate().split("/")[2]);
		int nowDate = LocalDate.now().getDayOfMonth();
		int anniversaryRemainDate = anniversaryDate - nowDate;
		return FundingResponseDto.builder()
				.fundingId(funding.getId())
				.giftPhoto(funding.getGiftPhoto())
				.giftName(funding.getGiftName())
				.giftPrice(funding.getGiftPrice())
				.giftFundingRate(giftFundingPrice / funding.getGiftPrice() * 100 + "%")
				.giftFundingPrice(giftFundingPrice)
				.anniversaryRemains("D"+anniversaryRemainDate)
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

	//

	// ImminentAnniversaryFriendList 생성 메소드
	public ImminentAnniversaryFriend generateImminentAnniversaryFriend(Friendship friendship, Anniversary anniversary) {
		User friend = userRepository.findById(friendship.getFriendId()).orElseThrow(
				() -> new IllegalArgumentException("해당하는 유저가 존재하지 않습니다.")
		);
		int anniversaryDate = Integer.parseInt(anniversary.getAnniversaryDate().split("/")[2]);
		int nowDate = LocalDate.now().getDayOfMonth();
		int anniversaryRemainDate = anniversaryDate - nowDate;
		return ImminentAnniversaryFriend.builder()
				.friendName(friend.getName())
				.friendProfile(friend.getProfileImg())
				.anniversaryName(anniversary.getAnniversaryName())
				.anniversaryRemains("D"+anniversaryRemainDate)
				.build();
	}

	// RecommendedGoods 생성 메소드
	public RecommendedGoods generateRecommendedGoods(Goods goods) {
		return RecommendedGoods.builder()
				.goodsName(goods.getGoodsName())
				.goodsPhoto(goods.getGoodsPhoto())
				.build();
	}

	// MainDto 생성메소드
	public MainDto generateMainDto(List<ImminentAnniversaryFriend> imminentAnniversaryFriendList, List<RecommendedGoods> recommendedGoodsList) {
		return MainDto.builder()
				.imminentAnniversaryFriendList(imminentAnniversaryFriendList)
				.recommendedGoods(recommendedGoodsList)
				.build();
	}

}
