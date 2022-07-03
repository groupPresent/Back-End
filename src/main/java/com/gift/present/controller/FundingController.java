package com.gift.present.controller;

import com.gift.present.dto.fundingdto.FundingDetailResponseDto;
import com.gift.present.dto.fundingdto.FundingRequestDto;
import com.gift.present.dto.fundingdto.FundingResponseDto;
import com.gift.present.dto.userdto.UserInfoResponseDto;
import com.gift.present.model.Funding;
import com.gift.present.model.User;
import com.gift.present.security.UserDetailsImpl;
import com.gift.present.service.FundingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    public void createFunding(@AuthenticationPrincipal UserDetailsImpl userDetails,
                              @RequestPart(value = "giftPhoto", required = false) MultipartFile giftPhoto,
                              @RequestPart(value = "fundingRequestDto", required = false) FundingRequestDto fundingRequestDto)
    {
        User user = userDetails.getUser();
        fundingService.createFunding(giftPhoto, fundingRequestDto, user);
    }

    // 펀딩받고싶은선물페이지 - 수정
//    @PutMapping("/user/funding/{fundingId}")
//    public void editFunding(@AuthenticationPrincipal UserDetailsImpl userDetails,
//                            @RequestPart(value = "giftPhoto", required = false) MultipartFile giftPhoto,
//                            @RequestPart(value = "fundingRequestDto", required = false) FundingRequestDto fundingRequestDto,
//                            @PathVariable Long fundingId)
//    {
//        User user = userDetails.getUser();
//        fundingService.editFunding(giftPhoto, fundingRequestDto, fundingId, user);
//    }

    // 마이페이지 - 받은펀딩 목록조회
    @GetMapping("/user/funding")
    public ResponseEntity<List<FundingResponseDto>> getAllFundingList(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok().body(fundingService.getAllFundingList(user));
    }


}
