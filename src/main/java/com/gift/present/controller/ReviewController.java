package com.gift.present.controller;


import com.gift.present.dto.fundingdto.FundingRequestDto;
import com.gift.present.dto.qnAdto.ReviewDto;
import com.gift.present.model.User;
import com.gift.present.security.UserDetailsImpl;
import com.gift.present.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@AllArgsConstructor

public class ReviewController {
    public final ReviewService reviewService;

    // 내가 남긴 후기 history 목록 조회
    @GetMapping("/user/funding/review")
    public ResponseEntity<List<ReviewDto>> getAllReviews(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok().body(reviewService.getReviews(user));
    }

    //후기작성
    @PostMapping("/user/funding/{fundingId}/review")
    public void writeReview(@AuthenticationPrincipal UserDetailsImpl userDetails,
                            @RequestPart(value = "reviewPhoto", required = false) MultipartFile reviewPhoto,
                            //@RequestBody ReviewDto reviewDto,
                            @RequestPart(value = "reviewDto", required = false) ReviewDto reviewDto,
                            @PathVariable Long fundingId)
    {
        User user = userDetails.getUser();
        reviewService.writeReview(reviewPhoto, reviewDto, user, fundingId);
    }
}
