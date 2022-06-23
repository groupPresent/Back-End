package com.gift.present.repository;

import com.gift.present.model.Anniversary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnniversaryRepository extends JpaRepository<Anniversary, Long> {
}
