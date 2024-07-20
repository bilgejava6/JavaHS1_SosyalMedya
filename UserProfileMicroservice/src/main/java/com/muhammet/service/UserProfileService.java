package com.muhammet.service;

import com.muhammet.config.JwtManager;
import com.muhammet.dto.request.UserProfileCreateRequestDto;
import com.muhammet.document.UserProfile;
import com.muhammet.exception.ErrorType;
import com.muhammet.exception.UserProfileException;
import com.muhammet.mapper.UserProfileMapper;
import com.muhammet.rabbitmq.model.CreateAuthModel;
import com.muhammet.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository repository;
    private final JwtManager jwtManager;
    public Boolean save(UserProfileCreateRequestDto dto) {
        repository.save(UserProfileMapper.INSTANCE.fromRequestDto(dto));
        return true;
    }

    public void save(CreateAuthModel model){
        repository.save(UserProfile.builder()
                        .userName(model.getUserName())
                        .authId(model.getAuthId())
                .build());
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

    @Cacheable(value = "sifreleme")
    public String sifrele(String name) {
        String result="";
        String[] dize = {"K","Ü",",","?","(","&","%"};
        Random random = new Random();
        for (int i=0;i<name.length();i++){
           result = result.concat(name.charAt(name.length()-i-1)+dize[random.nextInt(6)]);
        }
        try{
            Thread.sleep(3000L);
        }catch (InterruptedException interruptedException){
            System.out.println("Err");
        }

        return result;
    }
}
