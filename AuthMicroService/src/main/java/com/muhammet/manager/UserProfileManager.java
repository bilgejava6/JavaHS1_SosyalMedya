package com.muhammet.manager;

import com.muhammet.dto.request.UserProfileCreateRequestDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.muhammet.config.RestApis.CREATE_PROFILE;

@FeignClient(url = "http://service-loadbalancer-user:9091/dev/v1/user-profile", name = "userProfileManager")
public interface UserProfileManager {

    @PostMapping(CREATE_PROFILE)
    ResponseEntity<Boolean> createProfile(@RequestBody UserProfileCreateRequestDto dto);

}
