package com.gift.present.repository;

import com.gift.present.model.Fundraising;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundraisingRepository extends JpaRepository<Fundraising, Long> {
    List<Fundraising> findAllByFunding_Id(Long fundingId);
    List<Fundraising> findAllByContributorId(Long userId);
    void deleteAllByContributorId(Long userId);
}
