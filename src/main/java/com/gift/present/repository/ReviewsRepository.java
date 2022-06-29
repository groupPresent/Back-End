package com.gift.present.repository;

import com.gift.present.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {
    List<Reviews> findAllByUser_Id(Long userId);
}
