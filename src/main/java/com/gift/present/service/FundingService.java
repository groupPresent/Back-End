package com.gift.present.service;

import com.gift.present.dto.fundingcommentdto.FundingCommentResponseDto;
import com.gift.present.dto.fundingdto.ContributorDto;
import com.gift.present.dto.fundingdto.FundingDetailResponseDto;
import com.gift.present.dto.fundingdto.FundingRequestDto;
import com.gift.present.dto.fundingdto.FundingResponseDto;
import com.gift.present.model.*;
import com.gift.present.repository.*;
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
    private final FundingCommentRepository fundingCommentRepository;
    private final UserRepository userRepository;

    // 펀딩세부페이지 - 조회
    @Transactional
    public FundingDetailResponseDto getFundingDetail(Long fundingId) {
        Funding funding = fundingRepository.findById(fundingId).orElseThrow(
                () -> new IllegalArgumentException("해당 펀딩이 존재하지 않습니다.")
        );
        List<Fundraising> fundraisingList = fundraisingRepository.findAllByFunding_Id(fundingId);
        List<FundingComment> fundingCommentList = fundingCommentRepository.findAllByFunding_Id(fundingId);

        return generateFundingDetailResponseDto(funding, fundraisingList, fundingCommentList);
    }

    // 펀딩받고싶은선물페이지 - 작성
    @Transactional
    public void createFunding(MultipartFile giftPhoto, FundingRequestDto fundingRequestDto, User user) {
        Long userId = user.getId();
        Anniversary anniversary = anniversaryRepository.findByAnniversaryNameAndUser_Id(fundingRequestDto.getAnniversaryName(), userId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 기념일이 존재하지 않습니다.")
        );
        Funding funding = new Funding(userId, fundingRequestDto.getGiftName(), "giftPhotoUrl", fundingRequestDto.getGiftPrice(), anniversary);
        fundingRepository.save(funding);
    }

//    // 펀딩받고싶은선물페이지 - 수정
//    @Transactional
//    public void editFunding(MultipartFile giftPhoto, FundingRequestDto fundingRequestDto, Long fundingId, User user) {
//        Anniversary anniversary = anniversaryRepository.findByAnniversaryNameAndUser_Id(fundingRequestDto.getAnniversaryName(), user.getId()).orElseThrow(
//                () -> new IllegalArgumentException("해당하는 기념일이 존재하지 않습니다.")
//        );
//        Funding funding = fundingRepository.findById(fundingId).orElseThrow(
//                () -> new IllegalArgumentException("해당 펀딩이 존재하지 않습니다.")
//        );
//        funding.update(fundingRequestDto, anniversary);
//    }

    // 받은펀딩 목록 조회 메소드
    public List<FundingResponseDto> getAllFundingList(User user) {
        List<FundingResponseDto> fundingResponseDtoList = new ArrayList<>();
        List<Funding> fundingList = fundingRepository.findAllByUserId(user.getId());
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



    public FundingCommentResponseDto generateFundingCommentResponseDto(FundingComment fundingComment) {
        return FundingCommentResponseDto.builder()
                .commenter(fundingComment.getAuthor())
                .content(fundingComment.getContent())
                .build();
    }

    // 펀딩 세부페이지 Dto 생성 메소드
    public FundingDetailResponseDto generateFundingDetailResponseDto(Funding funding, List<Fundraising> fundraisingList, List<FundingComment> fundingCommentList) {
        int moneys = 0;
        List<ContributorDto> contributorList = new ArrayList<>();
        for(Fundraising fundraising : fundraisingList) {
            moneys += fundraising.getMoney();
            User contributor = userRepository.findById(fundraising.getContributorId()).orElseThrow(
                    () -> new IllegalArgumentException("해당하는 유저가 없습니다")
            );
            Long contributorId = contributor.getId();
            String contributorName = contributor.getName();

            contributorList.add(generateContributorDto(contributorId, contributorName));
        }

        List<FundingCommentResponseDto> fundingCommentResponseDtoList = new ArrayList<>();
        for(FundingComment fundingComment : fundingCommentList) {
            fundingCommentResponseDtoList.add(generateFundingCommentResponseDto(fundingComment));
        }

        return FundingDetailResponseDto.builder()
                .giftName(funding.getGiftName())
                .giftPhoto(funding.getGiftPhoto())
                .giftFundingRate((moneys/ funding.getGiftPrice()*100) + "%")
                .currentFundraisingPrice(moneys)
                .giftFundingPrice(funding.getGiftPrice())
                .contributorList(contributorList)
                .contributorNum(contributorList.size())
                .commentList(fundingCommentResponseDtoList)
                .commentNum(fundingCommentList.size())
                .build();
    }

    // contributorDto 생성 메소드
    public ContributorDto generateContributorDto(Long contributorId, String contributorName) {
        return ContributorDto.builder()
                .id(contributorId)
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
