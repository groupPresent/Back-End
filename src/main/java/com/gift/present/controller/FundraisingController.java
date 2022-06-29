package com.gift.present.controller;

import com.gift.present.dto.fundraisingdto.FundraisingRequestDto;
import com.gift.present.dto.fundraisingdto.FundraisingResponseDto;
import com.gift.present.model.Fundraising;
import com.gift.present.model.User;
import com.gift.present.security.UserDetailsImpl;
import com.gift.present.service.FundraisingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FundraisingController {
    private final FundraisingService fundraisingService;

    // 펀딩세부페이지 - 모금하기
    @PostMapping("/user/fundraising/{fundingId}")
    public void createFundraise(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                @PathVariable Long fundingId,
                                @RequestBody FundraisingRequestDto fundraisingRequestDto)
    {
        User user = userDetails.getUser();
        fundraisingService.createFundraise(fundingId, fundraisingRequestDto, user);
    }

    // 프로필 보기 - 내가 준 모금 목록 조회하기
    @GetMapping("/user/fundraising")
    public ResponseEntity<List<FundraisingResponseDto>> getAllFundraising(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok().body(fundraisingService.getAllFundraising(user));
    }
}
