package com.gift.present.dto.fundraisingdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FundraisingResponseDto {
    private String giftPhoto;
    private String giftName;
    private int giftPrice;
    private String giftFundingRate;
    private int giftFundingPrice;
    private String dDay;
}
