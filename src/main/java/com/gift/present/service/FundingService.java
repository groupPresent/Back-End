package com.gift.present.service;

import com.gift.present.dto.fundingdto.FundingDetailResponseDto;
import com.gift.present.dto.fundingdto.FundingRequestDto;
import com.gift.present.model.Funding;
import com.gift.present.repository.FundingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FundingService {
    private final FundingRepository fundingRepository;

    // 펀딩세부페이지 - 조회
    public FundingDetailResponseDto getFundingDetail(Long friendId, Long giftId) {

        return FundingDetailResponseDto.builder()
                .build();
    }

    // 펀딩받고싶은선물페이지-작성
    public void createFunding(Long userId, MultipartFile giftPhoto, FundingRequestDto fundingRequestDto) {
        // public Funding(Long userId, String giftName, String giftPhoto, int giftPrice, int fundingPrice) {
//        Funding funding = new Funding(userId, fundingRequestDto.getGiftName(), "giftPhoto", fundingRequestDto.getGiftPrice(), fundingRequestDto.);
    }


    // 펀딩 세부페이지 Dto 생성 메소드
    public FundingDetailResponseDto generateFundingDetailResponseDto(Funding funding) {
        return FundingDetailResponseDto.builder()
                .giftName(funding.getGiftName())
                .giftPhoto(funding.getGiftPhoto())
                .giftFundingRate((funding.getFundingPrice()/ funding.getGiftPrice()*100) + "%")
                .giftFundingPrice(funding.getGiftPrice())
//                .donatorList()
//                .donatorNum(5)
//                .commentList()
//                .commentNum(5)
                .build();
    }

    //

}
