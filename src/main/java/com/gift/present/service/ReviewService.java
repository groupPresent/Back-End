package com.gift.present.service;

import com.gift.present.dto.qnAdto.ReviewDto;
import com.gift.present.model.Reviews;
import com.gift.present.repository.ReviewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewsRepository reviewsRepository;

    //내가 남긴 후기 History 목록 조회
    public List<ReviewDto> getReviews() {
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        List<Reviews> reviewsList = reviewsRepository.findAll();

        for(Reviews reviews : reviewsList) {
            reviewDtoList.add(generateReviewDto(reviews));
        }
        return reviewDtoList;
    }

    public ReviewDto generateReviewDto(Reviews reviews) {
        return ReviewDto.builder()
                .reviewTitle(reviews.getReviewTitle())
                .reviewContent(reviews.getReviewContent())
                .reviewStar(reviews.getReviewStar())
                .reviewPhoto(reviews.getReviewPhoto())
                .build();
    }

    public void WriteReview(ReviewDto reviewDto) {
         Reviews review = new Reviews(reviewDto.getReviewTitle(), reviewDto.getReviewContent(),
                reviewDto.getReviewStar(), reviewDto.getReviewPhoto());
                reviewsRepository.save(review);

    }

}
