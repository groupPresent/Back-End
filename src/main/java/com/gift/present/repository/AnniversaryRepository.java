package com.gift.present.repository;

import com.gift.present.model.Anniversary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnniversaryRepository extends JpaRepository<Anniversary, Long> {
    Optional<Anniversary> findByAnniversaryNameAndUser_Id(String anniversaryName, Long userId);
    Optional<Anniversary> findByIdAndUser_Id(Long anniversaryId, Long userId);
    List<Anniversary> findAllByUser_Id(Long userId);
    void deleteAllByUserId(Long userId);
}
