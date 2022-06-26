package com.gift.present.dto.anniversarydto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnniversaryInfoDto {
    private String name;
    private String date;
    private String anniversaryRemains;
}
