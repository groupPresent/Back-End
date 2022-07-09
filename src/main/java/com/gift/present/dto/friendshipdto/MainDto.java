package com.gift.present.dto.friendshipdto;

import com.gift.present.dto.fundingdto.RecommendedGoods;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MainDto {
    List<ImminentAnniversaryFriend> imminentAnniversaryFriendList;
    List<RecommendedGoods> recommendedGoods;
}
