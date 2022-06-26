package com.gift.present.dto.userdto;

import com.gift.present.dto.anniversarydto.AnniversaryInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDto {
    private String userPhoto;
    private String userName;
    private String birthDay;
    private String accountNum;
    private List<AnniversaryInfoDto> anniversaryInfoList;
}
