package com.gift.present.service;

import com.gift.present.dto.qnAdto.ReviewDto;
import com.gift.present.model.Reviews;
import com.gift.present.model.User;
import com.gift.present.repository.ReviewsRepository;
import com.gift.present.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewsRepository reviewsRepository;
    private final UserRepository userRepository;

    //내가 남긴 후기 History 목록 조회
    public List<ReviewDto> getReviews(User user) {
        List<ReviewDto> reviewDtoList = new ArrayList<>();

        List<Reviews> reviewsList = reviewsRepository.findAllByUser_Id(user.getId());

        for(Reviews reviews : reviewsList) {
            reviewDtoList.add(generateReviewDto(reviews));
        }
        return reviewDtoList;
    }

    // 후기 작성
    public void writeReview(ReviewDto reviewDto, User user, Long fundingId) {
        User findUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당하는 유저가 존재하지 않습니다.")
        );
        Reviews review = new Reviews(reviewDto.getReviewTitle(), reviewDto.getReviewContent(),
                reviewDto.getReviewStar(), reviewDto.getReviewPhoto(), reviewDto.getReviewPrice(), findUser, fundingId);
        reviewsRepository.save(review);
    }

    public ReviewDto generateReviewDto(Reviews reviews) {
        return ReviewDto.builder()
                .reviewTitle(reviews.getReviewTitle())
                .reviewContent(reviews.getReviewContent())
                .reviewStar(reviews.getReviewStar())
                .reviewPhoto(reviews.getReviewPhoto())
                .reviewPrice(reviews.getReviewPrice())
                .build();
    }



}
