package com.gift.present.controller;

import com.gift.present.dto.fundraisingdto.FundraisingRequestDto;
import com.gift.present.dto.fundraisingdto.FundraisingResponseDto;
import com.gift.present.model.Fundraising;
import com.gift.present.service.FundraisingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FundraisingController {
    private final FundraisingService fundraisingService;

    // 펀딩세부페이지 - 모금하기
    @PostMapping("/user/fundraising/{fundingId}")
    public void createFundraise(@PathVariable Long fundingId, @RequestBody FundraisingRequestDto fundraisingRequestDto) {
        // User
        fundraisingService.createFundraise(fundingId, fundraisingRequestDto);
    }

    // 프로필 보기 - 내가 준 모금 목록 조회하기
    @GetMapping("/user/fundraising")
    public ResponseEntity<List<FundraisingResponseDto>> getAllFundraising() {
        // User
        return ResponseEntity.ok().body(fundraisingService.getAllFundraising());
    }
}
