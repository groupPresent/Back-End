package com.gift.present.dto.friendshipdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//친구
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipDto {
    private Long friendId;
    private String friendName;
    private String photoUrl;
}
