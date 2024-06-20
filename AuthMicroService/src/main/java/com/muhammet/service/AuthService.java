package com.muhammet.service;

import com.muhammet.dto.request.AuthLoginRequestDto;
import com.muhammet.dto.request.AuthRegisterReqestDto;
import com.muhammet.dto.request.UserProfileCreateRequestDto;
import com.muhammet.entity.Auth;
import com.muhammet.exception.AuthException;
import com.muhammet.exception.ErrorType;
import com.muhammet.manager.UserProfileManager;
import com.muhammet.mapper.AuthMapper;
import com.muhammet.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthRepository repository;
    private final UserProfileManager userProfileManager;
    public void save(AuthRegisterReqestDto dto) {
      Auth auth =  repository.save(AuthMapper.INSTANCE.fromAuthRegisterRequestDto(dto));
      userProfileManager.createProfile(
              UserProfileCreateRequestDto.builder()
                      .authId(auth.getId())
                      .userName(auth.getUserName())
                      .build()
      );
    }

    public String login(AuthLoginRequestDto dto) {
        Optional<Auth> optionalAuth = repository
                .findOptionalByUserNameAndPassword(dto.getUserName(),dto.getPassword());
        if(optionalAuth.isEmpty())
            throw  new AuthException(ErrorType.BAD_REQUEST_USERNAME_OR_PASSWORD_ERROR);

        return "TKN: "+ optionalAuth.get().getId();
    }
}
