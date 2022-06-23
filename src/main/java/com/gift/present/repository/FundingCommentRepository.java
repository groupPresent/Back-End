package com.gift.present.repository;

import com.gift.present.model.FundingComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingCommentRepository extends JpaRepository<FundingComment, Long> {
}
