package com.gift.present.dto.friendshipdto;

import com.gift.present.dto.fundingdto.FundingResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipFundingDto {
	private String giftPhoto;
	private String giftName;
	private int giftPrice;
	private String giftFundingRate;
	private int giftFundingPrice;
}
