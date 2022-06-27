package com.gift.present.dto.friendshipdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//친구 추가
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipDetailDto {
    //private String userId;
    private String friendName;
    private String friendPhone;
}
