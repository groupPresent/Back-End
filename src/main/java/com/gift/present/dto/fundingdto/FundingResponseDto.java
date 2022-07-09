package com.gift.present.dto.fundingdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FundingResponseDto {
    private Long fundingId;
    private String giftPhoto;
    private String giftName;
    private int giftPrice;
    private String giftFundingRate;
    private int giftFundingPrice;
    private String anniversaryRemains;
}
