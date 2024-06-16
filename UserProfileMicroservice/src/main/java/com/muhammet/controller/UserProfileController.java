package com.muhammet.controller;

import com.muhammet.dto.request.UserProfileCreateRequestDto;
import com.muhammet.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.muhammet.config.RestApis.*;
@RestController
@RequestMapping(USER_PROFILE)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping(CREATE_PROFILE)
    public ResponseEntity<Boolean> createProfile(@RequestBody @Valid UserProfileCreateRequestDto dto){
        return ResponseEntity.ok(userProfileService.save(dto));
    }
}
