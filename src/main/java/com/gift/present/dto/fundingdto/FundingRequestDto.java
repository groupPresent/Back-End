package com.gift.present.dto.fundingdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FundingRequestDto {
    private String giftName;
    private int giftPrice;
    private String date;
}
