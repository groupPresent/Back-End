package com.gift.present.repository;

import com.gift.present.model.FundingComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundingCommentRepository extends JpaRepository<FundingComment, Long> {
    List<FundingComment> findAllByFunding_Id(Long fundingId);
}
