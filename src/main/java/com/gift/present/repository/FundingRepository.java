package com.gift.present.repository;

import com.gift.present.dto.fundingdto.FundingResponseDto;
import com.gift.present.model.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundingRepository extends JpaRepository<Funding, Long> {
    List<Funding> findAllByUserId(Long userId);
}
