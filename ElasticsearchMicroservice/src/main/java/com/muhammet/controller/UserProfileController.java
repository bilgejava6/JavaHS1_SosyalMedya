package com.muhammet.controller;

import com.muhammet.document.UserProfile;
import com.muhammet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/elastic-user")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping("/save")
    public ResponseEntity<UserProfile> save(String ad,String soyad,String adres){
     UserProfile userProfile =
             userProfileService.save(
                UserProfile.builder()
                        .ad(ad)
                        .soyad(soyad)
                        .adres(adres)
                        .createAt(System.currentTimeMillis())
                        .build()
        );
     return ResponseEntity.ok(userProfile);
    }
}
