package com.gift.present.controller;

import com.gift.present.dto.fundingdto.FundingDetailResponseDto;
import com.gift.present.dto.fundingdto.FundingRequestDto;
import com.gift.present.service.FundingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FundingController {
    private final FundingService fundingService;

    // 펀딩세부페이지-조회
    @GetMapping("/user/friend/{friendId}/gift/{giftId}")
    public ResponseEntity<FundingDetailResponseDto> getFundingDetail(@PathVariable Long friendId, @PathVariable Long giftId) {
        return ResponseEntity.ok().body(fundingService.getFundingDetail(friendId, giftId));
    }

    // 펀딩받고싶은선물페이지-작성
    @PostMapping("/user/{userId}/funding")
    public void createFunding(@PathVariable Long userId,
                              @RequestPart(value = "giftPhoto", required = false) MultipartFile giftPhoto,
                              @RequestPart(value = "fundingRequestDto", required = false) FundingRequestDto fundingRequestDto
                              ) {

        fundingService.createFunding(userId, giftPhoto, fundingRequestDto);
    }


}
