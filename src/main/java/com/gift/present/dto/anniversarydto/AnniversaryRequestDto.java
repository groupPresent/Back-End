package com.gift.present.dto.anniversarydto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnniversaryRequestDto {
    private String anniversaryName;
    private String anniversaryDate;
    private String anniversaryContent;
}
