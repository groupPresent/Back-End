package com.gift.present.repository;

import com.gift.present.model.Anniversary;
import com.gift.present.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnniversaryRepository extends JpaRepository<Anniversary, Long> {
    Anniversary findByAnniversaryDateAndUser_Id(String anniversaryDate, Long userId);
}
