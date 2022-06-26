package com.gift.present.service;

import com.gift.present.dto.friendshipdto.FriendshipDetailDto;
import com.gift.present.dto.friendshipdto.FriendshipDto;
import com.gift.present.dto.qnAdto.QnADto;
import com.gift.present.model.Friendship;
import com.gift.present.model.QnA;
import com.gift.present.repository.FriendshipRepository;
import com.gift.present.repository.QnARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;

    


    // QnA의 Dto 변환 과정
    public FriendshipDto generateFriendshipDto(Friendship friendship) {
        return FriendshipDto.builder()
                .friendId("1")
                .friendName("beomin")
                .photoUrl("www~")
                .build();
    }


	public void insertFriend(String friendId) {
		// TODO Auto-generated method stub
		
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


	public List<FriendshipDto> searchFriendInfo(String friendId) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<FriendshipDto> searchFriendFundingInfo(String friendId) {
		// TODO Auto-generated method stub
		return null;
	}


	public void updateFriendFavorite(String friendId) {
		// TODO Auto-generated method stub
		
	}


	

}
