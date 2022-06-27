package com.gift.present.dto.qnAdto;

import com.gift.present.model.Reviews;
import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ReviewDto {
    private String reviewTitle;
    private String reviewContent;
    private int reviewStar;
    private String reviewPhoto;
    private int reviewPrice;
    //후기 작성시간 추가.

}


