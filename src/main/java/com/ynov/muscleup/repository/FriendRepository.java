package com.ynov.muscleup.repository;

import com.ynov.muscleup.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, String> {
}
