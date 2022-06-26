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
    private int currentFundraisingPrice;
    private int giftFundingPrice;
    private List<ContributorDto> contributorList;
    private int contributorNum;
    private List<FundingCommentResponseDto> commentList;
    private int commentNum;
}
