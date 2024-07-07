package com.muhammet.service;

import com.muhammet.document.UserProfile;
import com.muhammet.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository repository;

    public UserProfile save(UserProfile build) {
        return repository.save(build);
    }

    public Iterable<UserProfile> getAll() {
        return repository.findAll();
    }
}
