package com.muhammet.service;

import com.muhammet.document.Authority;
import com.muhammet.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository repository;

    public Authority save(Authority authority){
       return repository.save(authority);
    }

    public Optional<Authority> findByAuthId(Long authId){
        return repository.findOptionalByAuthId(authId);
    }
}

