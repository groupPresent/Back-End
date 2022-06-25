package com.gift.present.dto.fundingdto;


import com.gift.present.dto.fundingcommentdto.FundingCommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FundingDetailResponseDto {
    private String giftName;
    private String giftPhoto;
    private String giftFundingRate;
    private int giftFundingPrice;
    private List<DonatorDto> donatorList;
    private int donatorNum;
    private List<FundingCommentResponseDto> commentList;
    private int commentNum;
}
