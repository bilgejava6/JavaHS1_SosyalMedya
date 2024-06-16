package com.muhammet.service;

import com.muhammet.dto.request.UserProfileCreateRequestDto;
import com.muhammet.mapper.UserProfileMapper;
import com.muhammet.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository repository;

    public Boolean save(UserProfileCreateRequestDto dto) {
        repository.save(UserProfileMapper.INSTANCE.fromRequestDto(dto));
        return true;
    }
}
