package com.muhammet.repository;

import com.muhammet.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
    Optional<UserProfile> findOptionalByAuthId(Long auhtId);
}
