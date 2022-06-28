package com.gift.present.repository;

import com.gift.present.model.Anniversary;
import com.gift.present.model.Friendship;
import com.gift.present.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findAllByUser_Id(Long userId);
    List<Friendship> findAllByUser_IdAndFriendId(Long userId,Long friendId);

}
