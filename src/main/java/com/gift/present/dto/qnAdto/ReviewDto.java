package com.gift.present.dto.qnAdto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private String reviewTitle;
    private String reviewContent;
    private int reviewStar;
    private String reviewPhoto;
    private int reviewPrice;
}


