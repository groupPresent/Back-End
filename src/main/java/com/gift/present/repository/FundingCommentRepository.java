package com.gift.present.repository;

import com.gift.present.model.FundingComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FundingCommentRepository extends JpaRepository<FundingComment, Long> {
    List<FundingComment> findAllByFunding_Id(Long fundingId);
    Optional<FundingComment> findByIdAndAuthor(Long fundingCommentId, String author);
    void deleteByIdAndAuthor(Long fundingCommentId, String author);
    void deleteAllByAuthor(String name);
}
