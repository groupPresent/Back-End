package com.gift.present.service;

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

    public List<FriendshipDto> getAllFriendship() {
        List<FriendshipDto> friendshipDtoList = new ArrayList<>();

        List<Friendship> friendshipList = friendshipRepository.findAll();
        for(Friendship friendship : friendshipList) {
            friendshipDtoList.add(generateFriendshipDto(friendship));
        }
        return friendshipDtoList;
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
