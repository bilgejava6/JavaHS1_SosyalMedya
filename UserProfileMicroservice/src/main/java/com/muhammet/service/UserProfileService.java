package com.muhammet.service;

import com.muhammet.config.JwtManager;
import com.muhammet.dto.request.UserProfileCreateRequestDto;
import com.muhammet.document.UserProfile;
import com.muhammet.exception.ErrorType;
import com.muhammet.exception.UserProfileException;
import com.muhammet.mapper.UserProfileMapper;
import com.muhammet.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository repository;
    private final JwtManager jwtManager;
    public Boolean save(UserProfileCreateRequestDto dto) {
        repository.save(UserProfileMapper.INSTANCE.fromRequestDto(dto));
        return true;
    }

    public List<UserProfile> getAll(String token) {
        Optional<Long> authId = jwtManager.getAuthId(token);
        if(authId.isEmpty())
            throw new UserProfileException(ErrorType.INVALID_TOKEN_ERROR);
        Optional<UserProfile> optionalUser = repository.findOptionalByAuthId(authId.get());
        if (optionalUser.isEmpty())
            throw new UserProfileException(ErrorType.INVALID_AUTHID_ERROR);
        return repository.findAll();
    }
}
