package com.gift.present.service;

import com.gift.present.dto.fundraisingdto.FundraisingRequestDto;
import com.gift.present.dto.fundraisingdto.FundraisingResponseDto;
import com.gift.present.model.Funding;
import com.gift.present.model.Fundraising;
import com.gift.present.model.User;
import com.gift.present.repository.FundingRepository;
import com.gift.present.repository.FundraisingRepository;
import com.gift.present.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FundraisingService {
    private final FundraisingRepository fundraisingRepository;
    private final FundingRepository fundingRepository;
    private final UserRepository userRepository;


    // 모금하기 메소드
    @Transactional
    public void createFundraise(Long fundingId, FundraisingRequestDto fundraisingRequestDto, User user) {
        Long userId = user.getId();
        Funding funding = fundingRepository.findByIdAndUserIdIsNot(fundingId, userId).orElseThrow(
                () -> new IllegalArgumentException("해당 펀딩이 존재하지 않거나 자신의 펀딩에는 모금할 수 없습니다.")
        );
        Fundraising fundraising = new Fundraising(funding, userId, fundraisingRequestDto.getDonation());
        fundraisingRepository.save(fundraising);
    }

    // 내가 준 모금 목록 조회
    @Transactional
    public List<FundraisingResponseDto> getAllFundraising(User user) {
        Long userId = user.getId();
        List<FundraisingResponseDto> fundraisingResponseDtoList = new ArrayList<>();
        userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저가 존재하지 않습니다.")
        );
        List<Fundraising> fundraisingList = fundraisingRepository.findAllByContributorId(userId);
        for(Fundraising fundraising : fundraisingList) {
            fundraisingResponseDtoList.add(generateFundraisingResponseDto(fundraising));
        }
        return fundraisingResponseDtoList;
    }

    // FundraisingResponseDto 생성 메소드
    public FundraisingResponseDto generateFundraisingResponseDto(Fundraising fundraising) {
        return FundraisingResponseDto.builder()
                .giftPhoto("NoImg")
                .giftName(fundraising.getFunding().getGiftName())
                .giftPrice(fundraising.getFunding().getGiftPrice())
                .giftFundingRate(fundraising.getMoney()/fundraising.getFunding().getGiftPrice()*100 + "%")
                .giftFundingPrice(fundraising.getMoney())
                .anniversaryRemains(fundraising.getFunding().getAnniversary().getAnniversaryDate())
                .build();
    }
}
