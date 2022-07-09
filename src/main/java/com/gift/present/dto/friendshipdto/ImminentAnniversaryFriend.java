package com.gift.present.dto.friendshipdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImminentAnniversaryFriend {
    private String friendName;
    private String friendProfile;
    private String anniversaryName;
    private String anniversaryRemains;
}
