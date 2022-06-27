package com.gift.present.controller;


import com.gift.present.dto.qnAdto.ReviewDto;
import com.gift.present.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@AllArgsConstructor

public class MyPageController {
    public final ReviewService reviewService;

    // 내가 남긴 후기 history 목록 조회
    @GetMapping("/user/funding/review")
    public ResponseEntity<List<ReviewDto>> getAllReviews()
    {
        return ResponseEntity.ok().body(reviewService.getReviews());
    }

    //후기작성
    @PostMapping("/user/funding/review")
    public void WriteReview(@RequestBody ReviewDto reviewDto) {
        reviewService.WriteReview(reviewDto);
    }
}
