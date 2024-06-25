package com.muhammet.repository;

import com.muhammet.document.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserProfileRepository extends MongoRepository<UserProfile,String> {
    Optional<UserProfile> findOptionalByAuthId(Long auhtId);
}
