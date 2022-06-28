package com.gift.present.dto.fundingcommentdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FundingCommentResponseDto {
    private String commenter;
    private String content;
}
