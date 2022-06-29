package com.gift.present.repository;

import com.gift.present.model.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FundingRepository extends JpaRepository<Funding, Long> {
    List<Funding> findAllByUserId(Long userId);
    Optional<Funding> findByIdAndUserIdIsNot(Long fundingId, Long userId);
    void deleteAllByUserId(Long userId);
}
