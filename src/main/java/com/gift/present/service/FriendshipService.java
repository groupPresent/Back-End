package com.gift.present.service;

import java.util.ArrayList;
import java.util.List;

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
    private final AnniversaryRepository anniversaryRepository;
    private final UserRepository userRepository;

    @Transactional
	public void insertFriend(Long friendId) {
    	
        //Friendship friendship= new Friendship(1L, friendId, false);
        //friendshipRepository.save(friendship);
		
	}


	public List<FriendshipDto> searchAllFriends() {
		List<FriendshipDto> friendshipDtoList = new ArrayList<>();

        List<Friendship> friendshipList = friendshipRepository.findAll();
        for(Friendship friendship : friendshipList) {
            friendshipDtoList.add(generateFriendshipDto(friendship));
        }
        return friendshipDtoList;

	}
	
	


	public List<FriendshipDto> searchFriend(String friendName) {
		List<FriendshipDto> friendshipDtoList = new ArrayList<>();

        List<Friendship> friendshipList = friendshipRepository.findAll();
        for(Friendship friendship : friendshipList) {
            friendshipDtoList.add(generateFriendshipDto(friendship));
        }
        return friendshipDtoList;
	}


	public List<FriendshipInfoDto> searchFriendInfo(Long friendId) {
		return null;
	}


	public List<FriendshipFundingDto> searchFriendFundingInfo(Long friendId) {
		// TODO Auto-generated method stub
		return null;
	}


	public void updateFriendFavorite(Long friendId) {
		// TODO Auto-generated method stub
		
	}
	
	
	// QnA의 Dto 변환 과정
    public FriendshipDto generateFriendshipDto(Friendship friendship) {
        return FriendshipDto.builder()
                .friendId("1")
                .friendName("beomin")
                .photoUrl("www~")
                .build();
    }


	

}
