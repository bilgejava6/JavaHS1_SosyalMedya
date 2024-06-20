package com.muhammet.service;

import com.muhammet.dto.request.UserProfileCreateRequestDto;
import com.muhammet.entity.UserProfile;
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

    public Boolean save(UserProfileCreateRequestDto dto) {
        repository.save(UserProfileMapper.INSTANCE.fromRequestDto(dto));
        return true;
    }

    public List<UserProfile> getAll(String token) {
        if(!token.startsWith("TKN: "))
            throw new UserProfileException(ErrorType.INVALID_TOKEN_ERROR);
        String authIdString = token.substring(5);
        Long auhtId = Long.parseLong(authIdString);
        Optional<UserProfile> optionalUser = repository.findOptionalByAuthId(auhtId);
        if (optionalUser.isEmpty())
            throw new UserProfileException(ErrorType.INVALID_AUTHID_ERROR);
        return repository.findAll();
    }
}
