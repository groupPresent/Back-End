package com.gift.present.dto.qnAdto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QnADto {
    private String question;
    private int viewCnt;
}
