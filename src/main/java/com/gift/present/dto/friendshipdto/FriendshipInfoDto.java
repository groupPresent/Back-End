package com.gift.present.dto.friendshipdto;

import java.util.List;

import com.gift.present.dto.anniversarydto.AnniversaryRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipInfoDto {
    private String friendPhoto;
    private String friendName;
    private String birthDay;
    private List<AnniversaryRequestDto> anniversaryList;
}
