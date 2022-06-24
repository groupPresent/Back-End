package com.gift.present.dto.qnAdto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QnADetailDto {
    private String title;
    private int viewCnt;
    private String content;
}
