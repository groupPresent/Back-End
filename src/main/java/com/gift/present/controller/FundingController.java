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

    // 펀딩세부페이지 - 조회
    @GetMapping("/user/funding/{fundingId}")
    public ResponseEntity<FundingDetailResponseDto> getFundingDetail(@PathVariable Long fundingId) {
        return ResponseEntity.ok().body(fundingService.getFundingDetail(fundingId));
    }

    // 펀딩받고싶은선물페이지 - 작성
    @PostMapping("/user/funding")
    public void createFunding(@RequestPart(value = "giftPhoto", required = false) MultipartFile giftPhoto,
                              @RequestPart(value = "fundingRequestDto", required = false) FundingRequestDto fundingRequestDto
                              ) {
        // User
        fundingService.createFunding(giftPhoto, fundingRequestDto);
    }

    // 펀딩받고싶은선물페이지 - 수정
    @PutMapping("/user/funding/{fundingId}")
    public void editFunding(@RequestPart(value = "giftPhoto", required = false) MultipartFile giftPhoto,
                            @RequestPart(value = "fundingRequestDto", required = false) FundingRequestDto fundingRequestDto,
                            @PathVariable Long fundingId
                            ) {
        // User
        fundingService.editFunding(giftPhoto, fundingRequestDto, fundingId);
    }
}
