package com.gift.present.dto.fundingdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendedGoods {
    private String goodsPhoto;
    private String goodsName;
}
