package com.gift.present.dto.friendshipdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipDetailDto {
    private String friendId;
    private String friendName;
    private String photoUrl;
    private String date;
}
