package com.gift.present.service;

import com.gift.present.dto.fundingdto.ContributorDto;
import com.gift.present.dto.fundingdto.FundingDetailResponseDto;
import com.gift.present.dto.fundingdto.FundingRequestDto;
import com.gift.present.dto.fundingdto.FundingResponseDto;
import com.gift.present.model.Anniversary;
import com.gift.present.model.Funding;
import com.gift.present.model.Fundraising;
import com.gift.present.model.User;
import com.gift.present.repository.AnniversaryRepository;
import com.gift.present.repository.FundingRepository;
import com.gift.present.repository.FundraisingRepository;
import com.gift.present.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FundingService {
    private final FundingRepository fundingRepository;
    private final AnniversaryRepository anniversaryRepository;
    private final FundraisingRepository fundraisingRepository;
    private final UserRepository userRepository;

    // 펀딩세부페이지 - 조회
    @Transactional
    public FundingDetailResponseDto getFundingDetail(Long fundingId) {
        Funding funding = fundingRepository.findById(fundingId).orElseThrow(
                () -> new IllegalArgumentException("해당 펀딩이 존재하지 않습니다.")
        );
        List<Fundraising> fundraisingList = fundraisingRepository.findAllByFunding_Id(fundingId);
        return generateFundingDetailResponseDto(funding, fundraisingList);
    }

    // 펀딩받고싶은선물페이지 - 작성
    @Transactional
    public void createFunding(MultipartFile giftPhoto, FundingRequestDto fundingRequestDto) {
        Anniversary anniversary = anniversaryRepository.findByAnniversaryDateAndUser_Id(fundingRequestDto.getDate(), 1L);
        Funding funding = new Funding(1L, fundingRequestDto.getGiftName(), "giftPhotoUrl", fundingRequestDto.getGiftPrice(), anniversary);
        fundingRepository.save(funding);
    }

    // 펀딩받고싶은선물페이지 - 수정
    @Transactional
    public void editFunding(MultipartFile giftPhoto, FundingRequestDto fundingRequestDto, Long fundingId) {
        Anniversary anniversary = anniversaryRepository.findByAnniversaryDateAndUser_Id(fundingRequestDto.getDate(), 1L);
        Funding funding = fundingRepository.findById(fundingId).orElseThrow(
                () -> new IllegalArgumentException("해당 펀딩이 존재하지 않습니다.")
        );
        funding.update(fundingRequestDto, anniversary);
    }

    // 받은펀딩 목록 조회 메소드
    public List<FundingResponseDto> getAllFundingList() {
        List<FundingResponseDto> fundingResponseDtoList = new ArrayList<>();
        List<Funding> fundingList = fundingRepository.findAllByUserId(1L);
        for(Funding funding : fundingList) {
            List<Fundraising> fundraisingList = fundraisingRepository.findAllByFunding_Id(funding.getId());
            int giftFundingPrice = 0;
            for(Fundraising fundraising : fundraisingList) {
                giftFundingPrice += fundraising.getMoney();
            }
            fundingResponseDtoList.add(generateFundingResponseDto(funding, giftFundingPrice));
        }
        return fundingResponseDtoList;
    }


    // 펀딩 세부페이지 Dto 생성 메소드
    public FundingDetailResponseDto generateFundingDetailResponseDto(Funding funding, List<Fundraising> fundraisingList) {
        int moneys = 0;
        List<ContributorDto> contributorList = new ArrayList<>();
        for(Fundraising fundraising : fundraisingList) {
            moneys += fundraising.getMoney();
            System.out.println(fundraising.getMoney());
            String contributorName = userRepository.findById(fundraising.getContributorId()).orElseThrow(
                    () -> new IllegalArgumentException("해당하는 유저가 없습니다")
            ).getUserName();
            contributorList.add(generateContributorDto(contributorName));
        }
        return FundingDetailResponseDto.builder()
                .giftName(funding.getGiftName())
                .giftPhoto(funding.getGiftPhoto())
                .giftFundingRate((moneys/ funding.getGiftPrice()*100) + "%")
                .currentFundraisingPrice(moneys)
                .giftFundingPrice(funding.getGiftPrice())
                .contributorList(contributorList)
                .contributorNum(contributorList.size())
//                .commentList()
//                .commentNum(5)
                .build();
    }

    // contributorDto 생성 메소드
    public ContributorDto generateContributorDto(String contributorName) {
        return ContributorDto.builder()
                .name(contributorName)
                .build();
    }

    // FundingResponseDto 생성 메소드
    public FundingResponseDto generateFundingResponseDto(Funding funding, int giftFundingPrice) {
        return FundingResponseDto.builder()
                .giftPhoto(funding.getGiftPhoto())
                .giftName(funding.getGiftName())
                .giftPrice(funding.getGiftPrice())
                .giftFundingRate(giftFundingPrice / funding.getGiftPrice() * 100 + "%")
                .giftFundingPrice(giftFundingPrice)
                .anniversaryRemains("D-5")
                .build();
    }
}
